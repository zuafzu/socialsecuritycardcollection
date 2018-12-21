package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.view.View
import android.widget.DatePicker
import android.widget.ImageView
import com.baidu.ocr.sdk.OCR
import com.baidu.ocr.sdk.OnResultListener
import com.baidu.ocr.sdk.exception.OCRError
import com.baidu.ocr.sdk.model.AccessToken
import com.baidu.ocr.sdk.model.IDCardParams
import com.baidu.ocr.sdk.model.IDCardResult
import com.baidu.ocr.ui.camera.CameraActivity
import com.baidu.ocr.ui.camera.CameraNativeHelper
import com.baidu.ocr.ui.camera.CameraView
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant
import com.hhkj.cyf.socialsecuritycardcollection.tools.FileUtil
import kotlinx.android.synthetic.main.activity_collect.*
import org.jetbrains.anko.toast
import java.io.File
import java.util.*

class Collect1Activity : BaseActivity() {

    private var type = 0//0个人，1代办
    private var hasGotToken = false
    private val REQUEST_CODE_CAMERA = 102
    private var imageView: ImageView? = null

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

        tv_sex.text = Constant.getXb(this)[0].name
        tv_cardType.text = Constant.getZjlx(this)[0].name
        tv_nationality.text = Constant.getMz(this)[0].name

