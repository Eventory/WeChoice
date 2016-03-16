package com.fuxuemingzhu.wechoice.module.choice;

import android.os.Bundle;

import com.fuxuemingzhu.wechoice.model.ChoiceModel;
import com.fuxuemingzhu.wechoice.model.bean.Choice;
import com.fuxuemingzhu.wechoice.model.bean.ChoicePage;
import com.fuxuemingzhu.wechoice.model.callback.DataCallback;
import com.jude.beam.expansion.list.BeamListFragmentPresenter;
import com.jude.utils.JUtils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 包名：com.fuxuemingzhu.wechoice.module.choice
 * 类描述：
 * 创建人：fuxuemingzhu
 * 邮箱：fuxuemingzhu@163.com
 * 创建时间：2016/3/16 10:47
 * <p>
 * 修改人：fuxuemingzhu
 * 修改时间：2016/3/16 10:47
 * 修改备注：
 *
 * @version 1.0
 */
public class ChoicePresenter extends BeamListFragmentPresenter<ChoiceFragment, Choice> {

    private int morePages = 1;
    final int QUEUE_SIZE = 10;//队列大小
    //手写队列用来存储已经加载过的文章页数
    Queue<Integer> refreshQueue = new LinkedList<>();
    Queue<Integer> moreQueue = new LinkedList<>();

    int page = 1;

    @Override
    protected void onCreate(ChoiceFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
        onRefresh();
    }

    @Override
    public int getCurPage() {
        return super.getCurPage();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();

        if (refreshQueue.size() == QUEUE_SIZE) {
            refreshQueue.poll();
        }
        refreshQueue.offer(page);
        moreQueue.clear();
        moreQueue.offer(page);

        ChoiceModel.getInstance().getChoice(page, new DataCallback() {
            @Override
            public void success(String info, ChoicePage data) {
                getAdapter().clear();
                getAdapter().addAll(data.getContentlist());
                while (refreshQueue.contains(page)) {
                    page = (int) Math.ceil(Math.random() * 25);
                }
                JUtils.Log("refreshQueue", refreshQueue.toString());
            }

            @Override
            public void error(String errorInfo) {
                getView().showError(new Throwable(errorInfo));
            }
        });

    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        while (moreQueue.contains(morePages)) {
            morePages = (int) Math.ceil(Math.random() * 25);
        }
        if (moreQueue.size() == 25) {
            moreQueue.clear();
            moreQueue.offer(page);
        }
        moreQueue.offer(morePages);
        JUtils.Log("moreQueue", moreQueue.toString());


        ChoiceModel.getInstance().getChoice(morePages, new DataCallback() {
            @Override
            public void success(String info, ChoicePage data) {
                getAdapter().addAll(data.getContentlist());
            }

            @Override
            public void error(String errorInfo) {
                getView().showError(new Throwable(errorInfo));
            }
        });
    }
}
