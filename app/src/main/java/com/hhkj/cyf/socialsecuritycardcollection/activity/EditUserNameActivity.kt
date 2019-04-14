package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.os.Bundle
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant
import com.hhkj.cyf.socialsecuritycardcollection.tools.NetTools
import com.hhkj.cyf.socialsecuritycardcollection.tools.SPTools
import com.hhkj.cyf.socialsecuritycardcollection.tools.ToastUtil
import com.hhkj.cyf.socialsecuritycardcollection.url.Urls
import kotlinx.android.synthetic.main.activity_edit_user_name.*
import kotlinx.android.synthetic.main.activity_feedback.*
import org.jetbrains.anko.toast

class EditUserNameActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user_name)
        initView()
    }

    private fun initView() {
        setLeftBtn(true)
        setTextTitle("修改昵称")

        btn_save.setOnClickListener {
            if (et_nickName.text.toString().isEmpty()){
//                toast("请输入要修改的昵称")
                ToastUtil.showToastMessage(this@EditUserNameActivity, "请输入要修改的昵称",R.mipmap.toast_notice)
                return@setOnClickListener
            }
            net_updateNickName()
        }
    }

    private fun net_updateNickName() {
        val map = hashMapOf<String, String>()
        map["phone"] = "" + SPTools[this@EditUserNameActivity, Constant.PHONE, ""]
        map["nickName"] = et_nickName.text.toString()
        NetTools.net(map, Urls().updateNickName, this) { response ->
            SPTools.put(this@EditUserNameActivity, Constant.USERNAME, et_nickName.text.toString())
//            toast("" + response.msg)
            ToastUtil.showToastMessage(this@EditUserNameActivity, response.msg,R.mipmap.toast_ok)
            hideSoftInput()
            finish()
        }
    }

}
