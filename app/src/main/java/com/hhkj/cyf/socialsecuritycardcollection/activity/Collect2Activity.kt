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
import com.hhkj.cyf.socialsecuritycardcollection.tools.PhoneTools
import com.hhkj.cyf.socialsecuritycardcollection.view.jdaddress.AddressBean
import com.hhkj.cyf.socialsecuritycardcollection.view.jdaddress.AreaPickerView
import com.hhkj.cyf.socialsecuritycardcollection.view.jdaddress.MyTools.getCityJson
import kotlinx.android.synthetic.main.activity_collect2.*
import org.jetbrains.anko.toast
/**
 * 其他信息
 */
class Collect2Activity : BaseActivity() {

    private var type = 0//0个人，1采集
    private var isModify = 0//0录入，1修改

    private var areaPickerView: AreaPickerView? = null
    private var addressBeans: List<AddressBean>? = null
    private var myNum: IntArray? = null
    private var dictionaryBean: DictionaryBean? = null
    private var commitBean: CommitBean? = null


    private var ryztId = ""
    private var gjId = ""
    private var hjxzId = ""
    private var klmyhId = ""
    private var zszyId = ""
    private var zshyId = ""
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

        addressBeans = Gson().fromJson<Any>(getCityJson(this), object : TypeToken<List<AddressBean>>() {}.type) as List<AddressBean>?
        dictionaryBean = intent.getSerializableExtra("dictionaryBean") as DictionaryBean?

