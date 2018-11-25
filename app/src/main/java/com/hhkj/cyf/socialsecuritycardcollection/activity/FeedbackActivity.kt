package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.os.Bundle
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import kotlinx.android.synthetic.main.activity_feedback.*

class FeedbackActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        initView()
        initClick()
    }

    private fun initView() {
        setTextTitle("意见反馈")
        setLeftBtn(true)
    }

    private fun initClick() {
        btn_submit.setOnClickListener {
            finish()
        }
    }

}
