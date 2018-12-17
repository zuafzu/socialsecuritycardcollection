package com.hhkj.cyf.socialsecuritycardcollection.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hhkj.cyf.socialsecuritycardcollection.R;
import com.hhkj.cyf.socialsecuritycardcollection.bean.QueryBean;

import java.util.List;


/**
 * Created by caoyingfu on 2017/8/16.
 */

public class QueryListAdapter extends BaseAdapter {

    private OnMyClickListener onMyClickListener;
    private List<QueryBean> queryList;
    private Context context;

    public QueryListAdapter(List<QueryBean> queryList, Context context, OnMyClickListener onMyClickListener) {
        this.queryList = queryList;
        this.context = context;
        this.onMyClickListener = onMyClickListener;
    }

    @Override
    public int getCount() {
        return queryList == null ? 0 : queryList.size();
    }

    @Override
    public Object getItem(int position) {
        return queryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_query, parent, false);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_id = (TextView) convertView.findViewById(R.id.tv_id);
            holder.tv_state = (TextView) convertView.findViewById(R.id.tv_state);
            holder.btn_edit = (TextView) convertView.findViewById(R.id.btn_edit);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.tv_name.setText(queryList.get(position).getName());
        holder.tv_id.setText(queryList.get(position).getIdentity());
        holder.tv_state.setText(queryList.get(position).getStateStr());
        holder.btn_edit.setVisibility(View.GONE);
        // 0保存成功，1上传成功，2上传失败，3异地
        switch (queryList.get(position).getState()) {
            case "0":
                holder.btn_edit.setVisibility(View.VISIBLE);
                holder.btn_edit.setText("查看");
                holder.tv_state.setText("保存成功");
                holder.tv_state.setTextColor(ContextCompat.getColor(context, android.R.color.holo_green_light));
                break;
            case "1":
                holder.btn_edit.setVisibility(View.VISIBLE);
                holder.btn_edit.setText("查看");
                holder.tv_state.setText("上传成功——如需查询制卡进度，请您点击主界面中“制卡进度查询”");
                holder.tv_state.setTextColor(ContextCompat.getColor(context, android.R.color.holo_green_light));
                break;
            case "2":
                holder.btn_edit.setVisibility(View.VISIBLE);
                holder.btn_edit.setText("修改");
                holder.tv_state.setText("上传失败——失败原因");
                holder.tv_state.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_light));
                break;
            case "3":
                holder.btn_edit.setVisibility(View.VISIBLE);
                holder.btn_edit.setText("查看");
                holder.tv_state.setText("异地上报——您已在XX地上报");
                holder.tv_state.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_light));
                break;
        }
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMyClickListener.clickBtn(position);
            }
        });
        return convertView;
    }

    public class Holder {
        public TextView tv_name, tv_id, tv_state, btn_edit;
    }

    public interface OnMyClickListener {
        void clickBtn(int index);
    }

}
