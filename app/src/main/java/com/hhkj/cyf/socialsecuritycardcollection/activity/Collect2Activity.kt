package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant
import kotlinx.android.synthetic.main.activity_collect2.*
import com.hhkj.cyf.socialsecuritycardcollection.view.jdaddress.AddressBean
import com.hhkj.cyf.socialsecuritycardcollection.view.jdaddress.AreaPickerView
import android.R.attr.button
import android.annotation.SuppressLint
import com.google.gson.reflect.TypeToken
import com.hhkj.cyf.socialsecuritycardcollection.view.jdaddress.MyTools.getCityJson
import com.google.gson.Gson


class Collect2Activity : BaseActivity() {

    private var type = 0//0个人，1采集

    private var areaPickerView: AreaPickerView? = null
    private var addressBeans: List<AddressBean>? = null
    private var myNum: IntArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect2)
        initView()
        initClick()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        setLeftBtn(true)
        setTextTitle(intent.getStringExtra("title"))
        type = intent.getIntExtra("type", 0)

        tv_peopleState.text = Constant.getRyzt(this)[0].name
        tv_country.text = Constant.getGj(this)[0].name
        tv_householdType.text = Constant.getHjxz(this)[0].name
        tv_bank.text = Constant.getKlmyh(this)[0].name
        tv_profession.text = Constant.getZszy(this)[0].name
        tv_industry.text = Constant.getZshy(this)[0].name

        addressBeans = Gson().fromJson<Any>(getCityJson(this), object : TypeToken<List<AddressBean>>() {}.type) as List<AddressBean>?
        areaPickerView = AreaPickerView(this, R.style.Dialog, addressBeans)
        areaPickerView!!.setAreaPickerViewCallback { value ->
            myNum = value
            if (value.size == 3)
                tv_area.text = addressBeans!![value[0]].label + "-" + addressBeans!![value[0]].children[value[1]].label + "-" + addressBeans!![value[0]].children[value[1]].children[value[2]].label
            else
                tv_area.text = addressBeans!![value[0]].label + "-" + addressBeans!![value[0]].children[value[1]].label
        }
    }

    private fun initClick() {
        ll_peopleState.setOnClickListener {
            SelectItemActivity.startSelectItem(this, Constant.getRyzt(this), "", object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_peopleState.text = name
                }
            })
        }
        ll_country.setOnClickListener {
            SelectItemActivity.startSelectItem(this, Constant.getGj(this), "", object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_country.text = name
                }
            })
        }
        ll_householdType.setOnClickListener {
            SelectItemActivity.startSelectItem(this, Constant.getHjxz(this), "", object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_householdType.text = name
                }
            })
        }
        ll_bank.setOnClickListener {
            SelectItemActivity.startSelectItem(this, Constant.getKlmyh(this), "", object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_bank.text = name
                }
            })
        }
        ll_profession.setOnClickListener {
            SelectItemActivity.startSelectItem(this, Constant.getZszy(this), "", object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_profession.text = name
                }
            })
        }
        ll_industry.setOnClickListener {
            SelectItemActivity.startSelectItem(this, Constant.getZshy(this), "", object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_industry.text = name
                }
            })
        }
        ll_area.setOnClickListener {
            if (myNum == null) {
                areaPickerView!!.setSelect()
            } else {
                if (myNum!!.size == 3) {
                    areaPickerView!!.setSelect(myNum!![0], myNum!![1], myNum!![2])
                } else {
                    areaPickerView!!.setSelect(myNum!![0], myNum!![1])
                }
            }
            areaPickerView!!.show()
        }
        btn_next.setOnClickListener {
            val mIntent = Intent(this, Collect3Activity::class.java)
            mIntent.putExtra("title", intent.getStringExtra("title"))
            mIntent.putExtra("type", type)
            startActivity(mIntent)
        }
    }

}
