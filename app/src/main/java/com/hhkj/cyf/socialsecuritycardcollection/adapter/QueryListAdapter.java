package com.hhkj.cyf.socialsecuritycardcollection.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhkj.cyf.socialsecuritycardcollection.R;
import com.hhkj.cyf.socialsecuritycardcollection.bean.LawsListBean;
import com.hhkj.cyf.socialsecuritycardcollection.bean.QueryBean;

import java.util.List;


/**
 * Created by caoyingfu on 2017/8/16.
 */

public class QueryListAdapter extends BaseAdapter {

    private List<QueryBean> queryList;
    private Context context;

    public QueryListAdapter(List<QueryBean> queryList, Context context) {
        this.queryList = queryList;
        this.context = context;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_query, parent, false);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_id = (TextView) convertView.findViewById(R.id.tv_id);
            holder.tv_state = (TextView) convertView.findViewById(R.id.tv_state);
            holder.tv_cardState = (TextView) convertView.findViewById(R.id.tv_cardState);
            holder.ll_card = (LinearLayout) convertView.findViewById(R.id.ll_card);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.tv_name.setText(queryList.get(position).getName());
        holder.tv_id.setText(queryList.get(position).getIdentity());
        holder.tv_state.setText(queryList.get(position).getStateStr());
        holder.tv_cardState.setText(queryList.get(position).getCardStateStr());
        // 0保存成功，1上报成功，2上报失败，3异地
        if (queryList.get(position).getCardState().equals("1")) {
            holder.ll_card.setVisibility(View.VISIBLE);
            holder.tv_state.setTextColor(ContextCompat.getColor(context, android.R.color.holo_green_light));
        } else {
            holder.ll_card.setVisibility(View.GONE);
            switch (queryList.get(position).getCardState()) {
                case "0":
                    holder.tv_state.setTextColor(ContextCompat.getColor(context, android.R.color.holo_green_light));
                    break;
                case "2":
                    holder.tv_state.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_light));
                    break;
                case "3":
                    holder.tv_state.setTextColor(ContextCompat.getColor(context, android.R.color.holo_orange_light));
                    break;
            }
        }
        return convertView;
    }

    public class Holder {
        public TextView tv_name, tv_id, tv_state, tv_cardState;
        private LinearLayout ll_card;
    }

}
