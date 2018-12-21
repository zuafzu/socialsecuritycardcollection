package com.hhkj.cyf.socialsecuritycardcollection.app;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "68da0e5c02", false);
    }
}
