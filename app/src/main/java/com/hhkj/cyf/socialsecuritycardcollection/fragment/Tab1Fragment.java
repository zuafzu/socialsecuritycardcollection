package com.hhkj.cyf.socialsecuritycardcollection.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hhkj.cyf.socialsecuritycardcollection.R;
import com.hhkj.cyf.socialsecuritycardcollection.activity.SelectCollectActivity;
import com.hhkj.cyf.socialsecuritycardcollection.activity.WebActivity;
import com.hhkj.cyf.socialsecuritycardcollection.adapter.HomeAdapter;
import com.hhkj.cyf.socialsecuritycardcollection.bean.BaseBean;
import com.hhkj.cyf.socialsecuritycardcollection.bean.HomeBean;
import com.hhkj.cyf.socialsecuritycardcollection.bean.StatusBean;
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant;
import com.hhkj.cyf.socialsecuritycardcollection.tools.NetTools;
import com.hhkj.cyf.socialsecuritycardcollection.tools.SPTools;
import com.hhkj.cyf.socialsecuritycardcollection.url.Urls;
import com.hhkj.cyf.socialsecuritycardcollection.view.SlideShowView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tab1Fragment extends Fragment implements View.OnClickListener {

    private SlideShowView slideShowView;
    private GridView gridView;
    private List<HomeBean> homeModel;
    private LinearLayout ll_status;
    private ImageView iv_status1;
    private ImageView iv_status2;
    private TextView tv_status1;
    private TextView tv_status2;
    private TextView tv_msg1;
    private TextView tv_msg2;

    private StatusBean statusBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_tab1, container, false);
        initView(mView);
        setData();
        net_selfQuery();
        return mView;
    }

    private void initView(final View mView) {
        ((TextView) mView.findViewById(R.id.tv_toolsbar_title)).setText(getString(R.string.tab1_fragment));
        ll_status = mView.findViewById(R.id.ll_status);
        iv_status1 = mView.findViewById(R.id.iv_status1);
        iv_status2 = mView.findViewById(R.id.iv_status2);
        tv_status1 = mView.findViewById(R.id.tv_status1);
        tv_status2 = mView.findViewById(R.id.tv_status2);
        tv_msg1 = mView.findViewById(R.id.tv_msg1);
        tv_msg2 = mView.findViewById(R.id.tv_msg2);
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
//


    }

    private void setData() {
        homeModel = new ArrayList<>();
        HomeBean homeBean1 = new HomeBean(R.mipmap.ic_launcher, "1", "社会保障卡信息采集");
        HomeBean homeBean2 = new HomeBean(R.mipmap.ic_launcher, "2", "制卡进度查询");
        HomeBean homeBean3 = new HomeBean(R.mipmap.ic_launcher, "3", "社保查询");
        HomeBean homeBean4 = new HomeBean(R.mipmap.ic_launcher, "4", "医保查询");
        homeModel.add(homeBean1);
        homeModel.add(homeBean2);
        homeModel.add(homeBean3);
        homeModel.add(homeBean4);
        gridView.setAdapter(new HomeAdapter(homeModel, getActivity()));
    }

    private void net_selfQuery() {

        Map<String, String> map = new HashMap<>();
        map.put("phone", "" + SPTools.INSTANCE.get(getActivity(), Constant.PHONE, ""));
        NetTools.net(map, new Urls().selfQuery, getActivity(), new NetTools.MyCallBack() {
            @Override
            public void getData(BaseBean response) {
                Log.e("zj", "net_selfQuery = " + response.getData());
                statusBean = new Gson().fromJson(response.getData(), StatusBean.class);

//                statusBean.setStatus("2");
//                statusBean.setStatusMsg("1");

                String msg1 = "";
                String msg2 = "";
                String[] temp = null;



                if (statusBean != null) {
                    if (statusBean.getStatusMsg() !=null){
                        if (statusBean.getStatusMsg().contains("，")) {
                            temp = statusBean.getStatusMsg().split("，");
                            msg1 = temp[0];
                            msg2 = temp[1];
                        } else {
                            msg1 = statusBean.getStatusMsg();
                        }
                    }
                    if ("0".equals(statusBean.getStatus())) {
                        ll_status.setVisibility(View.GONE);
                    } else if ("1".equals(statusBean.getStatus())) {
                        ll_status.setVisibility(View.VISIBLE);
                        iv_status1.setImageDrawable(getResources().getDrawable(R.drawable.shape_tab1_left));
                        iv_status2.setImageDrawable(getResources().getDrawable(R.drawable.shape_tab1_right));
                        tv_status1.setBackground(getResources().getDrawable(R.drawable.circle_tab1));
                        tv_status2.setTextColor(getResources().getColor(R.color.white));
                        tv_status2.setBackground(getResources().getDrawable(R.drawable.circle_tab2));
                        tv_msg1.setText(msg1);
                        tv_msg2.setText(msg2);
                    } else {
                        ll_status.setVisibility(View.VISIBLE);
                        iv_status1.setImageDrawable(getResources().getDrawable(R.drawable.shape_tab1_left));
                        iv_status2.setImageDrawable(getResources().getDrawable(R.drawable.shape_tab1_right2));

                        tv_status1.setBackground(getResources().getDrawable(R.drawable.circle_tab1));
                        tv_status2.setTextColor(getResources().getColor(R.color.colorPrimary));
                        tv_status2.setBackground(getResources().getDrawable(R.drawable.circle_tab1));

                        tv_msg1.setText(msg1);
                        tv_msg2.setText(msg2);
                        tv_msg2.setTextColor(getResources().getColor(R.color.colorPrimary));
                    }

                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

}
