package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant
import com.hhkj.cyf.socialsecuritycardcollection.tools.NetTools
import com.hhkj.cyf.socialsecuritycardcollection.tools.SPTools
import com.hhkj.cyf.socialsecuritycardcollection.tools.ToastUtil
import com.hhkj.cyf.socialsecuritycardcollection.url.Urls
import kotlinx.android.synthetic.main.activity_feedback.*

class FeedbackActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        initView()
        initClick()
    }

    private fun initView() {
        setTextTitle("意见反馈")
        setLeftBtn(true)
    }

    private fun initClick() {
        btn_submit.setOnClickListener {
            net_suggest()
        }
    }

    private fun net_suggest() {
        val map = hashMapOf<String, String>()
        map["phone"] = "" + SPTools[this@FeedbackActivity, Constant.PHONE, ""]
        map["message"] = et_content.text.toString()
        NetTools.net(map, Urls().suggest, this) { response ->
            ToastUtil.showToastMessage(this@FeedbackActivity,response.msg,R.mipmap.toast_ok)
            hideSoftInput()
            finish()
        }
    }


}
