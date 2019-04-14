package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.bean.CommitBean
import com.hhkj.cyf.socialsecuritycardcollection.bean.DictionaryBean
import com.hhkj.cyf.socialsecuritycardcollection.tools.ToastUtil
import com.hhkj.cyf.socialsecuritycardcollection.tools.Validator
import kotlinx.android.synthetic.main.activity_collect1_2.*
import org.jetbrains.anko.toast
/**
 * 监护人信息
 */
class Collect1_2Activity : BaseActivity() {

    private var type = 0//0个人，1采集
    private var isModify = 0//0录入，1修改
    private var jhrzjlxId = ""

    private var dictionaryBean: DictionaryBean? = null
    private var commitBean: CommitBean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect1_2)
        initView()
        initClick()
    }

    private fun initView() {
        setLeftBtn(true)
        setTextTitle(intent.getStringExtra("title"))
        type = intent.getIntExtra("type", 0)
        isModify = intent.getIntExtra("isModify", 0)
        dictionaryBean = intent.getSerializableExtra("dictionaryBean") as DictionaryBean?
        commitBean = intent.getSerializableExtra("commitBean") as CommitBean?

        if (isModify == 0){
            tv_cardType2.text =dictionaryBean!!.zjlxMap[0].name
            jhrzjlxId = dictionaryBean!!.zjlxMap[0].id
        }else{
            tv_cardType2.text =commitBean!!.jhrzjlxName
            if (commitBean!!.jhrzjlx!= null){
                jhrzjlxId = commitBean!!.jhrzjlx
            }
            et_jhrIdCard.setText(commitBean!!.jhrzh)
            et_jhrName.setText(commitBean!!.jhrxm)
        }
    }

    private fun initClick() {
        ll_cardType2.setOnClickListener {
            SelectItemActivity.startSelectItem(this, dictionaryBean!!.zjlxMap, jhrzjlxId, object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_cardType2.text = name
                    jhrzjlxId = id
                    commitBean!!.jhrzjlxName = name
                }
            })
        }
        btn_next.setOnClickListener {
            if (et_jhrIdCard.text.isEmpty()){
                ToastUtil.showToastMessage(this@Collect1_2Activity, "监护人证件号码不能为空",R.mipmap.toast_notice)

                return@setOnClickListener
            }

            if (tv_cardType2.text == "身份证" && !Validator.isIDCard(et_jhrIdCard.text.toString())) {
                ToastUtil.showToastMessage(this@Collect1_2Activity, "身份证号格式错误",R.mipmap.toast_notice)

                return@setOnClickListener
            }
            if (et_jhrName.text.isEmpty()){
                ToastUtil.showToastMessage(this@Collect1_2Activity, "监护人姓名不能为空",R.mipmap.toast_notice)

                return@setOnClickListener
            }

            commitBean!!.jhrzjlx = jhrzjlxId

            commitBean!!.jhrzh = et_jhrIdCard.text.toString()
            commitBean!!.jhrxm = et_jhrName.text.toString()

            commitBean!!.jhrzjlxName = tv_cardType2.text.toString()
            val mIntent = Intent(this, Collect2Activity::class.java)
            mIntent.putExtra("title", intent.getStringExtra("title"))
            mIntent.putExtra("dictionaryBean", dictionaryBean)
            mIntent.putExtra("commitBean", commitBean)
            mIntent.putExtra("isModify", isModify)

            mIntent.putExtra("type", type)
            startActivity(mIntent)
        }
    }

}
