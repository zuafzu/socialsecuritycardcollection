package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.url.Urls
import kotlinx.android.synthetic.main.activity_search_id.*

class SearchIdActivity : BaseActivity() {

    private var type = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_id)
        initView()
        initClick()
    }

    private fun initView() {
        setLeftBtn(true)
        setTextTitle(intent.getStringExtra("title"))
        type = intent.getIntExtra("type", 0)
        setRightBtn(true,"帮助", View.OnClickListener {
            val mIntent = Intent(this,WebActivity::class.java)
            mIntent.putExtra("title","操作指南")
            mIntent.putExtra("url", Urls.url_help_collect)
            startActivity(mIntent)
        })
    }

    private fun initClick() {
        btn_check.setOnClickListener {
            val mIntent = Intent(this, Collect1Activity::class.java)
            mIntent.putExtra("type", type)
            mIntent.putExtra("title", intent.getStringExtra("title"))
            startActivity(mIntent)
        }
    }

}
