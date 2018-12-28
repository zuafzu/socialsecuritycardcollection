package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.google.gson.Gson
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.adapter.QueryListAdapter
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.bean.CommitBean
import com.hhkj.cyf.socialsecuritycardcollection.bean.QueryBean
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant
import com.hhkj.cyf.socialsecuritycardcollection.tools.AssetsTools
import com.hhkj.cyf.socialsecuritycardcollection.tools.NetTools
import com.hhkj.cyf.socialsecuritycardcollection.tools.SPTools
import com.hhkj.cyf.socialsecuritycardcollection.url.Urls
import kotlinx.android.synthetic.main.activity_query_list.*

class QueryListActivity : BaseActivity(), QueryListAdapter.OnMyClickListener {

    private var queryList: MutableList<QueryBean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query_list)
        initView()
        net_allPeopleQuery()
        setData()
    }

    private fun initView() {
        setLeftBtn(true)
        setTextTitle("采集进度查询")
    }

    private fun setData() {
        queryList = ArrayList()
        for (i in 0 until 4) {
            val queryBean = QueryBean("$i", "张三$i", "220102190001011234", "" + i % 4, "状态描述" + i % 4, "", "1")
            queryList!!.add(queryBean)
        }
        listView.adapter = QueryListAdapter(queryList, this, this)
    }

    override fun clickBtn(index: Int) {
        // 0保存成功，1上传成功，2上传失败，3异地
        when (queryList!![index].state) {
            "2" -> {
                net_queryDetail(queryList!![index], "2")
            }
            "0", "1", "3" -> {
                net_queryDetail(queryList!![index], "0")
            }
        }
    }

    private fun net_allPeopleQuery() {
        val map = hashMapOf<String, String>()
        map["phone"] = "" + SPTools[this@QueryListActivity, Constant.PHONE, ""]
        NetTools.net(map, Urls().allPeopleQuery, this) { response ->
            Log.e("zj", "allPeopleQuery = " + response.data)

        }
    }

    private fun net_queryDetail(bean: QueryBean, state: String) {
        val map = hashMapOf<String, String>()
        map["phone"] = "" + SPTools[this@QueryListActivity, Constant.PHONE, ""]
        map["zjhm"] = "" + bean.zhengjianNum
        NetTools.net(map, Urls().queryDetail, this) { response ->
            Log.e("zj", "queryDetail = " + response.data)
            var commitBean = Gson().fromJson<CommitBean>(response.data, CommitBean::class.java)

            if (state == "2") {
                val mIntent = Intent(this, Collect1Activity::class.java)
                if (bean.type == "0") {
                    mIntent.putExtra("title", "个人办理")
                } else {
                    mIntent.putExtra("title", "亲属代办")
                }
                mIntent.putExtra("type", Integer.parseInt(bean.type))
                mIntent.putExtra("isModify", 1)
                mIntent.putExtra("commitBean", commitBean)
                startActivity(mIntent)
            } else {
                // 下载头像证件照
                NetTools.net_DownloadImg(
                        Environment.getExternalStorageDirectory().absolutePath + "/sbcj_cache",
                        "index_社保卡采集.png",
                        Urls.fileAccessHost+commitBean.zp//下载图片路径 "https://js.tuguaishou.com/start-design/20180326/28.jpg"
                ) {
                    // 不论下载头像证件照成功失败都调用这个方法
                    // 需要替换的字符串
                    val map = HashMap<String, String>()
                    map["et0"] = commitBean.xm
                    map["et1"] = commitBean.xb
                    map["et2"] = commitBean.gj
                    map["et3"] = commitBean.zjlx
                    map["et4"] = commitBean.zjhm
                    map["et5"] = commitBean.zjyxq
                    map["et6"] = commitBean.csrq
                    map["et7"] = commitBean.mz
                    map["et8"] = commitBean.lxsj
                    map["et9"] = commitBean.lxdh
                    map["et10"] = commitBean.hjxz
                    map["et11"] = commitBean.yzbm
                    map["et12"] = commitBean.ryzt
                    map["et13"] = commitBean.jhrxm
                    map["et14"] = commitBean.jhrzjlx
                    map["et15"] = commitBean.jhrzh
                    map["et16"] = commitBean.txdz
                    map["et17"] = commitBean.zszy
                    map["et18"] = commitBean.zshy
                    map["et19"] = commitBean.klmyh
                    map["et20"] = commitBean.dbr_xm
                    map["et21"] = commitBean.dbr_sfzhm
                    map["et22"] = commitBean.dbr_xb
                    map["et23"] = commitBean.dbr_lxdh
                    map["et24"] = commitBean.xm
                    map["et25"] = commitBean.zjhm
                    map["et26"] = "申请日期"
                    map["et27"] = "申请地点"
                    map["et28"] = commitBean.dbr_xm
                    map["et29"] = commitBean.dbr_sfzhm
                    map["et30"] = commitBean.yjdz
                    // 保存到sd卡的路径
                    var path = Environment.getExternalStorageDirectory().absolutePath + "/sbcj_cache/index_社保卡采集.html"
                    // 复制assets里的html到sdcard根目录并替换默认值
                    AssetsTools.copy("index.html", path, map, this)
                    // 跳转加载html
                    val mIntent = Intent(this, CollectWebActivity::class.java)
                    mIntent.putExtra("title", "查看详情")
                    mIntent.putExtra("url", "file:///$path")
                    mIntent.putExtra("state", bean.state)
                    startActivity(mIntent)
                }
            }

        }
    }

}
