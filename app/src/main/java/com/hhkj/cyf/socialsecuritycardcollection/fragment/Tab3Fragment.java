package com.hhkj.cyf.socialsecuritycardcollection.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhkj.cyf.socialsecuritycardcollection.R;
import com.hhkj.cyf.socialsecuritycardcollection.activity.AboutUsActivity;
import com.hhkj.cyf.socialsecuritycardcollection.activity.ChangePwActivity;
import com.hhkj.cyf.socialsecuritycardcollection.activity.FeedbackActivity;
import com.hhkj.cyf.socialsecuritycardcollection.activity.UserInfoActivity;
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity;
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant;

public class Tab3Fragment extends Fragment implements View.OnClickListener {

    private LinearLayout ll_userinfo, ll_changepw, ll_feedback, ll_aboutus,ll_callus;
    private TextView tv_version;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_tab3, container, false);
        initView(mView);
        setData();
        return mView;
    }

    private void initView(final View mView) {
        ((TextView) mView.findViewById(R.id.tv_toolsbar_title)).setText(getString(R.string.tab3_fragment));
        ll_userinfo = mView.findViewById(R.id.ll_userinfo);
        ll_changepw = mView.findViewById(R.id.ll_changepw);
        ll_feedback = mView.findViewById(R.id.ll_feedback);
        ll_aboutus = mView.findViewById(R.id.ll_aboutus);
        tv_version = mView.findViewById(R.id.tv_version);
        ll_callus = mView.findViewById(R.id.ll_callus);
        ll_userinfo.setOnClickListener(this);
        ll_changepw.setOnClickListener(this);
        ll_feedback.setOnClickListener(this);
        ll_aboutus.setOnClickListener(this);
        ll_callus.setOnClickListener(this);
    }

    private void setData() {
        tv_version.setText("当前版本：" + ((BaseActivity) getActivity()).getAppVersionName());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_userinfo:
                startActivity(new Intent(getActivity(), UserInfoActivity.class));
                break;
            case R.id.ll_changepw:
                startActivity(new Intent(getActivity(), ChangePwActivity.class));
                break;
            case R.id.ll_feedback:
                startActivity(new Intent(getActivity(), FeedbackActivity.class));
                break;
            case R.id.ll_aboutus:
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
                break;
            case R.id.ll_callus:
                 AlertDialog.Builder normalDialog = new AlertDialog.Builder(getActivity());
                normalDialog.setTitle("提示");
                normalDialog.setMessage("确认拨打电话吗？");
                normalDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                ((BaseActivity)getActivity()).callPhone(Constant.phone);
                            }
                        });
                normalDialog.setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                // 显示
                normalDialog.show();
                break;
        }
    }

}
