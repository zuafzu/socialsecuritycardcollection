package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.url.Urls
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initView()
        initClick()
    }

    private fun initView() {
        setTextTitle("创建账户")
        setLeftBtn(true)
    }

    private fun initClick() {
        btn_register.setOnClickListener {

        }
        tv_agreement.setOnClickListener {
            val mIntent = Intent(this,WebActivity::class.java)
            mIntent.putExtra("title","用户服务协议")
            mIntent.putExtra("url", Urls.url_register_agreement)
            startActivity(mIntent)
        }
    }
}
