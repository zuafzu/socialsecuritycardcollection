package com.hhkj.cyf.socialsecuritycardcollection.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import com.hhkj.cyf.socialsecuritycardcollection.R;
import com.hhkj.cyf.socialsecuritycardcollection.bean.DictionaryBean;
import com.hhkj.cyf.socialsecuritycardcollection.bean.SelectItemBean;

import java.util.List;


/**
 * Created by caoyingfu on 2017/8/16.
 */

public class SelectItemAdapter extends BaseAdapter {

    private List<DictionaryBean.ListBean> selectItemBeanList;
    private Activity activity;

    public SelectItemAdapter(List<DictionaryBean.ListBean> selectItemBeanList, Activity activity) {

        this.selectItemBeanList = selectItemBeanList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return selectItemBeanList == null ? 0 : selectItemBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return selectItemBeanList.get(position);
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
                    R.layout.item_select_item, parent, false);
            holder.tv_single_choice = (CheckedTextView) convertView.findViewById(R.id.tv_single_choice);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.tv_single_choice.setText(selectItemBeanList.get(position).getName());
        return convertView;
    }

    public class Holder {
        public CheckedTextView tv_single_choice;
    }

}
