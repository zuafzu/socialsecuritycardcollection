package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.os.Bundle
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant
import com.hhkj.cyf.socialsecuritycardcollection.tools.NetTools
import com.hhkj.cyf.socialsecuritycardcollection.tools.SPTools
import com.hhkj.cyf.socialsecuritycardcollection.tools.ToastUtil
import com.hhkj.cyf.socialsecuritycardcollection.url.Urls
import kotlinx.android.synthetic.main.activity_change_pw.*
import kotlinx.android.synthetic.main.activity_edit_user_name.*
import org.jetbrains.anko.toast

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
        setPwEt(et_pw, switch0)
        setPwEt(et_pw1, switch1)
        setPwEt(et_pw2, switch2)
    }

    private fun initClick() {
        btn_submit.setOnClickListener {
            if (et_pw2.text.toString() != et_pw1.text.toString()) {
//                toast("新密码与确认密码不一致")
                ToastUtil.showToastMessage(this@ChangePwActivity,"新密码与确认密码不一致",R.mipmap.toast_notice)
                return@setOnClickListener
            }
            net_updatePassword()
        }
    }

    private fun net_updatePassword() {
        val map = hashMapOf<String, String>()
        map["phone"] = "" + SPTools[this@ChangePwActivity, Constant.PHONE, ""]
        map["oldPassword"] = et_pw.text.toString()
        map["newPassword"] = et_pw1.text.toString()
        NetTools.net(map, Urls().updatePassword, this) { response ->
//            toast("" + response.msg)
            ToastUtil.showToastMessage(this@ChangePwActivity,"" + response.msg,R.mipmap.toast_ok)

            finish()

        }
    }

}
