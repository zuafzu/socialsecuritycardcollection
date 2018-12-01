package com.hhkj.cyf.socialsecuritycardcollection.adapter;

import android.app.Activity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhkj.cyf.socialsecuritycardcollection.R;
import com.hhkj.cyf.socialsecuritycardcollection.bean.HomeBean;

import java.util.List;


/**
 * Created by caoyingfu on 2017/8/16.
 */

public class HomeAdapter extends BaseAdapter {

    private List<HomeBean> homeModels;
    private Activity activity;
    private int width;

    public HomeAdapter(List<HomeBean> homeModels, Activity activity) {

        Display display = activity.getWindowManager().getDefaultDisplay();
        width = display.getWidth() / 3;

        this.homeModels = homeModels;
        this.activity = activity;
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
            convertView = LayoutInflater.from(activity).inflate(
                    R.layout.item_home, parent, false);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.textView = (TextView) convertView.findViewById(R.id.textView);
            holder.l1 = (LinearLayout) convertView.findViewById(R.id.l1);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.imageView.setImageResource(homeModels.get(position).getImageUrl());

        holder.l1 = (LinearLayout) convertView.findViewById(R.id.l1);
        AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) holder.l1.getLayoutParams();
        layoutParams.height = width;
        holder.l1.setLayoutParams(layoutParams);

        holder.textView.setText(homeModels.get(position).getModelName());
        return convertView;
    }

    public class Holder {
        public ImageView imageView;
        public TextView textView;
        public LinearLayout l1;
    }

}
