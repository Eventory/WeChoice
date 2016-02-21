package com.fuxuemingzhu.wechoice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fuxuemingzhu.wechoice.R;
import com.fuxuemingzhu.wechoice.entity.Choice;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 包名：com.fuxuemingzhu.wechoice.adapter
 * 类描述：
 * 创建人：fuxuemingzhu
 * 邮箱：fuxuemingzhu@163.com
 * 创建时间：2016/2/21 19:43
 * <p/>
 * 修改人：fuxuemingzhu
 * 修改时间：2016/2/21 19:43
 * 修改备注：
 *
 * @version 1.0
 */
public class ChoiceAdapter extends BaseAdapter {
    private Context context;
    private List<Choice> list;
    private LayoutInflater inflater;

    public ChoiceAdapter(Context context, List<Choice> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(
                R.layout.adapter_choice, null);
        viewHolder = new ViewHolder();

        viewHolder.title = (TextView) view
                .findViewById(R.id.tv_adapter_choice_title);
        viewHolder.source = (TextView) view
                .findViewById(R.id.tv_adapter_choice_source);
        viewHolder.read = (TextView) view
                .findViewById(R.id.tv_adapter_choice_read);
        viewHolder.image = (ImageView) view
                .findViewById(R.id.iv_adapter_choice_image);
        view.setTag(viewHolder);
        viewHolder.title.setText(list.get(i).getTitle());
        viewHolder.source.setText(list.get(i).getSource());
        viewHolder.read.setText("" + 1000);
        if (list.get(i).getFirstImg() != null && !list.get(i).getFirstImg().equals("")) {
            Picasso.with(context).load(list.get(i).getFirstImg())
                    .into(viewHolder.image);
        }

        return view;
    }

    static class ViewHolder {
        public TextView title;
        public TextView source;
        public TextView read;
        public ImageView image;
    }

}
