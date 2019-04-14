package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.os.Bundle
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant
import com.hhkj.cyf.socialsecuritycardcollection.tools.*
import com.hhkj.cyf.socialsecuritycardcollection.url.Urls
import kotlinx.android.synthetic.main.activity_forget_pw.*
import org.jetbrains.anko.toast

class ForgetPwActivity : BaseActivity() {
    private var timeCount: MyCountTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pw)
        initView()
        initClick()
    }

    private fun initView() {
        setTextTitle("找回密码")
        setLeftBtn(true)
        setPwEt(et_pw1,switch0)
        setPwEt(et_pw2,switch1)
        timeCount = MyCountTimer(tv_code, 0xffffffff.toInt(), 0xffffffff.toInt())//传入了文字颜色值

    }

    private fun initClick() {
        tv_code.setOnClickListener {
            if (et_phone.text.toString() == "") {
                ToastUtil.showToastMessage(this@ForgetPwActivity, "手机号不能为空",R.mipmap.toast_notice)

                return@setOnClickListener
            }
            if (!PhoneTools.isMobile(et_phone.text.toString())) {
                ToastUtil.showToastMessage(this@ForgetPwActivity, "请输入合法手机号",R.mipmap.toast_notice)
                return@setOnClickListener
            }
            net_common_verCode()
        }
        btn_submit.setOnClickListener {
            if (et_phone.text.toString() == "") {
                ToastUtil.showToastMessage(this@ForgetPwActivity, "手机号不能为空",R.mipmap.toast_notice)

                return@setOnClickListener
            }
            if (!PhoneTools.isMobile(et_phone.text.toString())) {
                ToastUtil.showToastMessage(this@ForgetPwActivity, "请输入合法手机号",R.mipmap.toast_notice)

                return@setOnClickListener
            }
            if (et_code.text.toString() == "") {
                ToastUtil.showToastMessage(this@ForgetPwActivity, "验证码不能为空",R.mipmap.toast_notice)

                return@setOnClickListener
            }
            if (et_pw1.text.toString() == "") {
                ToastUtil.showToastMessage(this@ForgetPwActivity, "新密码不能为空",R.mipmap.toast_notice)

                return@setOnClickListener
            }
            if (et_pw1.text.toString() != et_pw2.text.toString()){
                ToastUtil.showToastMessage(this@ForgetPwActivity, "两次输入的密码不一致",R.mipmap.toast_notice)

                return@setOnClickListener
            }
            net_forgetPassword()
        }
    }

    private fun net_forgetPassword() {
        val map = hashMapOf<String, String>()
        map["phone"] = "" + et_phone.text.toString()
        map["password"] = et_pw1.text.toString()
        map["code"] = "" + et_code.text.toString()
        NetTools.net(map, Urls().forgetPassword, this) { _ ->
            finish()
        }
    }

    private fun net_common_verCode() {
        val map = hashMapOf<String, String>()
        map.put("phone", et_phone.text.toString())
        map.put("type", "1")
        NetTools.net(map, Urls().getCode, this) { response ->
            timeCount!!.start()
            ToastUtil.showToastMessage(this@ForgetPwActivity, response.msg!!,R.mipmap.toast_ok)

        }
    }
}
