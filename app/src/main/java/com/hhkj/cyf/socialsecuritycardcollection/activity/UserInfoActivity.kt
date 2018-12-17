package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import kotlinx.android.synthetic.main.activity_user_info.*
import java.io.FileNotFoundException

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
        builder.create().show()
    }

    private fun getImg() {
        //将Uri图片转换为Bitmap
        try {
            val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(mUritempFile))
            //TODO，将裁剪的bitmap显示在imageview控件上
            imgPath = getRealFilePath(mUritempFile)
            iv_img.setImageBitmap(bitmap)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                requestCode_SysAlbum -> {
                    cropPic(data!!.data, 1, 1, 512, 512)
                }
                requestCode_SysCamera -> {
                    // 三星等手机拍照旋转90度
                    if (readPictureDegree(mStringphotoFile) != 0) {
                        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, mUriphotoFile)
                        val bitmap2 = toturn(bitmap)
                        saveBitmapFile(mStringphotoFile, bitmap2)
                    }
                    cropPic(mUriphotoFile, 1, 1, 512, 512)
                }
                requestCode_CropPic -> {
                    getImg()
                }
            }
        }
    }

}
