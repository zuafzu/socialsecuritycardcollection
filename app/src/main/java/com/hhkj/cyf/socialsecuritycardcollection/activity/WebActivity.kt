package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        initView()
    }

    private fun initView() {
        setTextTitle(intent.getStringExtra("title"))
        setLeftBtn(true)

        val ws = webView.settings
        ws.builtInZoomControls = true// 隐藏缩放按钮
        ws.useWideViewPort = true// 可任意比例缩放
        ws.loadWithOverviewMode = true// setUseWideViewPort方法设置webview推荐使用的窗口。setLoadWithOverviewMode方法是设置webview加载的页面的模式。
        ws.savePassword = true
        ws.saveFormData = true// 保存表单数据
        ws.javaScriptEnabled = true
        ws.domStorageEnabled = true
        ws.setSupportMultipleWindows(true)// 新加
        ws.pluginState = WebSettings.PluginState.ON
        //我就是没有这一行，死活不出来。MD，硬是没有人写这一句的
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = WebViewClient()
        webView.loadUrl(intent.getStringExtra("url"))
    }
}
