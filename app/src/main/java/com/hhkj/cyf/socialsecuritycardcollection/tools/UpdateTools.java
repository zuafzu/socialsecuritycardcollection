package com.hhkj.cyf.socialsecuritycardcollection.tools;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hhkj.cyf.socialsecuritycardcollection.R;
import com.hhkj.cyf.socialsecuritycardcollection.activity.BootPageActivity;
import com.hhkj.cyf.socialsecuritycardcollection.activity.WelcomeActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.request.RequestCall;

import java.io.File;

import okhttp3.Call;

/**
 * Created by caoyingfu on 16/3/18.
 */
public class UpdateTools {

    public static void downLoadAPK(final Context cotext, String path, final boolean isNeedUp) {
        try {
            String apkPath = Environment.getExternalStorageDirectory() + "/sbkcj.apk";
            File f = new File(apkPath);
            if (f.exists()) {
                f.delete();
            }
            final RequestCall requestCall = OkHttpUtils.get().url(path).build();
            final View view = LayoutInflater.from(cotext).inflate(R.layout.dialog_updata, null);
            view.setTag(false);
            final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            progressBar.setMax(100);
            progressBar.setProgress(0);
            final TextView tv_size = (TextView) view.findViewById(R.id.tv_size);
            final TextView tv_allsize = (TextView) view.findViewById(R.id.tv_allsize);
            final CustomDialog.Builder builder = new CustomDialog.Builder(cotext);
            builder.setCancel(false);
            builder.setTitle("正在更新中");
            builder.setContentView(view);
            if (isNeedUp) {
                // 强制更新

            } else {
                // 非强制更新
                builder.setCenterButton("取消更新",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                view.setTag(true);
                                dialog.dismiss();
                                requestCall.cancel();
                                if (cotext.getClass().toString().contains("WelcomeActivity")) {
                                    // 欢迎界面
                                    if (isNeedUp) {
                                        // 强制更新

                                    } else {
                                        // 非强制更新
                                        cotext.startActivity(new Intent(cotext, BootPageActivity.class));
                                        ((WelcomeActivity) cotext).finish();
                                    }
                                } else if (cotext.getClass().toString().contains("MainActivity")) {
                                    // 主界面（不需要操作）
                                }
                            }
                        }, R.drawable.background_btn3);
            }
            final Dialog dialog = builder.create();
            dialog.show();
            requestCall.execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "sbkcj.apk") {
                @Override
                public void onError(Call call, Exception e, int id) {
                    dialog.dismiss();
                    if ((boolean) view.getTag()) {
                        ToastUtil.showToastMessage(cotext,"取消下载！",R.mipmap.toast_notice);
                    } else {
                        ToastUtil.showToastMessage(cotext,"下载失败！",R.mipmap.toast_notice);
//                        Toast.makeText(cotext, "下载失败！", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onResponse(File file, int id) {
                    dialog.dismiss();
                    ToastUtil.showToastMessage(cotext,"下载完成！",R.mipmap.toast_ok);
//                    Toast.makeText(cotext, "下载完成！", Toast.LENGTH_SHORT).show();
                    try {
                        Intent intent = new Intent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setAction(Intent.ACTION_VIEW);
                        String type = getMIMEType(file);
                        intent.setDataAndType(Uri.fromFile(file), type);
                        cotext.startActivity(intent);
                        android.os.Process.killProcess(android.os.Process.myPid());
                    } catch (Exception e) {
                        Log.e("cyf", "e : " + e.toString());
                    }
                }

                @Override
                public void inProgress(float progress, long total, int id) {
                    super.inProgress(progress, total, id);
                    progressBar.setProgress((int) (progress * 100));
                    tv_allsize.setText((float) (Math.round((((float) total) / 1024 / 1024) * 100)) / 100 + "M");
                    tv_size.setText((float) (Math.round((total * progress / 1024 / 1024) * 100)) / 100 + "M");
                }

            });
        } catch (Exception e) {
            Log.e("zj", "e = " + e.toString());

        }
    }

    private static String getMIMEType(File f) {
        String type = "";
        String fName = f.getName();
        String end = fName
                .substring(fName.lastIndexOf(".") + 1, fName.length())
                .toLowerCase();
        if (end.equals("m4a") || end.equals("mp3") || end.equals("mid")
                || end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
            type = "audio";
        } else if (end.equals("3gp") || end.equals("mp4")) {
            type = "video";
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png")
                || end.equals("jpeg") || end.equals("bmp")) {
            type = "image";
        } else if (end.equals("apk")) {
            type = "application/vnd.android.package-archive";
        } else {
            type = "*";
        }
        if (end.equals("apk")) {
        } else {
            type += "/*";
        }
        return type;
    }

}

