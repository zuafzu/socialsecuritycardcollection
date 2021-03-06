package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.adapter.SelectItemAdapter
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.bean.DictionaryBean
import com.hhkj.cyf.socialsecuritycardcollection.bean.SelectItemBean
import kotlinx.android.synthetic.main.activity_select_item.*

class SelectItemActivity : BaseActivity() {

    private var name = ""
    private var id = ""

    companion object {

        var listener: OnMySelectItemListener? = null
        var list: ArrayList<DictionaryBean.ListBean>? = null

        fun startSelectItem(activity: Activity, list: ArrayList<DictionaryBean.ListBean>, id: String, listener: OnMySelectItemListener) {
            this.listener = listener
            this.list = list
            val mIntent = Intent(activity, SelectItemActivity::class.java)
            mIntent.putExtra("id", id)
            activity.startActivity(mIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_item)
        initView()
        initClick()
        setData()
    }

    private fun initView() {
        setLeftBtn(true)
        setTextTitle("请选择")
        id = intent.getStringExtra("id")
        Log.e("zj","id ="+id)
        Log.e("zj","list ="+list.toString())
        listView.setOnItemClickListener { adapterView, view, i, l ->
            name = list!![i].name
            id = list!![i].id
        }
    }

    private fun setData() {
        listView.adapter = SelectItemAdapter(list, this)
        if (list!!.size > 0) {
//            listView.setItemChecked(0, true)
//            name = list!![0].name
//            id = list!![0].id
            for (i in 0 until list!!.size) {
                if (list!![i].id == id) {
                    name = list!![i].name
                    listView.setItemChecked(i, true)
                    break
                }
            }
        }
    }

    private fun initClick() {
        btn_ok.setOnClickListener {
            listener!!.setData(name, id)
            finish()
        }
    }

    interface OnMySelectItemListener {
        fun setData(name: String, id: String)
    }

}
