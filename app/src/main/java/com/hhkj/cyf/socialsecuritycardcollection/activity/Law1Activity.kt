package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import kotlinx.android.synthetic.main.activity_law1.*

class Law1Activity : BaseActivity() {
//    private var mAdapter:LawsAdapter?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_law1)
        initView()
    }

    private fun initView() {
        setLeftBtn(true)
        setTextTitle("")
//        val array = intArrayOf(R.mipmap.img2,R.mipmap.img3,R.mipmap.img4,R.mipmap.img5,R.mipmap.img6)
////        val array = intArrayOf(R.drawable.ic_dot_selected)
//
//        mAdapter = LawsAdapter(array,this@Law1Activity)
//        listView.adapter = mAdapter

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
