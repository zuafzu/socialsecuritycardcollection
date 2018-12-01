package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.DatePicker
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant
import com.hhkj.cyf.socialsecuritycardcollection.tools.Validator
import kotlinx.android.synthetic.main.activity_collect.*
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

class Collect1Activity : BaseActivity() {

    private var type = 0//0个人，1代办

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect)
        initView()
        initClick()
    }

    private fun initView() {
        setLeftBtn(true)
        setTextTitle(intent.getStringExtra("title"))
        type = intent.getIntExtra("type", 0)

        tv_sex.text = Constant.getSexs()[0].name
        tv_cardType.text = Constant.getCardType()[0].name
        tv_nationality.text = Constant.getNationality()[0].name
    }

    @SuppressLint("SimpleDateFormat")
    private fun initClick() {
        ll_sex.setOnClickListener {
            SelectItemActivity.startSelectItem(this, Constant.getSexs(), "", object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_sex.text = name
                }
            })
        }
        ll_cardType.setOnClickListener {
            SelectItemActivity.startSelectItem(this, Constant.getCardType(), "", object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    if(name == "身份证"){
                        ll_id_photo.visibility = View.VISIBLE
                    }else{
                        ll_id_photo.visibility = View.GONE
                    }
                    tv_cardType.text = name
                }
            })
        }
        ll_birth.setOnClickListener {
            val mView= layoutInflater.inflate(R.layout.dialog_date,null)
            val view = mView.findViewById<DatePicker>(R.id.datePicker)
            val builder = AlertDialog.Builder(this@Collect1Activity)
            builder.setTitle("请选择")
            builder.setView(mView)
            builder.setPositiveButton("确认") { p0, p1 ->
                var month = ""
                month = if (view.month + 1 < 10) {
                    "0" + (view.month + 1)
                } else {
                    "" + (view.month + 1)
                }
                var day = ""
                day = if (view.dayOfMonth < 10) {
                    "0" + view.dayOfMonth
                } else {
                    "" + view.dayOfMonth
                }
                tv_birth.text = "" + view.year + "/" + month + "/" + day
                p0.dismiss()
            }
            builder.setNegativeButton("取消") { p0, p1 ->
                p0.dismiss()
            }
            builder.create().show()
        }
        ll_cardEndDate.setOnClickListener {
            val mView= layoutInflater.inflate(R.layout.dialog_date,null)
            val view = mView.findViewById<DatePicker>(R.id.datePicker)
            val builder = AlertDialog.Builder(this@Collect1Activity)
            builder.setTitle("请选择")
            builder.setView(mView)
            builder.setPositiveButton("确认") { p0, p1 ->
                var month = ""
                month = if (view.month + 1 < 10) {
                    "0" + (view.month + 1)
                } else {
                    "" + (view.month + 1)
                }
                var day = ""
                day = if (view.dayOfMonth < 10) {
                    "0" + view.dayOfMonth
                } else {
                    "" + view.dayOfMonth
                }
                tv_cardEndDate.text = "" + view.year + "/" + month + "/" + day
                p0.dismiss()
            }
            builder.setNegativeButton("取消") { p0, p1 ->
                p0.dismiss()
            }
            builder.setNeutralButton("长期") { p0, p1 ->
                tv_cardEndDate.text = "长期"
                p0.dismiss()
            }
            builder.create().show()
        }
        ll_nationality.setOnClickListener {
            SelectItemActivity.startSelectItem(this, Constant.getNationality(), "", object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_nationality.text = name
                }
            })
        }
        btn_next.setOnClickListener {
            if (et_name.text.toString() == "") {
                toast("姓名不能为空")
                return@setOnClickListener
            }
            if (et_id.text.toString() == "") {
                toast("证件号码不能为空")
                return@setOnClickListener
            }
            if (tv_cardType.text == "身份证" && !Validator.isIDCard(et_id.text.toString())) {
                toast("身份证格式有误")
                return@setOnClickListener
            }
            if (tv_birth.text.toString() == "") {
                toast("出生日期不能为空")
                return@setOnClickListener
            }
            if (tv_cardEndDate.text.toString() == "") {
                toast("证件有效期不能为空")
                return@setOnClickListener
            }
            if (et_address.text.toString() == "") {
                toast("通信地址不能为空")
                return@setOnClickListener
            }

            val calendar = Calendar.getInstance()
            calendar.time = SimpleDateFormat("yyyy/MM/dd").parse(tv_birth.text.toString())
            if ((tv_cardType.text == "户口本" && tv_cardEndDate.text == "长期") ||
                    (tv_cardType.text == "身份证" && getCurrentAge(Date(calendar.timeInMillis)) < 16)) {
                val mIntent = Intent(this, Collect1_2Activity::class.java)
                mIntent.putExtra("title", intent.getStringExtra("title"))
                mIntent.putExtra("type", type)
                startActivity(mIntent)
            } else {
                val mIntent = Intent(this, Collect2Activity::class.java)
                mIntent.putExtra("title", intent.getStringExtra("title"))
                mIntent.putExtra("type", type)
                startActivity(mIntent)
            }
        }
    }

    /**
     * 根据生日计算当前周岁数
     */
    fun getCurrentAge(birthday: Date): Int {
        // 当前时间
        val curr = Calendar.getInstance()
        // 生日
        val born = Calendar.getInstance()
        born.time = birthday
        // 年龄 = 当前年 - 出生年
        val age = curr.get(Calendar.YEAR) - born.get(Calendar.YEAR)
        if (age <= 0) {
            return 0
        }
        // 如果当前月份小于出生月份: age-1
        // 如果当前月份等于出生月份, 且当前日小于出生日: age-1
        val currMonth = curr.get(Calendar.MONTH)
        val currDay = curr.get(Calendar.DAY_OF_MONTH)
        val bornMonth = born.get(Calendar.MONTH)
        val bornDay = born.get(Calendar.DAY_OF_MONTH)
        if (currMonth < bornMonth || currMonth == bornMonth && currDay <= bornDay) {
            age
        }
        return if (age < 0) 0 else age
    }

}
