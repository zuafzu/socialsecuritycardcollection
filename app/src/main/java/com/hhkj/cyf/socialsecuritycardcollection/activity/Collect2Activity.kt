package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.bean.CommitBean
import com.hhkj.cyf.socialsecuritycardcollection.bean.DictionaryBean
import com.hhkj.cyf.socialsecuritycardcollection.view.jdaddress.AddressBean
import com.hhkj.cyf.socialsecuritycardcollection.view.jdaddress.AreaPickerView
import com.hhkj.cyf.socialsecuritycardcollection.view.jdaddress.MyTools.getCityJson
import kotlinx.android.synthetic.main.activity_collect2.*
import org.jetbrains.anko.toast


class Collect2Activity : BaseActivity() {

    private var type = 0//0个人，1采集
    private var isModify = 0//0录入，1修改

    private var areaPickerView: AreaPickerView? = null
    private var addressBeans: List<AddressBean>? = null
    private var myNum: IntArray? = null
    private var dictionaryBean: DictionaryBean? = null
    private var commitBean: CommitBean? = null

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
        isModify = intent.getIntExtra("isModify", 0)

        dictionaryBean = intent.getSerializableExtra("dictionaryBean") as DictionaryBean?
//        commitBean = intent.getSerializableExtra("commitBean") as CommitBean?
        addressBeans = Gson().fromJson<Any>(getCityJson(this), object : TypeToken<List<AddressBean>>() {}.type) as List<AddressBean>?


        //-----------
        commitBean = CommitBean()
        commitBean!!.provinceId = "220000";
        commitBean!!.cityId = "220200";
        commitBean!!.regionId = "220211";
        var num1 = 0
        var num2 = 0
        var num3 = 0
        for(i in 0 until addressBeans!!.size){
            if (addressBeans!![i].value == commitBean!!.provinceId){
                num1 = i
                for (j in 0 until addressBeans!![i].children.size){
                    if (addressBeans!![i].children[j].value == commitBean!!.cityId){
                        num2 = j
                        for (k in 0 until addressBeans!![i].children[j].children.size){
                            if (addressBeans!![i].children[j].children[k].value == commitBean!!.regionId){
                                num3 = k
                                break
                            }
                        }
                        break
                    }
                }
                break
            }
        }

