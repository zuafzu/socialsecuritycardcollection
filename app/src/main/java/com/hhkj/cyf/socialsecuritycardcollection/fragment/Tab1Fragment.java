package com.hhkj.cyf.socialsecuritycardcollection.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.hhkj.cyf.socialsecuritycardcollection.R;
import com.hhkj.cyf.socialsecuritycardcollection.activity.CollectActivity;
import com.hhkj.cyf.socialsecuritycardcollection.activity.QueryListActivity;
import com.hhkj.cyf.socialsecuritycardcollection.activity.WebActivity;
import com.hhkj.cyf.socialsecuritycardcollection.adapter.HomeAdapter;
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity;
import com.hhkj.cyf.socialsecuritycardcollection.bean.HomeBean;
import com.hhkj.cyf.socialsecuritycardcollection.url.Urls;

import java.util.ArrayList;
import java.util.List;

public class Tab1Fragment extends Fragment implements View.OnClickListener {

    private ImageView iv_head;
    private LinearLayout ll1, ll2, ll3, ll4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_tab1, container, false);
        initView(mView);
        setData();
        return mView;
    }

    private void initView(final View mView) {
        ((TextView) mView.findViewById(R.id.tv_toolsbar_title)).setText(getString(R.string.tab1_fragment));
        iv_head = mView.findViewById(R.id.iv_head);
        ll1 = mView.findViewById(R.id.ll1);
        ll2 = mView.findViewById(R.id.ll2);
        ll3 = mView.findViewById(R.id.ll3);
        ll4 = mView.findViewById(R.id.ll4);
        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);
        ll4.setOnClickListener(this);
        ll1.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ll1.getLayoutParams();
                layoutParams.height = ll1.getWidth();
                ll1.setLayoutParams(layoutParams);
                ll2.setLayoutParams(layoutParams);
                ll3.setLayoutParams(layoutParams);
                ll4.setLayoutParams(layoutParams);
            }
        });
    }

    private void setData() {
        Glide.with(getActivity())
                .load("http://images.liqucn.com/img/h1/h973/img201709231534150_info300X300.jpg").apply(
                RequestOptions.bitmapTransform(new CircleCrop())
        ).into(iv_head);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll1:
                Intent intent1 = new Intent(getActivity(), CollectActivity.class);
                intent1.putExtra("title","采集");
                startActivity(intent1);
                break;
            case R.id.ll2:
                Intent intent2 = new Intent(getActivity(), QueryListActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll3:
                Intent intent3 = new Intent(getActivity(), WebActivity.class);
                intent3.putExtra("title", "社保查询");
                intent3.putExtra("url", Urls.url_ccshbx);
                startActivity(intent3);
                break;
            case R.id.ll4:
                Intent intent4 = new Intent(getActivity(), WebActivity.class);
                intent4.putExtra("title", "医保查询");
                intent4.putExtra("url", Urls.url_ccyb);
                startActivity(intent4);
                break;
        }
    }
}
