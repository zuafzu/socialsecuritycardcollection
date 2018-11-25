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

import com.hhkj.cyf.socialsecuritycardcollection.R;
import com.hhkj.cyf.socialsecuritycardcollection.bean.HomeBean;
import com.hhkj.cyf.socialsecuritycardcollection.bean.LawsListBean;

import java.util.List;


/**
 * Created by caoyingfu on 2017/8/16.
 */

public class LawsListAdapter extends BaseAdapter {

    private List<LawsListBean> lawsListBeans;
    private Context context;

    public LawsListAdapter(List<LawsListBean> lawsListBeans, Context context) {
        this.lawsListBeans = lawsListBeans;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lawsListBeans == null ? 0 : lawsListBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return lawsListBeans.get(position);
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
                    R.layout.item_laws, parent, false);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.tv_title.setText(lawsListBeans.get(position).getTitle());
        return convertView;
    }

    public class Holder {
        public TextView tv_title;
    }

}
