package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.smarttop.library.utils.LogUtil
import com.smarttop.library.widget.BottomDialog
import kotlinx.android.synthetic.main.activity_collect2.*


class Collect2Activity : BaseActivity() {

    private var type = 0//0个人，1采集

    private var provinceCode = ""
    private var cityCode = ""
    private var countyCode = ""
    private var streetCode = ""
    private var provincePosition = 0
    private var cityPosition = 0
    private var countyPosition = 0
    private var streetPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect2)
        initView()
        initClick()
    }

    private fun initView() {
        setLeftBtn(true)
        setTextTitle(intent.getStringExtra("title"))
        type = intent.getIntExtra("type", 0)
    }

    private fun initClick() {
        ll_area.setOnClickListener {
            val dialog = BottomDialog(this)
            dialog.setDisplaySelectorArea(provinceCode, provincePosition,
                    cityCode, cityPosition,
                    countyCode, countyPosition,
                    streetCode, streetPosition)
            dialog.setOnAddressSelectedListener { province, city, county, street ->
                provinceCode = if (province == null) "" else province.code
                cityCode = if (city == null) "" else city.code
                countyCode = if (county == null) "" else county.code
                streetCode = if (street == null) "" else street.code
                LogUtil.d("数据", "省份id=$provinceCode")
                LogUtil.d("数据", "城市id=$cityCode")
                LogUtil.d("数据", "乡镇id=$countyCode")
                LogUtil.d("数据", "街道id=$streetCode")
                val s = (if (province == null) "" else province.name) + (if (city == null) "" else city.name) + (if (county == null) "" else county.name) +
                        if (street == null) "" else street.name
                tv_area.text = s
                dialog.dismiss()
            }
            dialog.setSelectorAreaPositionListener { provincePosition, cityPosition, countyPosition, streetPosition ->
                this.provincePosition = provincePosition
                this.cityPosition = cityPosition
                this.countyPosition = countyPosition
                this.streetPosition = streetPosition
                LogUtil.d("数据", "省份位置=" + provincePosition);
                LogUtil.d("数据", "城市位置=" + cityPosition);
                LogUtil.d("数据", "乡镇位置=" + countyPosition);
                LogUtil.d("数据", "街道位置=" + streetPosition);
            }
            dialog.show()
        }
        btn_next.setOnClickListener {
            val mIntent = Intent(this, Collect3Activity::class.java)
            mIntent.putExtra("title", intent.getStringExtra("title"))
            mIntent.putExtra("type", type)
            startActivity(mIntent)
        }
    }

}
