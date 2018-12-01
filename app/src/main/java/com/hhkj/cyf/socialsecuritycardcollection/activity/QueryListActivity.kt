package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.adapter.QueryListAdapter
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.bean.QueryBean
import kotlinx.android.synthetic.main.activity_query_list.*

class QueryListActivity : BaseActivity() , QueryListAdapter.OnMyClickListener {

    private var queryList: MutableList<QueryBean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query_list)
        initView()
        setData()
    }

    private fun initView() {
        setLeftBtn(true)
        setTextTitle("采集进度查询")
    }

    private fun setData() {
        queryList = ArrayList()
        for (i in 0 until 4) {
            val queryBean = QueryBean("$i", "张三$i", "220102190001011234", "" + i % 4, "状态描述" + i % 4)
            queryList!!.add(queryBean)
        }
        listView.adapter = QueryListAdapter(queryList, this, this)
    }

    override fun clickEdit(index: Int) {
        val mIntent = Intent(this, Collect1Activity::class.java)
        mIntent.putExtra("title", "个人办理")
        mIntent.putExtra("type", 0)
        startActivity(mIntent)
    }

    override fun clickLook(index: Int) {
        val builder = AlertDialog.Builder(this@QueryListActivity)
        builder.setTitle("提示")
        builder.setMessage("异地上报")
        builder.setPositiveButton("确认") { p0, p1 ->
            p0.dismiss()
        }
        builder.create().show()
    }

}
