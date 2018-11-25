package com.hhkj.cyf.socialsecuritycardcollection.base

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.hhkj.cyf.socialsecuritycardcollection.R
import java.io.File
import android.content.ContentResolver


open class BaseActivity : AppCompatActivity() {

    var progressDialog: ProgressDialog? = null
    val requestCode_SysAlbum = 0
    val requestCode_SysCamera = 1
    val requestCode_CropPic = 2
    var mUriphotoFile: Uri? = null
    var mUritempFile: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("1221", javaClass.simpleName)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * 修改标题

     * @param title 标题名
     */
    protected fun setTextTitle(title: String) {
        (findViewById<TextView>(R.id.tv_toolsbar_title)).text = title
    }

    /**
     * 修改左按钮

     * @param flag 是否显示
     */
    protected fun setLeftBtn(flag: Boolean) = if (flag) {
        (findViewById<TextView>(R.id.tv_toolsbar_left)).visibility = View.VISIBLE
        (findViewById<TextView>(R.id.tv_toolsbar_left)).setOnClickListener {
            hideSoftInput()
            finish()
        }
    } else {
        (findViewById<TextView>(R.id.tv_toolsbar_left)).visibility = View.GONE
    }

    /**
     * 修改右按钮(文字按钮)

     * @param flag     是否显示
     * *
     * @param string   按钮名
     * *
     * @param listener 点击事件
     */
    protected fun setRightBtn(flag: Boolean, string: String, listener: View.OnClickListener) {
        if (flag) {
            (findViewById<TextView>(R.id.tv_toolsbar_right) as TextView).text = string
            (findViewById<TextView>(R.id.tv_toolsbar_right) as TextView).visibility = View.VISIBLE
            (findViewById<TextView>(R.id.tv_toolsbar_right) as TextView).setOnClickListener(listener)
        } else {
            (findViewById<TextView>(R.id.tv_toolsbar_right) as TextView).visibility = View.GONE
        }
    }


    /**
     * 设置右上角图片按钮

     * @param isShow
     * *
     * @param resId
     * *
     * @param onClickListener
     */
    protected fun setRightButton(isShow: Boolean, resId: Int, onClickListener: View.OnClickListener) {
        val iv_right = findViewById<TextView>(R.id.iv_right) as ImageView
        if (iv_right != null) {
            if (isShow) {
                iv_right!!.visibility = View.VISIBLE
                iv_right!!.setImageResource(resId)
                iv_right!!.setOnClickListener(onClickListener)
            } else {
                iv_right!!.visibility = View.GONE
            }
        }
    }

    /**
     * 修改右按钮(纯图按钮)

     * @param flag     是否显示
     * *
     * @param resId    按钮图
     * *
     * @param listener 点击事件
     */
    protected fun setRightBtn(flag: Boolean, resId: Int, listener: View.OnClickListener) {
        if (flag) {
            (findViewById<TextView>(R.id.tv_toolsbar_right) as TextView).text = ""
            val layoutParams = (findViewById<TextView>(R.id.tv_toolsbar_right) as TextView).layoutParams as LinearLayout.LayoutParams
            layoutParams.height = layoutParams.height - dip2px(28f)
            layoutParams.width = layoutParams.height
            layoutParams.gravity = Gravity.CENTER_VERTICAL
            layoutParams.setMargins(0, 0, dip2px(12f), 0)
            (findViewById<TextView>(R.id.tv_toolsbar_right) as TextView).layoutParams = layoutParams
            (findViewById<TextView>(R.id.tv_toolsbar_right) as TextView).setBackgroundResource(resId)
            (findViewById<TextView>(R.id.tv_toolsbar_right) as TextView).visibility = View.VISIBLE
            (findViewById<TextView>(R.id.tv_toolsbar_right) as TextView).setOnClickListener(listener)
        } else {
            (findViewById<TextView>(R.id.tv_toolsbar_right) as TextView).visibility = View.GONE
        }
    }

    open fun showProgressDialog() {
        showProgressDialog("加载中...")
    }

    open fun showProgressDialog(msg: String) {
        try {
            if (progressDialog == null) {
                progressDialog = ProgressDialog(this)
            }
            if (progressDialog != null && !progressDialog!!.isShowing) {
                progressDialog!!.setCanceledOnTouchOutside(false)
                progressDialog!!.setMessage(msg)
                progressDialog!!.show()
            }
        } catch (e: Exception) {

        }
    }

