package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hhkj.cyf.socialsecuritycardcollection.R

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        startActivity(Intent(this,BootPageActivity::class.java))
        finish()
    }
}
