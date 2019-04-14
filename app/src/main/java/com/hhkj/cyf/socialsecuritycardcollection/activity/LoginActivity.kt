package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.bean.LoginBean
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant
import com.hhkj.cyf.socialsecuritycardcollection.tools.*
import com.hhkj.cyf.socialsecuritycardcollection.url.Urls
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

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

    override fun onResume() {
        super.onResume()
        Log.e("zj","onResume")
        et_username.setText(SPTools[this@LoginActivity,Constant.PHONE,""].toString())
        if (!et_username.text.isEmpty()){
            et_username.setSelection(et_username.text.length)
        }
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
            if (et_username.text.toString().trim().isEmpty()){
//                toast("手机号不能为空")
                ToastUtil.showToastMessage(this@LoginActivity,"手机号不能为空",R.mipmap.toast_notice)

                return@setOnClickListener
            }
            if (!PhoneTools.isMobile(et_username.text.toString())){
//                toast("手机号格式不正确")
                ToastUtil.showToastMessage(this@LoginActivity,"手机号格式不正确",R.mipmap.toast_notice)
                return@setOnClickListener
            }
            if (et_pw.text.toString().trim().isEmpty()){
//                toast("密码不能为空")
                ToastUtil.showToastMessage(this@LoginActivity,"密码不能为空",R.mipmap.toast_notice)
                return@setOnClickListener
            }

            net_login()

//            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//            finish()
        }
    }

    private fun net_login() {
        val map = hashMapOf<String, String>()
        map["phone"] = et_username.text.toString()
        map["password"] = et_pw.text.toString()
        NetTools.net(map, Urls().auth_login, this) { response ->
            Log.e("zj", "net_login = " + response.data)
            val loginBean = Gson().fromJson<LoginBean>(response.data, LoginBean::class.java)
            SPTools.put(this@LoginActivity, Constant.HEADPHOTO, Urls.fileAccessHost + loginBean.headPhoto)
            SPTools.put(this@LoginActivity, Constant.PHONE, "" + loginBean.phone)
            SPTools.put(this@LoginActivity, Constant.SHEBAOURL, "" + loginBean.shebaoUrl)
            SPTools.put(this@LoginActivity, Constant.USERNAME, "" + loginBean.userName)
            SPTools.put(this@LoginActivity, Constant.YIBAOURL, "" + loginBean.yibaoUrl)
            SPTools.put(this@LoginActivity, Constant.ZKJDURL, "" + loginBean.zhikajinduUrl)
            SPTools.put(this@LoginActivity, Constant.TOKEN, "" + loginBean.token)

            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }

}
