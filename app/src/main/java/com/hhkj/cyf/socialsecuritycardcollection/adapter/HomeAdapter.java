package com.hhkj.cyf.socialsecuritycardcollection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hhkj.cyf.socialsecuritycardcollection.R;
import com.hhkj.cyf.socialsecuritycardcollection.bean.HomeBean;

import java.util.List;


/**
 * Created by caoyingfu on 2017/8/16.
 */

public class HomeAdapter extends BaseAdapter {

    private List<HomeBean> homeModels;
    private Context context;

    public HomeAdapter(List<HomeBean> homeModels, Context context) {

        this.homeModels = homeModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return homeModels == null ? 0 : homeModels.size();
    }

    @Override
    public Object getItem(int position) {
        return homeModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_home, parent, false);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.textView = (TextView) convertView.findViewById(R.id.textView);
            holder.l1 = (LinearLayout) convertView.findViewById(R.id.l1);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
//        Glide.with(context).load(homeModels.get(position).getImageUrl()).into(holder.imageView);

        holder.imageView.setImageResource(homeModels.get(position).getImageUrl());

        holder.l1 = (LinearLayout) convertView.findViewById(R.id.l1);
        final Holder finalHolder = holder;
        holder.l1.post(new Runnable() {
            @Override
            public void run() {
                AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) finalHolder.l1.getLayoutParams();
                layoutParams.height = finalHolder.l1.getWidth();
                finalHolder.l1.setLayoutParams(layoutParams);
            }
        });

        holder.textView.setText(homeModels.get(position).getModelName());
        return convertView;
    }

    public class Holder {
        public ImageView imageView;
        public TextView textView;
        public LinearLayout l1;
    }

}
