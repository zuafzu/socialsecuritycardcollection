package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
import java.io.File
import java.util.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.set

class QueryListActivity : BaseActivity(), QueryListAdapter.OnMyClickListener {

    private var queryList: ArrayList<QueryBean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query_list)
        initView()
        net_allPeopleQuery()
    }

    private fun initView() {
        setLeftBtn(true)
        setTextTitle("采集进度查询")
    }

    override fun clickBtn(index: Int) {
        // 0保存成功，1上传成功，2上传失败，3异地
        when (queryList!![index].status) {
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
            queryList = Gson().fromJson<ArrayList<QueryBean>>(response.data, object : TypeToken<ArrayList<QueryBean>>() {}.type)
            Log.e("zj", "queryList = " + queryList.toString())
            setData()
        }
    }

    private fun setData() {
        listView.adapter = QueryListAdapter(queryList, this, this)
    }

    private fun net_queryDetail(bean: QueryBean, state: String) {
        val map = hashMapOf<String, String>()
        map["phone"] = "" + SPTools[this@QueryListActivity, Constant.PHONE, ""]
        map["zjhm"] = "" + bean.zjhm
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
                val path = Environment.getExternalStorageDirectory().absolutePath + "/sbcj_cache"
                Log.e("zj", "path = $path")
                val file = File("$path/")
                if (!file.exists()) {
                    file.mkdirs()
                }
                // 下载头像证件照
                NetTools.net_DownloadImg(this@QueryListActivity,
                        path,
                        "index_社保卡采集.png",
                        Urls.fileAccessHost + commitBean.zp//下载图片路径 "https://js.tuguaishou.com/start-design/20180326/28.jpg"
                ) {
                    // 不论下载头像证件照成功失败都调用这个方法
                    // 需要替换的字符串
                    Log.e("zj", "time = " + System.currentTimeMillis())
                    val map = HashMap<String, String>()
                    map["et0"] = commitBean.xmStr1
                    map["et1"] = commitBean.xbName
                    map["et2"] = commitBean.gjName
                    map["et3"] = commitBean.zjlxName
                    map["et4"] = commitBean.zjhm
                    map["et5"] = commitBean.zjyxq
                    map["et6"] = commitBean.csrqStr
                    map["et7"] = commitBean.mzName
                    map["et8"] = commitBean.lxsjStr1
                    map["et9"] = commitBean.lxdhStr1
                    map["et10"] = commitBean.hjxzName
                    map["et11"] = commitBean.yzbm
                    map["et12"] = commitBean.ryztName
                    map["et13"] = commitBean.jhrxm
                    map["et14"] = commitBean.jhrzjlxName
                    map["et15"] = commitBean.jhrzh
                    map["et16"] = commitBean.txdz
                    map["et17"] = commitBean.zszyName
                    map["et18"] = commitBean.zshyName
                    map["et19"] = commitBean.klmyhName
                    map["et20"] = commitBean.dbr_xm
                    map["et21"] = commitBean.dbr_sfzhm
                    map["et22"] = ""//commitBean.dbr_xb
                    map["et23"] = commitBean.dbr_lxdh
                    map["et24"] = commitBean.xmStr1
                    map["et25"] = commitBean.zjhm
                    map["et26"] = commitBean.createDate1
                    map["et27"] = commitBean.dwmc
                    map["et28"] = commitBean.dbr_xm
                    map["et29"] = commitBean.dbr_sfzhm
                    map["et30"] = commitBean.yjdz
                    // 保存到sd卡的路径
                    var path = "$path/index_社保卡采集.html"
                    // 复制assets里的html到sdcard根目录并替换默认值
                    AssetsTools.copy("index.html", path, map, this)
                    // 跳转加载html

                    Log.e("zj", "time2 = " + System.currentTimeMillis())

                    val mIntent = Intent(this, CollectWebActivity::class.java)
                    mIntent.putExtra("title", "查看详情")
                    mIntent.putExtra("url", "file:///$path")
                    mIntent.putExtra("state", bean.status)
                    mIntent.putExtra("type", Integer.parseInt(bean.type))
                    mIntent.putExtra("commitBean", commitBean)
                    startActivity(mIntent)
                }
            }

        }
    }

}
