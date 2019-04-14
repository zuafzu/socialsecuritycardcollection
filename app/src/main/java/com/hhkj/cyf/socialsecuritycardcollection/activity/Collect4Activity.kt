package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.app.MyApplication
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.bean.CommitBean
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant
import com.hhkj.cyf.socialsecuritycardcollection.tools.*
import com.hhkj.cyf.socialsecuritycardcollection.url.Urls
import kotlinx.android.synthetic.main.activity_collect4.*
import org.jetbrains.anko.toast

class Collect4Activity : BaseActivity() {
    private var commitBean: CommitBean? = null
    private var isModify = 0//0录入，1修改

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect4)
        initView()
    }

    private fun initView() {
        setLeftBtn(true)
        setTextTitle(intent.getStringExtra("title"))
        isModify = intent.getIntExtra("isModify", 0)

        commitBean = intent.getSerializableExtra("commitBean") as CommitBean?

        if (isModify == 0){

        }else{
            et_name.setText(commitBean!!.dbr_xm)
            et_idCard.setText(commitBean!!.dbr_sfzhm)
            et_phone.setText(commitBean!!.dbr_lxdh)
        }

        btn_submit.setOnClickListener {
            if (et_name.text.toString().trim().isEmpty()){
                ToastUtil.showToastMessage(this@Collect4Activity, "姓名不能为空",R.mipmap.toast_notice)

                return@setOnClickListener
            }
            if (!Validator.isIDCard(et_idCard.text.toString().trim())){
                ToastUtil.showToastMessage(this@Collect4Activity, "身份证号格式错误",R.mipmap.toast_notice)

                return@setOnClickListener
            }
            if (!PhoneTools.isMobile(et_phone.text.toString())){
                ToastUtil.showToastMessage(this@Collect4Activity, "联系手机格式错误",R.mipmap.toast_notice)

                return@setOnClickListener
            }
            commitBean!!.dbr_xm = et_name.text.toString()
            commitBean!!.dbr_sfzhm = et_idCard.text.toString()
            commitBean!!.dbr_lxdh = et_phone.text.toString()
            net_addOrUpdate(commitBean!!)

        }
    }

    private fun net_addOrUpdate(commitBean: CommitBean) {
        val jsonBean = Gson().toJson(commitBean)
        val map = hashMapOf<String, String>()
        map["phone"] = "" + SPTools[this@Collect4Activity, Constant.PHONE, ""]
        map["cbInsured"] = ""+jsonBean

        Log.e("zj", "jsonBean = " + jsonBean)
        NetTools.net(map, Urls().addOrUpdate, this) { response ->
            Log.e("zj", "addOrUpdate = " + response.data)
            ToastUtil.showToastMessage(this@Collect4Activity, response.msg.toString(),R.mipmap.toast_ok)

            for (i in 0 until MyApplication.getActivies().size) {
                MyApplication.getActivies()[i].finish()
            }
            startActivity(Intent(this@Collect4Activity, MainActivity::class.java))
        }
    }

}
