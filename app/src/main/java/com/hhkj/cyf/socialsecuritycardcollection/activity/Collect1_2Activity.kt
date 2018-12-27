package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.bean.DictionaryBean
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant
import kotlinx.android.synthetic.main.activity_collect1_2.*
import org.jetbrains.anko.toast
import java.util.ArrayList

class Collect1_2Activity : BaseActivity() {

    private var type = 0//0个人，1采集

    private var dictionaryBean: DictionaryBean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect1_2)
        initView()
        initClick()
    }

    private fun initView() {
        setLeftBtn(true)
        setTextTitle(intent.getStringExtra("title"))
        type = intent.getIntExtra("type", 0)
        dictionaryBean = intent.getSerializableExtra("dictionaryBean") as DictionaryBean?
        tv_cardType2.text =dictionaryBean!!.zhrlxMap[0].name
    }

    private fun initClick() {
        ll_cardType2.setOnClickListener {
            SelectItemActivity.startSelectItem(this, dictionaryBean!!.zhrlxMap, "", object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_cardType2.text = name
                }
            })
        }
        btn_next.setOnClickListener {
            if (et_jhrIdCard.text.isEmpty()){
                toast("请输入监护人身份证号")
                return@setOnClickListener
            }
            if (et_jhrName.text.isEmpty()){
                toast("请输入监护人身份证号")
                return@setOnClickListener
            }
            val mIntent = Intent(this, Collect2Activity::class.java)
            mIntent.putExtra("title", intent.getStringExtra("title"))
            mIntent.putExtra("dictionaryBean", dictionaryBean)
            mIntent.putExtra("type", type)
            startActivity(mIntent)
        }
    }

}
