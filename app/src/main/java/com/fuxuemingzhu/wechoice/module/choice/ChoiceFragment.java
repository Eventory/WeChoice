package com.fuxuemingzhu.wechoice.module.choice;

import android.view.ViewGroup;

import com.fuxuemingzhu.wechoice.model.bean.Choice;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListFragment;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

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
@RequiresPresenter(ChoicePresenter.class)
public class ChoiceFragment extends BeamListFragment<ChoicePresenter, Choice> {
    @Override
    protected BaseViewHolder getViewHolder(ViewGroup viewGroup, int i) {
        return new ChoiceVH(viewGroup);
    }

    @Override
    protected ListConfig getConfig() {
        return super.getConfig()
                .setLoadmoreAble(true)
                .setRefreshAble(true)
                .setNoMoreAble(true)
                .setErrorAble(true)
                .setErrorTouchToResumeAble(true);

    }
}
