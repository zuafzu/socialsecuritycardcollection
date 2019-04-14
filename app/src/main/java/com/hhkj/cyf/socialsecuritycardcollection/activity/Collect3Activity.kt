package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.app.MyApplication
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.bean.CommitBean
import com.hhkj.cyf.socialsecuritycardcollection.bean.DictionaryBean
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant
import com.hhkj.cyf.socialsecuritycardcollection.tools.NetTools
import com.hhkj.cyf.socialsecuritycardcollection.tools.SPTools
import com.hhkj.cyf.socialsecuritycardcollection.tools.ToastUtil
import com.hhkj.cyf.socialsecuritycardcollection.url.Urls
import kotlinx.android.synthetic.main.activity_collect3.*
import org.jetbrains.anko.toast
import org.json.JSONObject
import top.zibin.luban.Luban
import top.zibin.luban.OnCompressListener
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.util.HashMap


class Collect3Activity : BaseActivity() {

    private var imgPath = ""
    private var type = 0//0个人，1采集
    private var isModify = 0//0录入，1修改

    private val colorPixel = arrayOf(intArrayOf(0, 0), intArrayOf(1, 0), intArrayOf(2, 0), intArrayOf(0, 1), intArrayOf(1, 1), intArrayOf(0, 2), intArrayOf(355, 0), intArrayOf(356, 0), intArrayOf(357, 0), intArrayOf(356, 1), intArrayOf(357, 1), intArrayOf(357, 2))
    private var commitBean: CommitBean? = null
    private var dictionaryBean: DictionaryBean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect3)
        initView()
        initClick()

    }

    private fun initView() {
        setLeftBtn(true)
        setTextTitle(intent.getStringExtra("title"))
        type = intent.getIntExtra("type", 0)
        isModify = intent.getIntExtra("isModify", 0)

        dictionaryBean = intent.getSerializableExtra("dictionaryBean") as DictionaryBean?

        Log.e("zj", "type  = " + type)
//        if (type == 0) {
//            btn_next.text = "保存"
//        } else {
//            btn_next.text = "下一步"
//        }
        commitBean = intent.getSerializableExtra("commitBean") as CommitBean?

        if (isModify == 0) {

        } else {

            val requestOptions = RequestOptions()
            requestOptions.error(R.mipmap.ic_head)
            requestOptions.placeholder(R.mipmap.ic_head)
            Glide.with(this).load(Urls.fileAccessHost + commitBean!!.zp + "&" + System.currentTimeMillis()).apply(requestOptions).into(iv_img)
        }

        val jsonBean = Gson().toJson(commitBean)
        Log.e("zj", "jsonBean22222 = " + jsonBean)
    }

    private fun initClick() {
        iv_img.setOnClickListener {
            showImgDialog()
        }
        iv_delete.setOnClickListener {
            imgPath = ""
            iv_delete.visibility = View.GONE
            iv_img.setImageResource(R.mipmap.ic_id_photo)
        }
        btn_next.setOnClickListener {
            Log.e("zj", "zp = " + commitBean!!.zp)
            if (commitBean!!.zp.isEmpty()) {
//                toast("请上传证件照")
                ToastUtil.showToastMessage(this@Collect3Activity, "请上传证件照",R.mipmap.toast_notice)
                return@setOnClickListener
            }

            if (intent.hasExtra("hasGuardian")) {

                var hasGuardian = intent.getIntExtra("hasGuardian", 0)
                if (hasGuardian == 1) {
                    val mIntent = Intent(this, Collect1_2Activity::class.java)
                    mIntent.putExtra("title", intent.getStringExtra("title"))
                    mIntent.putExtra("isModify", isModify)
                    mIntent.putExtra("dictionaryBean", dictionaryBean)

                    mIntent.putExtra("commitBean", commitBean)
                    mIntent.putExtra("type", type)

                    startActivity(mIntent)
                } else {
                    val mIntent = Intent(this, Collect2Activity::class.java)
                    mIntent.putExtra("title", intent.getStringExtra("title"))
                    mIntent.putExtra("isModify", isModify)
                    mIntent.putExtra("dictionaryBean", dictionaryBean)
                    mIntent.putExtra("type", type)

                    mIntent.putExtra("commitBean", commitBean)

                    startActivity(mIntent)
                }
            }

//            if (type == 0) {
//                net_addOrUpdate(commitBean!!);
//            } else {
//                val mIntent = Intent(this, Collect4Activity::class.java)
//                mIntent.putExtra("title", intent.getStringExtra("title"))
//                mIntent.putExtra("isModify", isModify)
//
//                mIntent.putExtra("commitBean", commitBean)
//
//                startActivity(mIntent)
//            }
        }
    }

    private fun showImgDialog() {
        val builder = AlertDialog.Builder(this@Collect3Activity)
        builder.setItems(arrayOf("相册", "拍照")) { _, p1 ->
            when (p1) {
                0 -> {
                    openSysAlbum()
                }
                1 -> {
                    openSysCamera()
                }
            }
        }
        dialog = builder.create()
        dialog!!.show()
    }

    private fun getImg() {
        //将Uri图片转换为Bitmap
        try {
            val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(mUritempFile))
            //TODO，将裁剪的bitmap显示在imageview控件上
            imgPath = getRealFilePath(mUritempFile)
            iv_img.setImageBitmap(bitmap)
            // iv_delete.visibility = View.VISIBLE
            compressImg()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

    }

    // 压缩图片
    private fun compressImg() {

        Log.e("zj", "imgPath = " + imgPath)
        Luban.with(this)
                .load(imgPath)
                .ignoreBy(50)
                .setTargetDir(Environment.getExternalStorageDirectory().absolutePath)
//                .setTargetDir(Environment.getExternalStorageDirectory().path)
                .setCompressListener(object : OnCompressListener {
                    override fun onStart() {

                    }

                    override fun onSuccess(file: File) {
                        imgPath = file.absolutePath
                        if (getPixColor(imgPath) && checkPixColor(imgPath)) {
                            net_UploadPhoto()
                        }
//                        getPixColor(imgPath)
//                        checkPixColor(imgPath)
//                        net_UploadPhoto()
                    }

                    override fun onError(e: Throwable) {
                        Log.e("zj", "file e = " + e.toString())

                    }
                }).launch()
    }

    // 测试获取像素点色值
    private fun getPixColor(path: String): Boolean {
        val src = BitmapFactory.decodeFile(path)
        var A: Int
        var R: Int
        var G: Int
        var B: Int
        var pixelColor: Int
        for (i in 0 until colorPixel.size) {
            pixelColor = src.getPixel(colorPixel[i][0], colorPixel[i][1])
            A = Color.alpha(pixelColor)
            R = Color.red(pixelColor)
            G = Color.green(pixelColor)
            B = Color.blue(pixelColor)
            // 判断不能有透明，色值在200以上，rgb三个值上下浮动在10以内
//            if (A != 255 || R < 200 || G < 200 || B < 200 ||
//                    ((R - B) > 15 || (B - R) > 15) ||
//                    ((R - G) > 15 || (G - R) > 15) ||
//                    ((G - B) > 15 || (B - G) > 15)) {
//                showErrDialog("图片背景不是白色")
//                break
//            }
            Log.e("zj", "R = " + R + ",G =" + G + ",B = " + B)
            if (R < 100 || G < 100 || B < 100) {
                showErrDialog("图片背景不是白色")
                return false
            }
        }
        return true
    }

    // 判断颜色是否黑白
    private fun checkPixColor(path: String): Boolean {
        val src = BitmapFactory.decodeFile(path)
        var A: Int
        var R: Int
        var G: Int
        var B: Int
        var pixelColor: Int
        val height = src.height
        val width = src.width
        for (x in 0 until width) {
            for (y in 0 until height) {
                pixelColor = src.getPixel(x, y)
                A = Color.alpha(pixelColor)
                R = Color.red(pixelColor)
                G = Color.green(pixelColor)
                B = Color.blue(pixelColor)
                if (R != B || G != B || R != G) {
                    return true
                }
            }
        }
        showErrDialog("照片不能是黑白照片")
        return false
    }

    private fun showErrDialog(msg: String) {
        val builder = AlertDialog.Builder(this@Collect3Activity)
        builder.setTitle("提示")
        builder.setMessage(msg)
        builder.setPositiveButton("确认") { p0, p1 ->
            imgPath = ""
            iv_delete.visibility = View.GONE
            iv_img.setImageResource(R.mipmap.ic_id_photo)
            p0.dismiss()
        }
        dialog = builder.create()
        dialog!!.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                requestCode_SysAlbum -> {
                    cropPic(commitBean!!.zjhm, data!!.data, 358, 441, 358, 441)
                }
                requestCode_SysCamera -> {
                    try {
                        // 刷新在系统相册中显示
                        MediaStore.Images.Media.insertImage(contentResolver,
                                MediaStore.Images.Media.getBitmap(this.contentResolver, mUriphotoFile),
                                resources.getString(R.string.app_name), "")
                        val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                        intent.data = mUriphotoFile
                        sendBroadcast(intent)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                    // 三星等手机拍照旋转90度
                    if (readPictureDegree(mStringphotoFile) != 0) {
                        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, mUriphotoFile)
                        val bitmap2 = toturn(bitmap)
                        saveBitmapFile(mStringphotoFile, bitmap2)
                    }
                    cropPic(commitBean!!.zjhm, mUriphotoFile, 358, 441, 358, 441)
                }
                requestCode_CropPic -> {
                    getImg()
                }
            }
        }
    }


    /**
     * 上传文件
     */
    private fun net_UploadPhoto() {
        val map = HashMap<String, File>()
        map["file"] = File(imgPath)
        NetTools.netFile("0", map, this) { response ->
            if (response.code == "2") {
                showErrDialog("" + response.msg)
            } else if (response.code == "0") {
                var file = File(imgPath)
                file.delete()
                var jsonObj = JSONObject(response.data)
                var imageUrl = jsonObj.getString("imageUrl")
                commitBean!!.zp = imageUrl
            }

        }
    }

    private fun net_addOrUpdate(commitBean: CommitBean) {
        val jsonBean = Gson().toJson(commitBean)
        val map = hashMapOf<String, String>()
        map["phone"] = "" + SPTools[this@Collect3Activity, Constant.PHONE, ""]
        map["cbInsured"] = "" + jsonBean

        Log.e("zj", "jsonBean = " + jsonBean)
        NetTools.net(map, Urls().addOrUpdate, this) { response ->
            Log.e("zj", "addOrUpdate = " + response.data)
            ToastUtil.showToastMessage(this@Collect3Activity, response.msg.toString(),R.mipmap.toast_notice)

            for (i in 0 until MyApplication.getActivies().size) {
                MyApplication.getActivies()[i].finish()
            }
            startActivity(Intent(this@Collect3Activity, MainActivity::class.java))
        }
    }

}
