package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.google.gson.Gson
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.bean.UpdateBean
import com.hhkj.cyf.socialsecuritycardcollection.constant.Constant
import com.hhkj.cyf.socialsecuritycardcollection.tools.CustomDialog
import com.hhkj.cyf.socialsecuritycardcollection.tools.NetTools.net
import com.hhkj.cyf.socialsecuritycardcollection.tools.UpdateTools
import com.hhkj.cyf.socialsecuritycardcollection.url.Urls

class WelcomeActivity : BaseActivity() {
    var versionBean: UpdateBean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        Handler().postDelayed({

//            val intent = Intent(this@WelcomeActivity, BootPageActivity::class.java)
//            startActivity(intent)
//            finish()

            net_appCheckUpdate()

        }, 500)
    }

    private fun net_appCheckUpdate() {

        val map = hashMapOf<String, String>()
        map["versionCode"] = getAppVersionCode()
        map["productName"] = Constant.PRODUCTNAME
        net(map, Urls().getNewVersion, this, { response ->
            Log.e("zj", "appCheckUpdate = " + response.data.toString())

            // ---------------cyf 测试使用--------------------
//            response.data = "{\"isUpdate\":1,\"msg\":\"测试更新\",\"state\":0,\"url\":\"http://192.168.11.2/mobile/high/app-debug.apk\",\"version\":3,\"versionName\":\"1.0.3\"}"
            // ---------------cyf 测试使用--------------------
            versionBean = Gson().fromJson(response.data, UpdateBean::class.java)

            Log.e("zj", "versionBean = " + versionBean.toString())
            if (null == response.data || "" == response.data || getAppVersionCode() == versionBean!!.versionCode) {
                val intent = Intent(this@WelcomeActivity, BootPageActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                showUpdateDialog()
            }
        }, "正在加载...", false, false)
    }


    private var isUpdata = false//是否点击更新

    private fun showUpdateDialog() {
        val builder = CustomDialog.Builder(this)
        // 0=普通更新，1=强制更新
        if ("1" == versionBean!!.updateStatus) {
            builder.setCancel(false)
            builder.setCenterButton("更新",
                    { dialog, which ->
                        isUpdata = true
                        dialog.dismiss()
                        UpdateTools.downLoadAPK(this@WelcomeActivity,Urls.file_url+ versionBean!!.fileUrl, true)
                    }, R.drawable.background_btn)
        } else {
            builder.setCancel(true)
            // background_btn蓝色，background_btn2灰色
            builder.setPositiveButton("取消",
                    { dialog, which ->
                        dialog.dismiss()
                        val intent = Intent(this@WelcomeActivity, BootPageActivity::class.java)
                        startActivity(intent)
                        finish()
                    }, R.drawable.background_btn)
            builder.setNegativeButton("更新",
                    { dialog, which ->
                        isUpdata = true
                        dialog.dismiss()
                        UpdateTools.downLoadAPK(this@WelcomeActivity, Urls.file_url+versionBean!!.fileUrl, false)
                    }, R.drawable.background_btn)
        }
        builder.setTitle("检查到更新：" + versionBean!!.versionName + "版本")
        builder.setMessage(versionBean!!.versionDesc)
        val dialog = builder.create()
        // 问题出在这，不要这个逻辑代码了，放到setPositiveButton这个方法里了
//        if ("0" == versionBean!!.updateStatus) {
//            dialog.setOnDismissListener {
//                val intent = Intent(this@WelcomeActivity, BootPageActivity::class.java)
//                startActivity(intent)
//                finish()
//            }
//        }
        dialog.show()
    }
}
