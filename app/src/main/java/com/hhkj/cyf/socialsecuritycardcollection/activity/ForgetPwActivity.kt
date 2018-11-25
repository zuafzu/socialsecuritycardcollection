package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.os.Bundle
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import kotlinx.android.synthetic.main.activity_forget_pw.*

class ForgetPwActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pw)
        initView()
        initClick()
    }

    private fun initView() {
        setTextTitle("找回密码")
        setLeftBtn(true)
    }

    private fun initClick() {
        btn_submit.setOnClickListener {
            finish()
        }
    }
}
