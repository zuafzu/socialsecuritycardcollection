package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant
import com.hhkj.cyf.socialsecuritycardcollection.tools.*
import com.hhkj.cyf.socialsecuritycardcollection.url.Urls
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseActivity() {

    private var timeCount: MyCountTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initView()
        initClick()
    }

    private fun initView() {
        setTextTitle("创建账户")
        setLeftBtn(true)
        setPwEt(et_pw,switch0)
        timeCount = MyCountTimer(tv_code, 0xffffffff.toInt(), 0xffffffff.toInt())//传入了文字颜色值

    }

    private fun initClick() {
        tv_code.setOnClickListener {
            if (et_phone.text.toString() == "") {
//                toast("手机号不能为空")
                ToastUtil.showToastMessage(this@RegisterActivity, "手机号不能为空！",R.mipmap.toast_notice)

                return@setOnClickListener
            }
            if (!PhoneTools.isMobile(et_phone.text.toString())) {
//                toast("请输入合法手机号")
                ToastUtil.showToastMessage(this@RegisterActivity, "请输入合法手机号！",R.mipmap.toast_notice)
                return@setOnClickListener
            }

            net_common_verCode()
        }

        btn_register.setOnClickListener {
            if (et_phone.text.toString() == "") {
//                toast("手机号不能为空")
                ToastUtil.showToastMessage(this@RegisterActivity,"手机号不能为空",R.mipmap.toast_notice)
                return@setOnClickListener
            }
            if (!PhoneTools.isMobile(et_phone.text.toString())) {
//                toast("请输入合法手机号")
                ToastUtil.showToastMessage(this@RegisterActivity,"请输入合法手机号",R.mipmap.toast_notice)
                return@setOnClickListener
            }
            if (et_code.text.toString() == "") {
//                toast("验证码不能为空")
                ToastUtil.showToastMessage(this@RegisterActivity,"验证码不能为空",R.mipmap.toast_notice)
                return@setOnClickListener
            }
            if (et_pw.text.toString() == "") {
//                toast("密码不能为空")
                ToastUtil.showToastMessage(this@RegisterActivity,"密码不能为空",R.mipmap.toast_notice)
                return@setOnClickListener
            }
            if (!checkbox.isChecked) {
//                toast("请阅读用户服务协议")
                ToastUtil.showToastMessage(this@RegisterActivity,"请阅读用户服务协议",R.mipmap.toast_notice)
                return@setOnClickListener
            }
            net_register()
        }
        tv_agreement.setOnClickListener {

            val mIntent = Intent(this,WebActivity::class.java)
            mIntent.putExtra("title","用户服务协议")
            mIntent.putExtra("url", Urls().url_register_agreement)
            startActivity(mIntent)
        }
    }

    private fun net_register() {
        val map = hashMapOf<String, String>()
        map["phone"] = "" + et_phone.text.toString()
        map["password"] = et_pw.text.toString()
        map["code"] = "" + et_code.text.toString()
        NetTools.net(map, Urls().register, this) { response ->
            SPTools.put(this@RegisterActivity,Constant.PHONE,"" + et_phone.text.toString())
//            toast(response.msg!!)
            ToastUtil.showToastMessage(this@RegisterActivity,response.msg!!,R.mipmap.toast_ok)
            finish()
        }
    }
    private fun net_common_verCode() {
        val map = hashMapOf<String, String>()
        map.put("phone", et_phone.text.toString())
        map.put("type", "0")
        NetTools.net(map, Urls().getCode, this) { response ->
            timeCount!!.start()
//            toast(response.msg!!)
            ToastUtil.showToastMessage(this@RegisterActivity,response.msg!!,R.mipmap.toast_ok)

        }
    }
}
