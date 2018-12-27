package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.bean.BaseBean
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant
import com.hhkj.cyf.socialsecuritycardcollection.tools.NetTools
import com.hhkj.cyf.socialsecuritycardcollection.tools.SPTools
import com.hhkj.cyf.socialsecuritycardcollection.url.Urls
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
import org.json.JSONObject
import java.io.File
import java.io.FileNotFoundException
import java.util.ArrayList
import java.util.HashMap

class UserInfoActivity : BaseActivity() {

    private var imgPath = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        initView()
        initClick()
    }

    private fun initView() {
        setLeftBtn(true)
        setTextTitle("完善个人信息")

        val requestOptions = RequestOptions()
        requestOptions.error(R.mipmap.ic_head)
        requestOptions.placeholder(R.mipmap.ic_head)
//        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);///不使用磁盘缓存
//        requestOptions.skipMemoryCache(true); // 不使用内存缓存
        Glide.with(this@UserInfoActivity).load(SPTools[this@UserInfoActivity, Constant.HEADPHOTO, ""].toString() + "&" + System.currentTimeMillis()).apply(requestOptions).into(iv_img)

    }

    override fun onResume() {
        super.onResume()
        tv_nickName.text = "" + SPTools[this@UserInfoActivity, Constant.USERNAME, ""]
    }

    private fun initClick() {
        ll_img.setOnClickListener {
            showImgDialog()
        }
        ll_name.setOnClickListener {
            startActivity(Intent(this, EditUserNameActivity::class.java))
        }
    }

    private fun showImgDialog() {
        val builder = AlertDialog.Builder(this@UserInfoActivity)
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
//            iv_img.setImageBitmap(bitmap)
            net_UploadPhoto()

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                requestCode_SysAlbum -> {
                    cropPic("" + SPTools[this@UserInfoActivity, Constant.PHONE, ""], data!!.data, 1, 1, 512, 512)
                }
                requestCode_SysCamera -> {
                    // 三星等手机拍照旋转90度
                    if (readPictureDegree(mStringphotoFile) != 0) {
                        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, mUriphotoFile)
                        val bitmap2 = toturn(bitmap)
                        saveBitmapFile(mStringphotoFile, bitmap2)
                    }
                    cropPic("" + SPTools[this@UserInfoActivity, Constant.PHONE, ""], mUriphotoFile, 1, 1, 512, 512)
                }
                requestCode_CropPic -> {
                    getImg()
                }
            }
        }
    }

    /**
     * 上传头像文件
     */
    private fun net_UploadPhoto() {
        val map = HashMap<String, File>()
        map["file"] = File(imgPath)
        NetTools.netFile("1", map, this) { response ->

            var jsonObj = JSONObject(response.data)
            var imageUrl = jsonObj.getString("imageUrl")

            val requestOptions = RequestOptions()
            requestOptions.error(R.mipmap.ic_head)
            requestOptions.placeholder(R.mipmap.ic_head)
//            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);///不使用磁盘缓存
//            requestOptions.skipMemoryCache(true); // 不使用内存缓存

            Glide.with(this).load(Urls.fileAccessHost + imageUrl + "&" + System.currentTimeMillis()).apply(requestOptions).into(iv_img)

            toast("" + response.msg)
        }
    }

}
