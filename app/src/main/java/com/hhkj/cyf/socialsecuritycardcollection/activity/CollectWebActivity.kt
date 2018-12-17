package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import kotlinx.android.synthetic.main.activity_collect_web.*

class CollectWebActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect_web)
        initView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initView() {
        setTextTitle(intent.getStringExtra("title"))
        setLeftBtn(true)
        if (intent.getStringExtra("state") == "0") {
            setRightBtn(true, "修改", View.OnClickListener {
                val mIntent = Intent(this, Collect1Activity::class.java)
                mIntent.putExtra("title", "个人办理")
                mIntent.putExtra("type", 0)
                startActivity(mIntent)
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
        webView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
        webView.settings.loadWithOverviewMode = true

        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = WebViewClient()
        webView.loadUrl(intent.getStringExtra("url"))

    }

}
