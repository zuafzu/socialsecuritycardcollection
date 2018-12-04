package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant
import kotlinx.android.synthetic.main.activity_collect1_2.*

class Collect1_2Activity : BaseActivity() {

    private var type = 0//0个人，1采集

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

        tv_cardType2.text = Constant.getJhrzjlx(this)[0].name
    }

    private fun initClick() {
        ll_cardType2.setOnClickListener {
            SelectItemActivity.startSelectItem(this, Constant.getJhrzjlx(this), "", object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_cardType2.text = name
                }
            })
        }
        btn_next.setOnClickListener {
            val mIntent = Intent(this, Collect2Activity::class.java)
            mIntent.putExtra("title", intent.getStringExtra("title"))
            mIntent.putExtra("type", type)
            startActivity(mIntent)
        }
    }

}
