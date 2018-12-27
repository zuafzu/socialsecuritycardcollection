package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.adapter.QueryListAdapter
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.bean.QueryBean
import com.hhkj.cyf.socialsecuritycardcollection.tools.AssetsTools
import com.hhkj.cyf.socialsecuritycardcollection.tools.NetTools
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
                // 下载头像证件照
                NetTools.net_DownloadImg(
                        Environment.getExternalStorageDirectory().absolutePath,
                        "要保存成的图像名字",
                        "下载图片的路径"
                ) {
                    // 不论下载头像证件照成功失败都调用这个方法
                    // 需要替换的字符串
                    val map = HashMap<String, String>()
                    map["et0"] = "姓名"
                    map["et1"] = "性别"
                    map["et2"] = "国籍"
                    // 保存到sd卡的路径
                    var path = Environment.getExternalStorageDirectory().absolutePath + "/index_社保卡采集.html"
                    // 复制assets里的html到sdcard根目录并替换默认值
                    AssetsTools.copy("index.html", path, map, this)
                    // 跳转加载html
                    val mIntent = Intent(this, CollectWebActivity::class.java)
                    mIntent.putExtra("title", "查看详情")
                    mIntent.putExtra("url", "file:///$path")
                    mIntent.putExtra("state", queryList!![index].state)
                    startActivity(mIntent)
                }
            }
        }
    }

}