        Log.e("zj","num ===="+num1+","+num2+","+num3)
//
        tv_area.text = addressBeans!![num1].label + "-" + addressBeans!![num1].children[num2].label + "-" + addressBeans!![num1].children[num2].children[num3].label
        myNum =  intArrayOf(6, 1, 3)
//        myNum!![0] = num1
//        myNum!![1] = num2
//        myNum!![2] = num3

//        if (isModify == 0) {
//            tv_peopleState.text = dictionaryBean!!.ryztMap[0].name
//            tv_country.text = dictionaryBean!!.gjMap[0].name
//            tv_householdType.text = dictionaryBean!!.hjxzMap[0].name
//            tv_bank.text = dictionaryBean!!.klmyhMap[0].name
//            tv_profession.text = dictionaryBean!!.zszyMap[0].name
//            tv_industry.text = dictionaryBean!!.zshyMap[0].name
//        } else {
//            tv_peopleState.text = commitBean!!.ryzt
//            tv_country.text = commitBean!!.gj
//            tv_householdType.text = commitBean!!.hjxz
//            tv_bank.text = commitBean!!.klmyh
//            tv_profession.text = commitBean!!.zszy
//            tv_industry.text = commitBean!!.zshy
//
//            et_phone.setText(commitBean!!.lxsj)
//            et_gdPhone.setText(commitBean!!.lxdh)
//            et_yb.setText(commitBean!!.ybbh)
//
//            //选择区域
////            tv_area.text = commitBean!!.yjqy
//
//            var num1 = 0
//            var num2 = 0
//            var num3 = 0
//            for(i in 0 until addressBeans!!.size){
//                if (addressBeans!![i].value == commitBean!!.provinceId){
//                    num1 = i
//                }
//                for (j in 0 until addressBeans!![i].children.size){
//                    if (addressBeans!![i].children[j].value == commitBean!!.cityId){
//                        num2 = j
//                    }
//
//                    for (k in 0 until addressBeans!![i].children[j].children.size){
//                        if (addressBeans!![i].children[j].value == commitBean!!.cityId){
//                            num3 = k
//                        }
//                    }
//                }
//
//            }
//
//            Log.e("zj","num ===="+num1+","+num2+","+num3)
//            et_address.setText(commitBean!!.yjdz)
//        }
        areaPickerView = AreaPickerView(this, R.style.Dialog, addressBeans)
        areaPickerView!!.setAreaPickerViewCallback { value ->
            myNum = value
            if (value.size == 3)
                tv_area.text = addressBeans!![value[0]].label + "-" + addressBeans!![value[0]].children[value[1]].label + "-" + addressBeans!![value[0]].children[value[1]].children[value[2]].label
            else
                tv_area.text = addressBeans!![value[0]].label + "-" + addressBeans!![value[0]].children[value[1]].label
        }
        areaPickerView!!.setSelect()
        areaPickerView!!.show()
        areaPickerView!!.dismiss()
    }

    private fun initClick() {
        ll_peopleState.setOnClickListener {
            SelectItemActivity.startSelectItem(this, dictionaryBean!!.ryztMap, "", object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_peopleState.text = name
                    commitBean!!.ryzt = id
                }
            })
        }
        ll_country.setOnClickListener {
            SelectItemActivity.startSelectItem(this, dictionaryBean!!.gjMap, "", object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_country.text = name
                    commitBean!!.gj = id
                }
            })
        }
        ll_householdType.setOnClickListener {
            SelectItemActivity.startSelectItem(this, dictionaryBean!!.hjxzMap, "", object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_householdType.text = name
                    commitBean!!.hjxz = id
                }
            })
        }
        ll_bank.setOnClickListener {
            SelectItemActivity.startSelectItem(this, dictionaryBean!!.klmyhMap, "", object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_bank.text = name
                    commitBean!!.klmyh = id
                }
            })
        }
        ll_profession.setOnClickListener {
            SelectItemActivity.startSelectItem(this, dictionaryBean!!.zszyMap, "", object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_profession.text = name
                    commitBean!!.zszy = id
                }
            })
        }
        ll_industry.setOnClickListener {
            SelectItemActivity.startSelectItem(this, dictionaryBean!!.zshyMap, "", object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_industry.text = name
                    commitBean!!.zshy = id
                }
            })
        }
        ll_area.setOnClickListener {
            if (myNum == null) {
                areaPickerView!!.setSelect()
            } else {
                Log.e("zj", "myNum = " + myNum!!.size)
                Log.e("zj", "myNum1 = " + myNum!![0].toString())
                Log.e("zj", "myNum2 = " + myNum!![1].toString())
                Log.e("zj", "myNum3 = " + myNum!![2].toString())
                if (myNum!!.size == 3) {
                    areaPickerView!!.setSelect(myNum!![0], myNum!![1], myNum!![2])
                } else {
                    areaPickerView!!.setSelect(myNum!![0], myNum!![1])
                }
            }
            areaPickerView!!.show()
        }
        btn_next.setOnClickListener {
            if (et_phone.text.toString() == "") {
                toast("联系手机不能为空")
                return@setOnClickListener
            }
            if (et_yb.text.toString() == "") {
                toast("邮政编码不能为空")
                return@setOnClickListener
            }
            if (et_address.text.toString() == "") {
                toast("邮寄地址不能为空")
                return@setOnClickListener
            }
            commitBean!!.yjdz = tv_area.text.toString() + et_address.text.toString()
            commitBean!!.lxsj = et_phone.text.toString()
            commitBean!!.lxdh = et_gdPhone.text.toString()
            commitBean!!.ybbh = et_yb.text.toString()
            val mIntent = Intent(this, Collect3Activity::class.java)
            mIntent.putExtra("title", intent.getStringExtra("title"))
            mIntent.putExtra("commitBean", commitBean)
            mIntent.putExtra("isModify", isModify)

            mIntent.putExtra("type", type)
            startActivity(mIntent)
        }
    }

}
