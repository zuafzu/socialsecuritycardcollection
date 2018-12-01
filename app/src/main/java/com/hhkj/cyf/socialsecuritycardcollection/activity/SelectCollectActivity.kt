package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.adapter.HomeAdapter
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.bean.HomeBean
import kotlinx.android.synthetic.main.activity_select_collect.*
import java.util.*

class SelectCollectActivity : BaseActivity() {

    private var homeModel: MutableList<HomeBean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_collect)
        initView()
        setData()
    }

    private fun initView() {
        setLeftBtn(true)
        setTextTitle("社会保障卡信息采集")
        gridView.setOnItemClickListener { adapterView, view, i, l ->
            when (i) {
                0 -> {
                    val mIntent = Intent(this, SearchIdActivity::class.java)
                    mIntent.putExtra("title", homeModel!![i].modelName)
                    mIntent.putExtra("type", 0)
                    startActivity(mIntent)
                }
                1 -> {
                    val mIntent = Intent(this, SearchIdActivity::class.java)
                    mIntent.putExtra("title", homeModel!![i].modelName)
                    mIntent.putExtra("type", 1)
                    startActivity(mIntent)
                }
                2 -> {
                    startActivity(Intent(this, QueryListActivity::class.java))
                }
            }
        }
    }

    private fun setData() {
        homeModel = ArrayList<HomeBean>()
        val homeBean1 = HomeBean(R.mipmap.ic_tab2_1, "1", "个人办理")
        val homeBean2 = HomeBean(R.mipmap.ic_tab2_2, "2", "亲属代办")
        val homeBean3 = HomeBean(R.mipmap.ic_tab2_3, "3", "采集进度查询")
        homeModel!!.add(homeBean1)
        homeModel!!.add(homeBean2)
        homeModel!!.add(homeBean3)
        gridView.adapter = HomeAdapter(homeModel, this)
    }
}
