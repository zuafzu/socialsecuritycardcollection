package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
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
