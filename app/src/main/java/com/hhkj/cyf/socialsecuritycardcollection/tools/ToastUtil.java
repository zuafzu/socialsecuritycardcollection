package com.hhkj.cyf.socialsecuritycardcollection.tools;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hhkj.cyf.socialsecuritycardcollection.R;

public class ToastUtil {
    public static void showToastMessage(Context context, String message,int resId) {

        try {
            //自定义Toast控件
            View toastView = LayoutInflater.from(context).inflate(R.layout.toast_clear_layout, null);
            LinearLayout relativeLayout = toastView.findViewById(R.id.toast_linear);

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) dpToPx(context, 130), (int) dpToPx(context, 130));
            relativeLayout.setLayoutParams(layoutParams);
            TextView textView = toastView.findViewById(R.id.tv_toast_clear);
            ImageView iv_toast = toastView.findViewById(R.id.iv_toast);
            textView.setText(message);
            textView.setTextSize(14);

            iv_toast.setImageResource(resId);
//            iv_toast.setImageResource(R.mipmap.toast_notice);
            Toast toast = new Toast(context);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setView(toastView);
            toast.show();
        } catch (Exception e) {
        }
    }

    public static float dpToPx(Context context, int dp) {
        //获取屏蔽的像素密度系数
        float density = context.getResources().getDisplayMetrics().density;
        return dp * density;
    }

    public static float pxTodp(Context context, int px) {
        //获取屏蔽的像素密度系数
        float density = context.getResources().getDisplayMetrics().density;
        return px / density;
    }
}
