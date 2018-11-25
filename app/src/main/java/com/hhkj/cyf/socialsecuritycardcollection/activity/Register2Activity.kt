package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import kotlinx.android.synthetic.main.activity_register2.*
import java.io.FileNotFoundException


class Register2Activity : BaseActivity() {

    private var imgPath = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)
        initView()
        initClick()
    }

    private fun initView() {
        setLeftBtn(true)
        setTextTitle("创建账户")
    }

    private fun initClick() {
        iv_img.setOnClickListener {
            showImgDialog()
        }
        iv_delete.setOnClickListener {
            imgPath = ""
            iv_delete.visibility = View.GONE
            iv_img.setImageResource(R.mipmap.ic_shenfenzheng)
        }
        ll_area.setOnClickListener {

        }
        iv_arr1.setOnClickListener {
            if(ll_main1.visibility == View.GONE){
                iv_arr1.setImageResource(R.mipmap.ic_uparr)
                ll_main1.visibility = View.VISIBLE
            }else{
                iv_arr1.setImageResource(R.mipmap.ic_downarr)
                ll_main1.visibility = View.GONE
            }
        }
        iv_arr2.setOnClickListener {
            if(ll_main2.visibility == View.GONE){
                iv_arr2.setImageResource(R.mipmap.ic_uparr)
                ll_main2.visibility = View.VISIBLE
            }else{
                iv_arr2.setImageResource(R.mipmap.ic_downarr)
                ll_main2.visibility = View.GONE
            }
        }
        iv_arr3.setOnClickListener {
            if(ll_main3.visibility == View.GONE){
                iv_arr3.setImageResource(R.mipmap.ic_uparr)
                ll_main3.visibility = View.VISIBLE
            }else{
                iv_arr3.setImageResource(R.mipmap.ic_downarr)
                ll_main3.visibility = View.GONE
            }
        }
    }

    private fun showImgDialog() {
        val builder = AlertDialog.Builder(this@Register2Activity)
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
            iv_delete.visibility = View.VISIBLE
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                requestCode_SysAlbum -> {
                    cropPic(data!!.data, 1, 2, 320, 640)
                }
                requestCode_SysCamera -> {
                    cropPic(mUriphotoFile, 1, 2, 320, 640)
                }
                requestCode_CropPic -> {
                    getImg()
                }
            }
        }
    }

}
