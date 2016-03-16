package com.fuxuemingzhu.wechoice.module.choice;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fuxuemingzhu.wechoice.R;
import com.fuxuemingzhu.wechoice.model.bean.Choice;
import com.fuxuemingzhu.wechoice.module.content.ContentActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 包名：com.fuxuemingzhu.wechoice.module.main
 * 类描述：
 * 创建人：fuxuemingzhu
 * 邮箱：fuxuemingzhu@163.com
 * 创建时间：2016/3/16 10:32
 * <p>
 * 修改人：fuxuemingzhu
 * 修改时间：2016/3/16 10:32
 * 修改备注：
 *
 * @version 1.0
 */
public class ChoiceVH extends BaseViewHolder<Choice> {

    @Bind(R.id.tv_adapter_choice_title)
    public TextView title;
    @Bind(R.id.tv_adapter_choice_source)
    public TextView source;
    @Bind(R.id.tv_adapter_choice_time)
    public TextView time;
    @Bind(R.id.iv_adapter_choice_image)
    public ImageView image;

    public ChoiceVH(ViewGroup parent) {
        super(parent, R.layout.adapter_choice);
        ButterKnife.bind(this, itemView);
    }


    @Override
    public void setData(Choice data) {
        title.setText(data.getTitle());
        time.setText(data.getId().substring(7, 15));
        if (data.getId().length() >= 15) {
            String timeString = data.getId().substring(7, 15);
            String timeShow = timeString.substring(0, 4) + "-" + timeString.substring(4, 6) + "-" + timeString
                    .substring(6);
            time.setText(timeShow);
        } else {
            time.setText(Calendar.getInstance().getTime().toString());
        }
        if (data.getFirstImg() != null && !data.getFirstImg().equals("")) {
            Glide.with(getContext()).load(data.getFirstImg()).into(image);
        } else {
            Glide.with(getContext()).load(R.mipmap.ic_launcher).into(image);
        }
        source.setText(data.getSource());


        itemView.setOnClickListener(position -> {
            Bundle bundle = new Bundle();
            bundle.putString("url", data.getUrl());
            // 创建一个Intent
            Intent intent = new Intent(getContext(),
                    ContentActivity.class);
            intent.putExtras(bundle);
            // 启动intent对应的Activity
            getContext().startActivity(intent);
        });
    }

}
