package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.adapter.LawsGridAdapter
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.bean.HomeBean
import kotlinx.android.synthetic.main.activity_laws_list2.*
import java.util.ArrayList

class LawsList2Activity : BaseActivity() {
    private var homeModel: MutableList<HomeBean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laws_list2)
        initView()
    }

    private fun initView() {
        setTextTitle(intent.getStringExtra("title"))
        setLeftBtn(true)
        var pos = intent.getStringExtra("pos")
        setData(pos)
        gridView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this@LawsList2Activity, LawsListActivity::class.java)
            intent.putExtra("title", homeModel!![i].modelName)
            intent.putExtra("id", homeModel!![i].modelId)
            intent.putExtra("pos",pos)
            startActivity(intent)
        }
    }

    private fun setData(pos: String) {
        homeModel = ArrayList()
        if (pos == "0") {
            val homeBean1 = HomeBean(R.mipmap.ic_tab2_1_1, "1", "被征地农民养老保险")
            val homeBean2 = HomeBean(R.mipmap.ic_tab2_1_2, "2", "城乡居民养老保险")
            val homeBean3 = HomeBean(R.mipmap.ic_tab2_1_3, "3", "城镇企业职工基本养老保险")
            val homeBean4 = HomeBean(R.mipmap.ic_tab2_1_4, "4", "机关事业单位养老保险")
            val homeBean5 = HomeBean(R.mipmap.ic_tab2_1_5, "5", "失业保险")
            val homeBean6 = HomeBean(R.mipmap.ic_tab2_1_6, "6", "其他")
            homeModel!!.add(homeBean1)
            homeModel!!.add(homeBean2)
            homeModel!!.add(homeBean3)
            homeModel!!.add(homeBean4)
            homeModel!!.add(homeBean5)
            homeModel!!.add(homeBean6)
        } else if (pos == "1") {
            val homeBean1 = HomeBean(R.mipmap.ic_tab2_2_1, "1", "工伤保险")
            val homeBean2 = HomeBean(R.mipmap.ic_tab2_2_2, "2", "居民医疗保险")
            val homeBean3 = HomeBean(R.mipmap.ic_tab2_2_3, "3", "生育保险")
            val homeBean4 = HomeBean(R.mipmap.ic_tab2_2_4, "4", "职工医疗保险")
            homeModel!!.add(homeBean1)
            homeModel!!.add(homeBean2)
            homeModel!!.add(homeBean3)
            homeModel!!.add(homeBean4)
        }

        gridView.adapter = LawsGridAdapter(homeModel, this@LawsList2Activity)
    }
}
