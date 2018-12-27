package com.hhkj.cyf.socialsecuritycardcollection.app

import android.app.Activity
import android.app.Application
import android.content.Context

import com.tencent.bugly.crashreport.CrashReport

class MyApplication : Application() {
    companion object {
        // 初始化activity数组
        private var  activities = ArrayList<Activity>()
        fun getActivies() = activities!!
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        CrashReport.initCrashReport(applicationContext, "68da0e5c02", false)
        context = applicationContext

    }
}
