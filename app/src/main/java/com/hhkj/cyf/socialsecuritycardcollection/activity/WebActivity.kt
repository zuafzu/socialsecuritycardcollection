package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings.LayoutAlgorithm
import android.webkit.WebViewClient
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant
import kotlinx.android.synthetic.main.activity_web.*


class WebActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        initView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initView() {
        setTextTitle(intent.getStringExtra("title"))
        setLeftBtn(true)
        // 帮助界面右上角有联系我们
        if(intent.getStringExtra("title") == "操作指南"){
            setRightBtn(true,"联系我们", View.OnClickListener {
                val builder = AlertDialog.Builder(this@WebActivity)
                builder.setTitle("提示")
                builder.setMessage("确认拨打电话吗？")
                builder.setPositiveButton("确认") { p0, p1 ->
                    p0.dismiss()
                    callPhone(Constant.phone)
                }
                builder.setNegativeButton("取消"){ p0, p1 ->
                    p0.dismiss()
                }
                builder.create().show()
            })
        }
        //支持javascript
        webView.settings.javaScriptEnabled = true
        // 设置可以支持缩放
        webView.settings.setSupportZoom(true)
        // 设置出现缩放工具
        webView.settings.builtInZoomControls = true
        //扩大比例的缩放
        webView.settings.useWideViewPort = true
        //自适应屏幕
        webView.settings.layoutAlgorithm = LayoutAlgorithm.NORMAL
        webView.settings.loadWithOverviewMode = true

        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = WebViewClient()
        webView.loadUrl(intent.getStringExtra("url"))

    }
}