        commitBean = intent.getSerializableExtra("commitBean") as CommitBean?
        if (isModify == 0) {

            tv_peopleState.text = dictionaryBean!!.ryztMap[0].name
            tv_country.text = dictionaryBean!!.gjMap[0].name
            tv_householdType.text = dictionaryBean!!.hjxzMap[0].name
            tv_bank.text = dictionaryBean!!.klmyhMap[0].name
            tv_profession.text = dictionaryBean!!.zszyMap[0].name
            tv_industry.text = dictionaryBean!!.zshyMap[0].name

            ryztId = dictionaryBean!!.ryztMap[0].id
            gjId = dictionaryBean!!.gjMap[0].id
            hjxzId = dictionaryBean!!.hjxzMap[0].id
            klmyhId = dictionaryBean!!.klmyhMap[0].id
            zszyId = dictionaryBean!!.zszyMap[0].id
            zshyId = dictionaryBean!!.zshyMap[0].id

        } else {

            tv_peopleState.text = commitBean!!.ryztName
            tv_country.text = commitBean!!.gjName
            tv_householdType.text = commitBean!!.hjxzName
            tv_bank.text = commitBean!!.klmyhName
            tv_profession.text = commitBean!!.zszyName
            tv_industry.text = commitBean!!.zshyName

            ryztId = commitBean!!.ryzt
            gjId = commitBean!!.gj
            hjxzId = commitBean!!.hjxz
            klmyhId = commitBean!!.klmyh
            zszyId = commitBean!!.zszy
            zshyId = commitBean!!.zshy

            et_phone.setText(commitBean!!.lxsjStr1)
            et_gdPhone.setText(commitBean!!.lxdhStr1)
            et_yb.setText(commitBean!!.yzbm)

            //选择区域
//            commitBean = CommitBean()
//            commitBean!!.provinceId = "220000";
//            commitBean!!.cityId = "220200";
//            commitBean!!.regionId = "220211";
            var num1 = 0
            var num2 = 0
            var num3 = 0
            for (i in 0 until addressBeans!!.size) {
                if (addressBeans!![i].value == commitBean!!.provinceId) {
                    num1 = i
                    for (j in 0 until addressBeans!![i].children.size) {
                        if (addressBeans!![i].children[j].value == commitBean!!.cityId) {
                            num2 = j
                            for (k in 0 until addressBeans!![i].children[j].children.size) {
                                if (addressBeans!![i].children[j].children[k].value == commitBean!!.regionId) {
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
            myNum = intArrayOf(num1, num2, num3)

            tv_area.text = addressBeans!![myNum!![0]].label + "-" + addressBeans!![myNum!![0]].children[myNum!![1]].label + "-" + addressBeans!![myNum!![0]].children[myNum!![1]].children[myNum!![2]].label

            et_address.setText(commitBean!!.yjdz)
        }
        areaPickerView = AreaPickerView(this, R.style.Dialog, addressBeans)
        areaPickerView!!.setAreaPickerViewCallback { value ->
            myNum = value
            if (value.size == 3) {
                Log.e("zj","commitBean == "+(commitBean == null))
                Log.e("zj","addressBeans == "+(addressBeans == null))
                commitBean!!.provinceId = addressBeans!![value[0]].value
                commitBean!!.cityId = addressBeans!![value[0]].children[value[1]].value
                commitBean!!.regionId = addressBeans!![value[0]].children[value[1]].children[value[2]].value

                commitBean!!.provinceName = addressBeans!![value[0]].label
                commitBean!!.cityName = addressBeans!![value[0]].children[value[1]].label
                commitBean!!.regionName = addressBeans!![value[0]].children[value[1]].children[value[2]].label
                tv_area.text = addressBeans!![value[0]].label + "-" + addressBeans!![value[0]].children[value[1]].label + "-" + addressBeans!![value[0]].children[value[1]].children[value[2]].label
            } else {
                tv_area.text = addressBeans!![value[0]].label + "-" + addressBeans!![value[0]].children[value[1]].label
            }
        }
    }

    private fun initClick() {
        ll_peopleState.setOnClickListener {
            SelectItemActivity.startSelectItem(this, dictionaryBean!!.ryztMap, ryztId, object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_peopleState.text = name
//                    commitBean!!.ryzt = id
                    ryztId = id

                }
            })
        }
        ll_country.setOnClickListener {
            SelectItemActivity.startSelectItem(this, dictionaryBean!!.gjMap, gjId, object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_country.text = name
                    gjId = id
                }
            })
        }
        ll_householdType.setOnClickListener {
            SelectItemActivity.startSelectItem(this, dictionaryBean!!.hjxzMap, hjxzId, object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_householdType.text = name
                    hjxzId = id
                }
            })
        }
        ll_bank.setOnClickListener {
            SelectItemActivity.startSelectItem(this, dictionaryBean!!.klmyhMap, klmyhId, object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_bank.text = name
                    klmyhId = id
                }
            })
        }
        ll_profession.setOnClickListener {
            SelectItemActivity.startSelectItem(this, dictionaryBean!!.zszyMap, zszyId, object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_profession.text = name
                    zszyId =id
                }
            })
        }
        ll_industry.setOnClickListener {
            SelectItemActivity.startSelectItem(this, dictionaryBean!!.zshyMap, zshyId, object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_industry.text = name
                    zshyId = id
                }
            })
        }
        ll_area.setOnClickListener {
            if (myNum == null) {
                areaPickerView!!.setSelect()
            } else {
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
            if (!PhoneTools.isMobile(et_phone.text.toString())) {
                toast("联系手机格式不正确")
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
            if (et_address.text.length < 5) {
                toast("邮寄地址不能少于5个字")
                return@setOnClickListener
            }

            commitBean!!.ryzt = ryztId
            commitBean!!.gj = gjId
            commitBean!!.hjxz = hjxzId
            commitBean!!.klmyh = klmyhId
            commitBean!!.zszy = zszyId
            commitBean!!.zshy = zshyId

            commitBean!!.yjdz = et_address.text.toString()
            commitBean!!.lxsjStr1 = et_phone.text.toString()
            commitBean!!.lxdhStr1 = et_gdPhone.text.toString()
            commitBean!!.yzbm = et_yb.text.toString()

            commitBean!!.ryztName = tv_peopleState.text.toString()
            commitBean!!.gjName = tv_country.text.toString()
            commitBean!!.hjxzName = tv_householdType.text.toString()
            commitBean!!.klmyhName = tv_bank.text.toString()
            commitBean!!.zszyName = tv_profession.text.toString()
            commitBean!!.zshyName = tv_industry.text.toString()

            val mIntent = Intent(this, Collect3Activity::class.java)
            mIntent.putExtra("title", intent.getStringExtra("title"))
            mIntent.putExtra("commitBean", commitBean)
            mIntent.putExtra("isModify", isModify)

            mIntent.putExtra("type", type)
            startActivity(mIntent)
        }
    }

}
