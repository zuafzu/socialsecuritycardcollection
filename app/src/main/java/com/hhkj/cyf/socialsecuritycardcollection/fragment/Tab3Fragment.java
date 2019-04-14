package com.hhkj.cyf.socialsecuritycardcollection.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.hhkj.cyf.socialsecuritycardcollection.R;
import com.hhkj.cyf.socialsecuritycardcollection.activity.AboutUsActivity;
import com.hhkj.cyf.socialsecuritycardcollection.activity.ChangePwActivity;
import com.hhkj.cyf.socialsecuritycardcollection.activity.ConnectPhoneListActivity;
import com.hhkj.cyf.socialsecuritycardcollection.activity.FeedbackActivity;
import com.hhkj.cyf.socialsecuritycardcollection.activity.LoginActivity;
import com.hhkj.cyf.socialsecuritycardcollection.activity.UserInfoActivity;
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity;
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant;
import com.hhkj.cyf.socialsecuritycardcollection.tools.SPTools;

public class Tab3Fragment extends Fragment implements View.OnClickListener {

    private LinearLayout ll_userinfo, ll_changepw, ll_feedback, ll_aboutus, ll_callus, ll_logout;
    private TextView tv_version;
    private TextView tv_nickName;
    private TextView tv_phone;
    private ImageView iv_img;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_tab3, container, false);
        initView(mView);
        return mView;
    }

    private void initView(final View mView) {
        ((TextView) mView.findViewById(R.id.tv_toolsbar_title)).setText(getString(R.string.tab3_fragment));
        tv_nickName = mView.findViewById(R.id.tv_nickName);
        iv_img = mView.findViewById(R.id.iv_img);
        tv_phone = mView.findViewById(R.id.tv_phone);
        ll_userinfo = mView.findViewById(R.id.ll_userinfo);
        ll_changepw = mView.findViewById(R.id.ll_changepw);
        ll_feedback = mView.findViewById(R.id.ll_feedback);
        ll_aboutus = mView.findViewById(R.id.ll_aboutus);
        tv_version = mView.findViewById(R.id.tv_version);
        ll_callus = mView.findViewById(R.id.ll_callus);
        ll_logout = mView.findViewById(R.id.ll_logout);
        ll_userinfo.setOnClickListener(this);
        ll_changepw.setOnClickListener(this);
        ll_feedback.setOnClickListener(this);
        ll_aboutus.setOnClickListener(this);
        ll_callus.setOnClickListener(this);
        ll_logout.setOnClickListener(this);
    }

    private void setData() {

        tv_phone.setText("" + SPTools.INSTANCE.get(getActivity(), Constant.PHONE, ""));
        tv_version.setText("当前版本：" + ((BaseActivity) getActivity()).getAppVersionName());

        Log.e("zj", "onResume");
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(R.mipmap.ic_head);
        requestOptions.placeholder(R.mipmap.ic_head);
//        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);///不使用磁盘缓存
//        requestOptions.skipMemoryCache(true); // 不使用内存缓存
        Log.e("zj", "url" + SPTools.INSTANCE.get(getActivity(), Constant.HEADPHOTO, "") + "&" + System.currentTimeMillis());
        Glide.with(getActivity()).load(SPTools.INSTANCE.get(getActivity(), Constant.HEADPHOTO, "") + "&" + System.currentTimeMillis()).apply(requestOptions).into(iv_img);

    }

    @Override
    public void onResume() {
        super.onResume();

        setData();
        tv_nickName.setText("" + SPTools.INSTANCE.get(getActivity(), Constant.USERNAME, ""));
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
              Intent intent =   new Intent(getActivity(), ConnectPhoneListActivity.class);
                intent.putExtra("title","联系我们");
                startActivity(intent);

//                AlertDialog.Builder normalDialog = new AlertDialog.Builder(getActivity());
//                normalDialog.setTitle("提示");
//                normalDialog.setMessage("确认拨打电话吗？");
//                normalDialog.setPositiveButton("确定",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                                ((BaseActivity) getActivity()).callPhone(Constant.phone);
//                            }
//                        });
//                normalDialog.setNegativeButton("取消",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        });
//                // 显示
//                normalDialog.show();
                break;
            case R.id.ll_logout:
                AlertDialog.Builder logoutDialog = new AlertDialog.Builder(getActivity());
                logoutDialog.setTitle("提示");
                logoutDialog.setMessage("确认退出当前账号吗？");
                logoutDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                String username =""+ SPTools.INSTANCE.get(getActivity(),Constant.PHONE,"");
                                SPTools.INSTANCE.clear(getActivity());
                                SPTools.INSTANCE.put(getActivity(),Constant.PHONE,username);
                                getActivity().finish();
                                startActivity(new Intent(getActivity(), LoginActivity.class));
                            }
                        });
                logoutDialog.setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                // 显示
                logoutDialog.show();
                break;
        }
    }

}
