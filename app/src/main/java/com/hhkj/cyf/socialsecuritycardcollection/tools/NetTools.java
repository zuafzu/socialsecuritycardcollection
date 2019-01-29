package com.hhkj.cyf.socialsecuritycardcollection.tools;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.hhkj.cyf.socialsecuritycardcollection.activity.LoginActivity;
import com.hhkj.cyf.socialsecuritycardcollection.app.MyApplication;
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity;
import com.hhkj.cyf.socialsecuritycardcollection.bean.BaseBean;
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant;
import com.hhkj.cyf.socialsecuritycardcollection.url.Urls;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.request.RequestCall;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public class NetTools {

    public static void net(String url, final Activity context, final MyCallBack myCallBack) {
        net(new HashMap<String, String>(), url, context, myCallBack, "正在加载...");
    }

    public static void net(Map<String, String> map, String url, final Activity context, final MyCallBack myCallBack) {
        net(map, url, context, myCallBack, "正在加载...");
    }

    public static void net(Map<String, String> map, String url, final Activity context, final MyCallBack myCallBack, final String msg) {
        net(map, url, context, myCallBack, msg, true, true);
    }

    public static void net(Map<String, String> map, final String url, final Activity context, final MyCallBack myCallBack, final String msg, final boolean isShow, final boolean isDismiss) {
        Log.e("zj", "net map = " + map.toString());
        Log.e("zj", "net url = " + url);
        Log.e("zj", "net token = " + SPTools.INSTANCE.get(context, Constant.TOKEN, "").toString());


        PostFormBuilder builder = OkHttpUtils.post().url(url);
        builder.addHeader(Constant.TOKEN, SPTools.INSTANCE.get(context, Constant.TOKEN, "").toString());
        builder.params(map);

        RequestCall call = builder.build();

        call.execute(new Callback<BaseBean>() {
            @Override
            public void onBefore(Request request, int id) {
                if (context != null && isShow) {
                    ((BaseActivity) context).showProgressDialog(msg);
                }
            }

            @Override
            public BaseBean parseNetworkResponse(Response response, int id) throws Exception {
                String json = response.body().string();
                Log.e("cyf7", "response : " + json);
                JSONObject jsonObject = new JSONObject(json);
                BaseBean bean = new BaseBean();
                bean.setCode(jsonObject.optString("code"));
                bean.setMsg(jsonObject.optString("msg"));
                String json2 = jsonObject.optString("data");
                if (!"".equals(json2) && !"{}".equals(json2) && !"{ }".equals(json2)) {
                    bean.setData(json2);
                }
                return bean;
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                // 无数据布局隐藏(后期可做网络错误显示)
//                ((BaseActivity) context).setListToastView(0, "", 0, false);
                if (e.getClass().getSimpleName().equals("ConnectException")) {
                    // 无法连接网络
                    Toast.makeText(context, "无法连接服务器", Toast.LENGTH_SHORT).show();
                } else if (e.getClass().getSimpleName().equals("SocketTimeoutException")) {
                    // 网络连接超时
                    Toast.makeText(context, "服务器连接超时", Toast.LENGTH_SHORT).show();
                } else {
                    // 其它异常
                    Log.e("Exception gson：", e.toString());
                }
                ((BaseActivity) context).dismissProgressDialog();
            }

            @Override
            public void onResponse(BaseBean baseBean, int id) {
                // 无数据布局隐藏
//                ((BaseActivity) context).setListToastView(0, "", 0, false);
                Log.e("zj", "bean = " + baseBean.toString());
                if ("0".equals(baseBean.getCode())) {

//                    Log.e("zj", "url = " + url);
//                    Log.e("zj", "baseBean = " + baseBean.toString());

//                    if (null == baseBean.getData() || "".equals(baseBean.getData())) {
//                        Toast.makeText(context, "返回data为null", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
                    if (isDismiss) {
                        ((BaseActivity) context).dismissProgressDialog();
                    }
                    if (myCallBack != null) {
                        myCallBack.getData(baseBean);
                    }

                } else if ("100".equals(baseBean.getCode())) {//201
                    // 登录信息失效
                    Toast.makeText(context, baseBean.getMsg(), Toast.LENGTH_SHORT).show();

                    for (int i = 0; i < MyApplication.Companion.getActivies().size(); i++) {
                        MyApplication.Companion.getActivies().get(i).finish();
                    }
                    context.startActivity(new Intent(context, LoginActivity.class));
                } else {
                    Toast.makeText(context, baseBean.getMsg(), Toast.LENGTH_SHORT).show();
                    ((BaseActivity) context).dismissProgressDialog();
                }
            }

            @Override
            public void onAfter(int id) {

            }

        });
    }

    /**
     * 上传文件(头像)
     *
     * @param map
     * @param context
     * @param myCallBack
     */
    public static void netFile(String remark, Map<String, File> map, final Activity context, final MyCallBack myCallBack) {
        PostFormBuilder builder = OkHttpUtils.post().url(new Urls().uploadPhoto);

        builder.addHeader(Constant.TOKEN, SPTools.INSTANCE.get(context, Constant.TOKEN, "").toString());
        builder.addFile("file", map.get("file").getName(), map.get("file"));

        Log.e("zj", "name = " + map.get("file").getName());
        builder.addParams("phone", "" + SPTools.INSTANCE.get(context, Constant.PHONE, ""));
        builder.addParams("remark", remark);


        RequestCall call = builder.build();
        call.execute(new Callback<BaseBean>() {
            @Override
            public void onBefore(Request request, int id) {
                if (context != null) {
                    ((BaseActivity) context).showProgressDialog("正在上传...");
                }
            }

            @Override
            public BaseBean parseNetworkResponse(Response response, int id) throws Exception {
                String json = response.body().string();
                Log.e("cyf7", "response : " + json);
                JSONObject jsonObject = new JSONObject(json);
                BaseBean bean = new BaseBean();
                bean.setCode(jsonObject.optString("code"));
                bean.setMsg(jsonObject.optString("msg"));
                // bean.setData(jsonObject.optString("data"));
                bean.setData(jsonObject.optString("data"));
                return bean;
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                if (e.getClass().getSimpleName().equals("ConnectException")) {
                    // 无法连接网络
                    Toast.makeText(context, "无法连接服务器", Toast.LENGTH_SHORT).show();
                } else if (e.getClass().getSimpleName().equals("SocketTimeoutException")) {
                    // 网络连接超时
                    Toast.makeText(context, "服务器连接超时", Toast.LENGTH_SHORT).show();
                } else {
                    // 其它异常
                    Log.e("Exception gson：", e.toString());
                }
                ((BaseActivity) context).dismissProgressDialog();
            }

            @Override
            public void onResponse(BaseBean baseBean, int id) {
                ((BaseActivity) context).dismissProgressDialog();

                if ("0".equals(baseBean.getCode())) {

                    if (myCallBack != null) {
                        myCallBack.getData(baseBean);
                    }
                    ((BaseActivity) context).dismissProgressDialog();

                } else if ("100".equals(baseBean.getCode())) {
                    // 登录信息失效
                    Toast.makeText(context, baseBean.getMsg(), Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < MyApplication.Companion.getActivies().size(); i++) {
                        MyApplication.Companion.getActivies().get(i).finish();
                    }
                    context.startActivity(new Intent(context, LoginActivity.class));
                } else {
                    if (myCallBack != null) {
                        myCallBack.getData(baseBean);
                    }
//                    Toast.makeText(context, baseBean.getMsg(), Toast.LENGTH_SHORT).show();
                    ((BaseActivity) context).dismissProgressDialog();
                }
            }

        });
    }

    public interface MyCallBack {
        void getData(BaseBean response);
    }

    // 下载图片
    public static void net_DownloadImg(final Activity context,String filePath, String fileName, String url, final MyCallBack myCallBack) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new FileCallBack(filePath, fileName) {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ((BaseActivity) context).dismissProgressDialog();
                        myCallBack.getData(null);
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        ((BaseActivity) context).dismissProgressDialog();
                        myCallBack.getData(null);
                    }

                });
    }



}
