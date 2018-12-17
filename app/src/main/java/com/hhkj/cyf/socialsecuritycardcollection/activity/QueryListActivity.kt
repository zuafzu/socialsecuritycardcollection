package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.adapter.QueryListAdapter
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.bean.QueryBean
import kotlinx.android.synthetic.main.activity_query_list.*

class QueryListActivity : BaseActivity(), QueryListAdapter.OnMyClickListener {

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

    override fun clickBtn(index: Int) {
        // 0保存成功，1上传成功，2上传失败，3异地
        when (queryList!![index].state) {
            "2" -> {
                val mIntent = Intent(this, Collect1Activity::class.java)
                mIntent.putExtra("title", "个人办理")
                mIntent.putExtra("type", 0)
                startActivity(mIntent)
            }
            "0", "1", "3" -> {
                // 生成本地html

                // 加载本地html
                val mIntent = Intent(this, CollectWebActivity::class.java)
                mIntent.putExtra("title", "查看详情")
                mIntent.putExtra("url", "file:///android_asset/index.html")
                mIntent.putExtra("state", queryList!![index].state)
                startActivity(mIntent)
            }
        }
    }

}
