package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.bean.CommitBean
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant
import com.hhkj.cyf.socialsecuritycardcollection.tools.NetTools
import com.hhkj.cyf.socialsecuritycardcollection.tools.SPTools
import com.hhkj.cyf.socialsecuritycardcollection.tools.Validator
import com.hhkj.cyf.socialsecuritycardcollection.url.Urls
import kotlinx.android.synthetic.main.activity_search_id.*
import org.jetbrains.anko.toast
import org.json.JSONObject

class SearchIdActivity : BaseActivity() {

    private var type = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_id)
        initView()
        initClick()
    }

    private fun initView() {
        setLeftBtn(true)
        setTextTitle(intent.getStringExtra("title"))
        type = intent.getIntExtra("type", 0)
        setRightBtn(true, "帮助", View.OnClickListener {
            val mIntent = Intent(this, WebActivity::class.java)
            mIntent.putExtra("title", "操作指南")
            mIntent.putExtra("url", Urls.url_help_collect)
            startActivity(mIntent)
        })
    }

    private fun initClick() {
        btn_check.setOnClickListener {

//            val mIntent = Intent(this, Collect1Activity::class.java)
//            mIntent.putExtra("type", type)
//            mIntent.putExtra("title", intent.getStringExtra("title"))
//            startActivity(mIntent)
            if (Validator.isIDCard(et_idCard.text.toString())){
//                net_getWriteCount()
                net_idCardQuery()
            }else{
                toast("身份证格式不正确")
            }
        }
    }
    private fun net_idCardQuery() {
        val map = hashMapOf<String, String>()
        map["phone"] = "" + SPTools[this@SearchIdActivity, Constant.PHONE, ""]
        map["idCard"] = "" + et_idCard.text.toString()
        NetTools.net(map, Urls().idCardQuery, this) { response ->
            Log.e("zj", "net_idCardQuery = " + response.data)

            val mIntent = Intent(this, Collect1Activity::class.java)
            mIntent.putExtra("type", type)
            mIntent.putExtra("title", intent.getStringExtra("title"))
            startActivity(mIntent)

        }
    }

}
