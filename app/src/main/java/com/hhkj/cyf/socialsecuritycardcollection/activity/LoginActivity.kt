package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hhkj.cyf.socialsecuritycardcollection.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
        initClick()
    }

    private fun initView() {

    }

    private fun initClick() {
        // 忘记密码
        btn_forgetPw.setOnClickListener {
            startActivity(Intent(this, ForgetPwActivity::class.java))
        }
        // 个人注册
        btn_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        // 登录
        btn_login.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}
