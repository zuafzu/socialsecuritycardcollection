package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.adapter.HomeAdapter
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.bean.HomeBean
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant
import com.hhkj.cyf.socialsecuritycardcollection.tools.NetTools
import com.hhkj.cyf.socialsecuritycardcollection.tools.SPTools
import com.hhkj.cyf.socialsecuritycardcollection.url.Urls
import kotlinx.android.synthetic.main.activity_select_collect.*
import org.jetbrains.anko.toast
import org.json.JSONObject
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
        setTextTitle(intent.getStringExtra("title"))
        gridView.setOnItemClickListener { adapterView, view, i, l ->
            when (i) {
                0 -> {
                    net_getWriteCount(0)

//                    val mIntent = Intent(this, SearchIdActivity::class.java)
//                    mIntent.putExtra("title", homeModel!![i].modelName)
//                    mIntent.putExtra("type", 0)
//                    startActivity(mIntent)
                }
                1 -> {
                    net_getWriteCount(1)

//                    val mIntent = Intent(this, SearchIdActivity::class.java)
//                    mIntent.putExtra("title", homeModel!![i].modelName)
//                    mIntent.putExtra("type", 1)
//                    startActivity(mIntent)
                }
                2 -> {
                    startActivity(Intent(this, QueryListActivity::class.java))
                }
            }
        }
    }

    private fun setData() {
        homeModel = ArrayList()
        val homeBean1 = HomeBean(R.mipmap.grbl, "1", "个人办理")
        val homeBean2 = HomeBean(R.mipmap.qsdb, "2", "亲属代办")
        val homeBean3 = HomeBean(R.mipmap.cjjd, "3", "采集进度查询")
        homeModel!!.add(homeBean1)
        homeModel!!.add(homeBean2)
        homeModel!!.add(homeBean3)
        gridView.adapter = HomeAdapter(homeModel, this)
    }

    private fun net_getWriteCount(i:Int) {
        val map = hashMapOf<String, String>()
        map["phone"] = "" + SPTools[this@SelectCollectActivity, Constant.PHONE, ""]
        NetTools.net(map, Urls().getWriteCount, this) { response ->
            Log.e("zj", "getWriteCount = " + response.data)

            var jsonObj = JSONObject(response.data)
            var nowCount = jsonObj.getString("nowCount")
            var maxCount = jsonObj.getString("maxCount")
            if (Integer.parseInt(nowCount) < Integer.parseInt(maxCount)) {
                when (i) {
                    0 -> {
                        if (nowCount == "0"){
                            val mIntent = Intent(this, SearchIdActivity::class.java)
                            mIntent.putExtra("title", homeModel!![i].modelName)
                            mIntent.putExtra("type", 0)
                            startActivity(mIntent)
                        }else{
                            toast("您已办理过个人办理，无法再次办理")
                        }
                    }
                    1 -> {
                        if (nowCount != "0"){
                            val mIntent = Intent(this, SearchIdActivity::class.java)
                            mIntent.putExtra("title", homeModel!![i].modelName)
                            mIntent.putExtra("type", 1)
                            startActivity(mIntent)
                        }else{
                            toast("请先进行个人办理")
                        }
                    }
                }
            } else {
                toast("录入次数已达最大限制 $maxCount")
            }
        }
    }
}