        OCR.getInstance(this).initAccessToken(object : OnResultListener<AccessToken> {
            override fun onResult(result: AccessToken) {
                // 调用成功，返回AccessToken对象
                val token = result.accessToken
                hasGotToken = true
            }

            override fun onError(error: OCRError) {
                // 调用失败，返回OCRError子类SDKError对象
                runOnUiThread {
                    toast("licence方式获取token失败,errorCode：" + error.errorCode)
                }
            }
        }, applicationContext)
        CameraNativeHelper.init(this, OCR.getInstance(this).license
        ) { errorCode, e ->
            val msg: String = when (errorCode) {
                CameraView.NATIVE_SOLOAD_FAIL -> "加载so失败，请确保apk中存在ui部分的so"
                CameraView.NATIVE_AUTH_FAIL -> "授权本地质量控制token获取失败"
                CameraView.NATIVE_INIT_FAIL -> "本地质量控制"
                else -> errorCode.toString()
            }
            toast("本地质量控制初始化错误，错误原因： $msg")
        }
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun initClick() {
        rl_img_z.setOnClickListener {
            if (!checkTokenStatus()) {
                return@setOnClickListener
            }
            imageView = iv_img_z
            val intent = Intent(this@Collect1Activity, CameraActivity::class.java)
            intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                    FileUtil.getSaveFile(application).absolutePath)
            intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT)
            startActivityForResult(intent, REQUEST_CODE_CAMERA)
        }
        rl_img_f.setOnClickListener {
            if (!checkTokenStatus()) {
                return@setOnClickListener
            }
            imageView = iv_img_f
            val intent = Intent(this@Collect1Activity, CameraActivity::class.java)
            intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                    FileUtil.getSaveFile(application).absolutePath)
            intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_BACK)
            startActivityForResult(intent, REQUEST_CODE_CAMERA)
        }
        ll_sex.setOnClickListener {
            SelectItemActivity.startSelectItem(this, Constant.getXb(this), "", object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_sex.text = name
                }
            })
        }
        ll_cardType.setOnClickListener {
            SelectItemActivity.startSelectItem(this, Constant.getZjlx(this), "", object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    if (name == "身份证") {
                        ll_id_photo.visibility = View.VISIBLE
                    } else {
                        ll_id_photo.visibility = View.GONE
                    }
                    tv_cardType.text = name
                }
            })
        }
        ll_birth.setOnClickListener {
            val mView = layoutInflater.inflate(R.layout.dialog_date, null)
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
                tv_birth.text = "" + view.year + "" + month + "" + day
                p0.dismiss()
            }
            builder.setNegativeButton("取消") { p0, p1 ->
                p0.dismiss()
            }
            dialog = builder.create()
            dialog!!.show()
        }
        ll_cardEndDate.setOnClickListener {
            val mView = layoutInflater.inflate(R.layout.dialog_date, null)
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
                tv_cardEndDate.text = "" + view.year + "" + month + "" + day
                p0.dismiss()
            }
            builder.setNegativeButton("取消") { p0, p1 ->
                p0.dismiss()
            }
            builder.setNeutralButton("长期") { p0, p1 ->
                tv_cardEndDate.text = "长期"
                p0.dismiss()
            }
            dialog = builder.create()
            dialog!!.show()
        }
        ll_nationality.setOnClickListener {
            SelectItemActivity.startSelectItem(this, Constant.getMz(this), "", object : SelectItemActivity.OnMySelectItemListener {
                override fun setData(name: String, id: String) {
                    tv_nationality.text = name
                }
            })
        }
        btn_next.setOnClickListener {
            //            if (et_name.text.toString() == "") {
//                toast("姓名不能为空")
//                return@setOnClickListener
//            }
//            if (et_id.text.toString() == "") {
//                toast("证件号码不能为空")
//                return@setOnClickListener
//            }
//            if (tv_cardType.text == "身份证" && !Validator.isIDCard(et_id.text.toString())) {
//                toast("身份证格式有误")
//                return@setOnClickListener
//            }
//            if (tv_birth.text.toString() == "") {
//                toast("出生日期不能为空")
//                return@setOnClickListener
//            }
//            if (tv_cardEndDate.text.toString() == "") {
//                toast("证件有效期不能为空")
//                return@setOnClickListener
//            }
//            if (et_address.text.toString() == "") {
//                toast("通信地址不能为空")
//                return@setOnClickListener
//            }
//
//            val calendar = Calendar.getInstance()
//            calendar.time = SimpleDateFormat("yyyyMMdd").parse(tv_birth.text.toString())
//            if ((tv_cardType.text == "户口本" && tv_cardEndDate.text == "长期") ||
//                    (tv_cardType.text == "身份证" && getCurrentAge(Date(calendar.timeInMillis)) < 16)) {
            val mIntent = Intent(this, Collect1_2Activity::class.java)
            mIntent.putExtra("title", intent.getStringExtra("title"))
            mIntent.putExtra("type", type)
            startActivity(mIntent)
//            } else {
//                val mIntent = Intent(this, Collect2Activity::class.java)
//                mIntent.putExtra("title", intent.getStringExtra("title"))
//                mIntent.putExtra("type", type)
//                startActivity(mIntent)
//            }
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

    private fun checkTokenStatus(): Boolean {
        if (!hasGotToken) {
            toast("token还未成功获取")
        }
        return hasGotToken
    }

    private fun recIDCard(idCardSide: String, filePath: String) {
        showProgressDialog()
        val param = IDCardParams()
        param.imageFile = File(filePath)
        // 设置身份证正反面
        param.idCardSide = idCardSide
        // 设置方向检测
        param.isDetectDirection = true
        // 设置图像参数压缩质量0-100, 越大图像质量越好但是请求时间越长。 不设置则默认值为20
        param.imageQuality = 20

        OCR.getInstance(this).recognizeIDCard(param, object : OnResultListener<IDCardResult> {
            override fun onResult(result: IDCardResult?) {
                if (result != null) {
                    setID(result)
                }
                dismissProgressDialog()
            }

            override fun onError(error: OCRError) {
                toast(error.message!!)
                dismissProgressDialog()
            }
        })
    }

    private fun setID(result: IDCardResult) {
        if (result.idCardSide == "front") {
            if (result.name != null && result.name.toString() != "") {
                et_name.setText(result.name.words)
            }
            if (result.gender != null && result.gender.toString() != "") {
                tv_sex.text = result.gender.words
            }
            if (result.idNumber != null && result.idNumber.toString() != "") {
                et_id.setText(result.idNumber.words)
            }
            if (result.birthday != null && result.birthday.toString() != "") {
                tv_birth.text = result.birthday.words
            }
            if (result.ethnic != null && result.ethnic.toString() != "") {
                tv_nationality.text = result.ethnic.words
            }
            if (result.address != null && result.address.toString() != "") {
                et_address.setText(result.address.words)
            }
            tv_z.visibility = View.GONE
        } else if (result.idCardSide == "back") {
            if (result.expiryDate != null && result.expiryDate.toString() != "") {
                tv_cardEndDate.text = result.expiryDate.words
            }
            tv_f.visibility = View.GONE
        }
        imageView!!.setImageBitmap(BitmapFactory.decodeFile(FileUtil.getSaveFile(applicationContext).absolutePath))
        //imageView!!.setImageURI(Uri.fromFile(File(FileUtil.getSaveFile(applicationContext).absolutePath)))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE)
                val filePath = FileUtil.getSaveFile(applicationContext).absolutePath
                if (!TextUtils.isEmpty(contentType)) {
                    if (CameraActivity.CONTENT_TYPE_ID_CARD_FRONT == contentType) {
                        recIDCard(IDCardParams.ID_CARD_SIDE_FRONT, filePath)
                    } else if (CameraActivity.CONTENT_TYPE_ID_CARD_BACK == contentType) {
                        recIDCard(IDCardParams.ID_CARD_SIDE_BACK, filePath)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        // 释放本地质量控制模型
        CameraNativeHelper.release()
        super.onDestroy()
    }

}
