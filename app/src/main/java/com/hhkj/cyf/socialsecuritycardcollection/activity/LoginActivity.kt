package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.bean.LoginBean
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant
import com.hhkj.cyf.socialsecuritycardcollection.tools.NetTools
import com.hhkj.cyf.socialsecuritycardcollection.tools.SPTools
import com.hhkj.cyf.socialsecuritycardcollection.url.Urls
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
        initClick()
    }

    private fun initView() {
        setPwEt(et_pw, switch0)
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
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
//            net_login()
        }
    }

    private fun net_login() {
        val map = hashMapOf<String, String>()
        map["phone"] = et_username.text.toString()
        map["password"] = et_pw.text.toString()
        NetTools.net(map, Urls().auth_login, this) { response ->
            Log.e("zj", "net_login = " + response.data)
            val loginBean = Gson().fromJson<LoginBean>(response.data, LoginBean::class.java)
            Log.e("zj", "net_login11 = " + loginBean.toString())

            SPTools.put(this@LoginActivity, Constant.HEADPHOTO, Urls.fileAccessHost + loginBean.headPhoto)
            SPTools.put(this@LoginActivity, Constant.PHONE, "" + loginBean.phone)
            SPTools.put(this@LoginActivity, Constant.SHEBAOURL, "" + loginBean.shebaoUrl)
            SPTools.put(this@LoginActivity, Constant.USERNAME, "" + loginBean.userName)
            SPTools.put(this@LoginActivity, Constant.YIBAOURL, "" + loginBean.yibaoUrl)
            SPTools.put(this@LoginActivity, Constant.ZKJDURL, "" + loginBean.zhikajinduUrl)

            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }

}
