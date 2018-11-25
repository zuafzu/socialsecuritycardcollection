package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.adapter.QueryListAdapter
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.bean.QueryBean
import kotlinx.android.synthetic.main.activity_query_list.*

class QueryListActivity : BaseActivity() {

    private var queryList: MutableList<QueryBean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query_list)
        initView()
        setData()
    }

    private fun initView() {
        setLeftBtn(true)
        setTextTitle("查询")
        listView.setOnItemClickListener { adapterView, view, i, l ->
            val mIntent = Intent(this, CollectActivity::class.java)
            mIntent.putExtra("title","查询")
            startActivity(mIntent)
        }
    }

    private fun setData() {
        queryList = ArrayList()
        for (i in 0 until 4) {
            val queryBean = QueryBean("$i", "张三$i", "220102190001011234", "" + i % 4, "状态描述" + i % 4, "" + i % 4, "制卡进度描述" + i % 4)
            queryList!!.add(queryBean)
        }
        listView.adapter = QueryListAdapter(queryList, this)
    }

}
