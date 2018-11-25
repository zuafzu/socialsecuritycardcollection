package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.adapter.LawsListAdapter
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.bean.LawsListBean
import kotlinx.android.synthetic.main.activity_laws_list.*


class LawsListActivity : BaseActivity() {

    private var lawsList: MutableList<LawsListBean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laws_list)
        initView()
        setData()
    }

    private fun initView() {
        setTextTitle(intent.getStringExtra("title"))
        setLeftBtn(true)
        listView.setOnItemClickListener { adapterView, view, i, l ->
            val mIntent = Intent(this, WebActivity::class.java)
            mIntent.putExtra("title", lawsList!![i].title)
            mIntent.putExtra("url", lawsList!![i].url)
            startActivity(mIntent)
        }
    }

    private fun setData() {
        lawsList = ArrayList()
        for (i in 0 until 30) {
            val homeBean1 = LawsListBean("测试政策指南$i", "https://www.baidu.com/")
            lawsList!!.add(homeBean1)
        }
        listView.adapter = LawsListAdapter(lawsList, this)
    }

}
