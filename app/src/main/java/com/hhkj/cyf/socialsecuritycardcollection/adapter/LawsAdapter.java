package com.hhkj.cyf.socialsecuritycardcollection.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhkj.cyf.socialsecuritycardcollection.R;
import com.hhkj.cyf.socialsecuritycardcollection.bean.LawsListBean;

import java.lang.reflect.Array;
import java.util.List;


/**
 * Created by caoyingfu on 2017/8/16.
 */

public class LawsAdapter extends BaseAdapter {

    private int[] lawsListBeans;
    private Context context;

    public LawsAdapter(int[]  lawsListBeans, Context context) {
        this.lawsListBeans = lawsListBeans;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lawsListBeans == null ? 0 : lawsListBeans.length;
    }

    @Override
    public Object getItem(int position) {
        return lawsListBeans[position];
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
                    R.layout.item_law, parent, false);
            holder.item_iv = (ImageView) convertView.findViewById(R.id.item_iv);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.item_iv.setImageResource(lawsListBeans[position]);
        return convertView;
    }

    public class Holder {
        public ImageView item_iv;
    }

}
