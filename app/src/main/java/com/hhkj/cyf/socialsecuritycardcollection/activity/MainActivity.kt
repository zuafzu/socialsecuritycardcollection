package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.widget.RadioButton
import android.widget.RadioGroup
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.fragment.Tab1Fragment
import com.hhkj.cyf.socialsecuritycardcollection.fragment.Tab2Fragment
import com.hhkj.cyf.socialsecuritycardcollection.fragment.Tab3Fragment
import org.jetbrains.anko.toast

class MainActivity : BaseActivity() , RadioGroup.OnCheckedChangeListener {

    private var mExitTime: Long = 0

    private var tab1Fragment: Tab1Fragment? = null
    private var tab2Fragment: Tab2Fragment? = null
    private var tab3Fragment: Tab3Fragment? = null


    private var group: RadioGroup? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }


    private fun initViews() {
        group = findViewById(R.id.main_tab_bar)
        group!!.setOnCheckedChangeListener(this)
        if (tab3Fragment == null) {
            tab3Fragment = Tab3Fragment()
            addFragment(tab3Fragment!!)
            showFragment(tab3Fragment!!)
        }
        if (tab2Fragment == null) {
            tab2Fragment = Tab2Fragment()
            addFragment(tab2Fragment!!)
            showFragment(tab2Fragment!!)
        }
        if (tab1Fragment == null) {
            tab1Fragment = Tab1Fragment()
            addFragment(tab1Fragment!!)
            showFragment(tab1Fragment!!)
        } else {
            showFragment(tab1Fragment!!)
        }
    }

    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        val childCount = group.childCount
        var checkedIndex = 0
        var btnButton: RadioButton? = null
        for (i in 0..childCount - 1) {
            btnButton = group.getChildAt(i) as RadioButton
            if (btnButton!!.isChecked()) {
                checkedIndex = i
                break
            }
        }

        val manager = supportFragmentManager
        when (checkedIndex) {
            0 -> if (tab1Fragment == null) {
                tab1Fragment = Tab1Fragment()
                // 判断当前界面是否隐藏，如果隐藏就进行添加显示，false表示显示，true表示当前界面隐藏
                addFragment(tab1Fragment!!)
                showFragment(tab1Fragment!!)
            } else {
                if (tab1Fragment!!.isHidden) {
                    showFragment(tab1Fragment!!)
                }
            }
            1 -> {
                if (tab2Fragment == null) {
                    tab2Fragment = Tab2Fragment()
                    // 判断当前界面是否隐藏，如果隐藏就进行添加显示，false表示显示，true表示当前界面隐藏
                    addFragment(tab2Fragment!!)
                    showFragment(tab2Fragment!!)
                } else {
                    if (tab2Fragment!!.isHidden()) {
                        showFragment(tab2Fragment!!)
                    }
                }

            }
            2 -> if (tab3Fragment == null) {
                tab3Fragment = Tab3Fragment()
                // 判断当前界面是否隐藏，如果隐藏就进行添加显示，false表示显示，true表示当前界面隐藏
                addFragment(tab3Fragment!!)
                showFragment(tab3Fragment!!)
            } else {
                if (tab3Fragment!!.isHidden()) {
                    showFragment(tab3Fragment!!)
                }
            }
            else -> {
            }
        }
    }

    /**
     * 添加Fragment
     */
    fun addFragment(fragment: Fragment) {
        val ft = this.supportFragmentManager.beginTransaction()
        ft.add(R.id.main_framelayout, fragment)
        ft.commit()
    }

    /**
     * 显示Fragment
     */
    fun showFragment(fragment: Fragment) {
        val ft = this.supportFragmentManager.beginTransaction()
        // 判断页面是否已经创建，如果已经创建，那么就隐藏掉
        if (tab1Fragment != null) {
            ft.hide(tab1Fragment!!)
        }
        if (tab2Fragment != null) {
            ft.hide(tab2Fragment!!)
        }
        if (tab3Fragment != null) {
            ft.hide(tab3Fragment!!)
        }
        ft.show(fragment)
        ft.commitAllowingStateLoss()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                toast("再按一次退出程序")
                mExitTime = System.currentTimeMillis()
            } else {
                finish()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
