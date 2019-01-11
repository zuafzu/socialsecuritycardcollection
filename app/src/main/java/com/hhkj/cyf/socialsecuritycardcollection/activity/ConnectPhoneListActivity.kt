package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.adapter.ConnectPhoneAdapter
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant
import kotlinx.android.synthetic.main.activity_laws_list.*

class ConnectPhoneListActivity : BaseActivity() {

    private var phoneList: MutableList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connect_phone_list)
        initView()
        setData()
    }

    private fun initView() {
        setTextTitle(intent.getStringExtra("title"))
        setLeftBtn(true)
        listView.setOnItemClickListener { adapterView, view, i, l ->
            val normalDialog = AlertDialog.Builder(this@ConnectPhoneListActivity)
            normalDialog.setTitle("提示")
            normalDialog.setMessage("确认拨打电话吗？")
            normalDialog.setPositiveButton("确定"
            ) { dialog, which ->
                dialog.dismiss()
                callPhone(phoneList!![i])
            }
            normalDialog.setNegativeButton("取消"
            ) { dialog, which -> dialog.dismiss() }
            // 显示
            normalDialog.show()
        }
    }

    private fun setData() {
        phoneList = ArrayList()
        val homeBean1 = "85858607"
        val homeBean2 = "80808301"
        val homeBean3 = "81668722"
        phoneList!!.add(homeBean1)
        phoneList!!.add(homeBean2)
        phoneList!!.add(homeBean3)
        listView.adapter = ConnectPhoneAdapter(phoneList, this)
    }
}
