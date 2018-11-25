package com.hhkj.cyf.socialsecuritycardcollection.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.hhkj.cyf.socialsecuritycardcollection.R;
import com.hhkj.cyf.socialsecuritycardcollection.activity.LawsListActivity;
import com.hhkj.cyf.socialsecuritycardcollection.adapter.LawsGridAdapter;
import com.hhkj.cyf.socialsecuritycardcollection.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

public class Tab2Fragment extends Fragment {

    private GridView gridView;
    private List<HomeBean> homeModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_tab2, container, false);
        initView(mView);
        setData();
        return mView;
    }

    private void initView(final View mView) {
        ((TextView) mView.findViewById(R.id.tv_toolsbar_title)).setText(getString(R.string.tab2_fragment));
        gridView = (GridView) mView.findViewById(R.id.gridView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), LawsListActivity.class);
                intent.putExtra("title", homeModel.get(i).getModelName());
                startActivity(intent);
            }
        });
    }

    private void setData() {
        homeModel = new ArrayList<>();
        HomeBean homeBean1 = new HomeBean(R.mipmap.ic_tab2_1, "1", "综合类");
        HomeBean homeBean2 = new HomeBean(R.mipmap.ic_tab2_2, "2", "城镇职工养老");
        HomeBean homeBean3 = new HomeBean(R.mipmap.ic_tab2_3, "3", "城乡居民养老");
        HomeBean homeBean4 = new HomeBean(R.mipmap.ic_tab2_4, "4", "失业保险");
        HomeBean homeBean5 = new HomeBean(R.mipmap.ic_tab2_5, "5", "机关事业养老保险");
        homeModel.add(homeBean1);
        homeModel.add(homeBean2);
        homeModel.add(homeBean3);
        homeModel.add(homeBean4);
        homeModel.add(homeBean5);
        gridView.setAdapter(new LawsGridAdapter(homeModel, getActivity()));
    }

}
