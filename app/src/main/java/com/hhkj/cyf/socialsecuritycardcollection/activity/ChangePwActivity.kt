package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.os.Bundle
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import kotlinx.android.synthetic.main.activity_change_pw.*

class ChangePwActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pw)
        initView()
        initClick()
    }

    private fun initView() {
        setTextTitle("修改密码")
        setLeftBtn(true)
        setPwEt(et_pw,switch0)
        setPwEt(et_pw1,switch1)
        setPwEt(et_pw2,switch2)
    }

    private fun initClick() {
        btn_submit.setOnClickListener {
            finish()
        }
    }

}
