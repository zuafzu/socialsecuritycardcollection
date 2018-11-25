package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import kotlinx.android.synthetic.main.activity_collect.*
import java.io.FileNotFoundException

class CollectActivity : BaseActivity() {

    private var imgPath = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect)
        initView()
        initClick()
    }

    private fun initView() {
        setLeftBtn(true)
        setTextTitle(intent.getStringExtra("title"))
    }

    private fun initClick() {
        iv_img.setOnClickListener {
            showImgDialog()
        }
        iv_delete.setOnClickListener {
            imgPath = ""
            iv_delete.visibility = View.GONE
            iv_img.setImageResource(R.mipmap.ic_add_img)
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
        val builder = AlertDialog.Builder(this@CollectActivity)
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
                    cropPic(data!!.data, 358, 441, 358, 441)
                }
                requestCode_SysCamera -> {
                    cropPic(mUriphotoFile, 358, 441, 358, 441)
                }
                requestCode_CropPic -> {
                    getImg()
                }
            }
        }
    }

}