    open fun dismissProgressDialog() {
        try {
            if (progressDialog != null && progressDialog!!.isShowing) {
                progressDialog!!.dismiss()
            }
        } catch (e: Exception) {

        }
    }

    /**
     * 收起键盘
     */
    protected fun hideSoftInput() {
        try {
            val mInputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            mInputMethodManager.hideSoftInputFromWindow(this.currentFocus!!.windowToken, 0)
        } catch (e: Exception) {

        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(dpValue: Float): Int {
        val scale = resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 返回当前程序版本名
     */
    fun getAppVersionName(): String {
        var versionName: String? = ""
        try {
            // ---get the package info---
            val pm = packageManager
            val pi = pm.getPackageInfo(packageName, 0)
            versionName = pi.versionName
            if (versionName == null || versionName.isEmpty()) {
                return ""
            }
        } catch (e: Exception) {
            Log.e("VersionInfo", "Exception", e)
        }

        return versionName!!
    }

    /**
     * 返回当前程序版本
     */
    fun getAppVersionCode(): String {
        var versionId = 0
        try {
            // ---get the package info---
            val pm = packageManager
            val pi = pm.getPackageInfo(packageName, 0)
            versionId = pi.versionCode
            if (versionId <= 0) {
                return ""
            }
        } catch (e: Exception) {
            Log.e("VersionInfo", "Exception", e)
        }

        return "$versionId"
    }

    /**
     * 打开系统相册
     */
    fun openSysAlbum() {
        val albumIntent = Intent(Intent.ACTION_PICK)
        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        startActivityForResult(albumIntent, requestCode_SysAlbum)
    }

    /**
     * 打开系统相机
     */
    fun openSysCamera() {
        mUriphotoFile = Uri.fromFile(File(Environment.getExternalStorageDirectory(), "small1.png"))
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mUriphotoFile)
        startActivityForResult(cameraIntent, requestCode_SysCamera)
    }

    /**
     * 裁剪图片
     *
     * @param data
     */
    fun cropPic(data: Uri?, aspectX: Int, aspectY: Int, outputX: Int, outputY: Int) {
        if (data == null) {
            return
        }
        val cropIntent = Intent("com.android.camera.action.CROP")
        cropIntent.setDataAndType(data, "image/*")

        // 开启裁剪：打开的Intent所显示的View可裁剪
        cropIntent.putExtra("crop", "true")
        // 裁剪宽高比
        cropIntent.putExtra("aspectX", aspectX)
        cropIntent.putExtra("aspectY", aspectY)
        // 裁剪输出大小
        cropIntent.putExtra("outputX", outputX)
        cropIntent.putExtra("outputY", outputY)
        cropIntent.putExtra("scale", true)
        /**
         * return-data
         * 这个属性决定我们在 onActivityResult 中接收到的是什么数据，
         * 如果设置为true 那么data将会返回一个bitmap
         * 如果设置为false，则会将图片保存到本地并将对应的uri返回，当然这个uri得有我们自己设定。
         * 系统裁剪完成后将会将裁剪完成的图片保存在我们所这设定这个uri地址上。我们只需要在裁剪完成后直接调用该uri来设置图片，就可以了。
         */
        // cropIntent.putExtra("return-data", true)
        // 当 return-data 为 false 的时候需要设置这句
        cropIntent.putExtra("return-data", false)
        mUritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().path + "/" + "small2.jpg")
        cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, mUritempFile)
        // 图片输出格式
        cropIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        // 头像识别 会启动系统的拍照时人脸识别
        //        cropIntent.putExtra("noFaceDetection", true);
        startActivityForResult(cropIntent, requestCode_CropPic)
    }

    /**
     * 通过Uri获取文件路径
     */
    fun getRealFilePath(uri: Uri?): String {
        if (null == uri) return ""
        val scheme = uri.scheme
        var data: String? = ""
        if (scheme == null)
            data = uri.path
        else if (ContentResolver.SCHEME_FILE == scheme) {
            data = uri.path
        } else if (ContentResolver.SCHEME_CONTENT == scheme) {
            val cursor = contentResolver.query(uri, arrayOf(MediaStore.Images.ImageColumns.DATA), null, null, null)
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    val index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                    if (index > -1) {
                        data = cursor.getString(index)
                    }
                }
                cursor.close()
            }
        }
        return data!!
    }

}
