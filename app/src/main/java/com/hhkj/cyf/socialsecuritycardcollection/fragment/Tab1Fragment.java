package com.hhkj.cyf.socialsecuritycardcollection.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.hhkj.cyf.socialsecuritycardcollection.R;
import com.hhkj.cyf.socialsecuritycardcollection.activity.SelectCollectActivity;
import com.hhkj.cyf.socialsecuritycardcollection.activity.WebActivity;
import com.hhkj.cyf.socialsecuritycardcollection.adapter.HomeAdapter;
import com.hhkj.cyf.socialsecuritycardcollection.bean.HomeBean;
import com.hhkj.cyf.socialsecuritycardcollection.url.Urls;
import com.hhkj.cyf.socialsecuritycardcollection.view.SlideShowView;

import java.util.ArrayList;
import java.util.List;

public class Tab1Fragment extends Fragment implements View.OnClickListener {

    private SlideShowView slideShowView;
    private GridView gridView;
    private List<HomeBean> homeModel;

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
        slideShowView = (SlideShowView) mView.findViewById(R.id.slideShowView);
        // 轮播Banner
        slideShowView.setTimeInterval(5);
        slideShowView.setOnItemClickListener(new SlideShowView.OnSlideShowViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        int[] imgs = {R.mipmap.baaner1, R.mipmap.baaner2, R.mipmap.baaner3};
        slideShowView.initUI(getActivity(), 2f, imgs);
        gridView = (GridView) mView.findViewById(R.id.gridView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Intent intent1 = new Intent(getActivity(), SelectCollectActivity.class);
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent(getActivity(), WebActivity.class);
                        intent2.putExtra("title", "制卡进度查询");
                        intent2.putExtra("url", Urls.url_zkjdcx);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(getActivity(), WebActivity.class);
                        intent3.putExtra("title", "社保查询");
                        intent3.putExtra("url", Urls.url_ccshbx);
                        startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4 = new Intent(getActivity(), WebActivity.class);
                        intent4.putExtra("title", "医保查询");
                        intent4.putExtra("url", Urls.url_ccyb);
                        startActivity(intent4);
                        break;
                }
            }
        });
    }

    private void setData() {
        homeModel = new ArrayList<>();
        HomeBean homeBean1 = new HomeBean(R.mipmap.ic_tab2_1, "1", "社会保障卡信息采集");
        HomeBean homeBean2 = new HomeBean(R.mipmap.ic_tab2_2, "2", "制卡进度查询");
        HomeBean homeBean3 = new HomeBean(R.mipmap.ic_tab2_3, "3", "社保查询");
        HomeBean homeBean4 = new HomeBean(R.mipmap.ic_tab2_4, "4", "医保查询");
        homeModel.add(homeBean1);
        homeModel.add(homeBean2);
        homeModel.add(homeBean3);
        homeModel.add(homeBean4);
        gridView.setAdapter(new HomeAdapter(homeModel, getActivity()));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

}
