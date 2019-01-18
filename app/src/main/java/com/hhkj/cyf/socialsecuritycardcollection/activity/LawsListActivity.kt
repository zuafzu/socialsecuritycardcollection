package com.hhkj.cyf.socialsecuritycardcollection.activity

import android.content.Intent
import android.os.Bundle
import com.hhkj.cyf.socialsecuritycardcollection.R
import com.hhkj.cyf.socialsecuritycardcollection.adapter.LawsListAdapter
import com.hhkj.cyf.socialsecuritycardcollection.base.BaseActivity
import com.hhkj.cyf.socialsecuritycardcollection.bean.LawsListBean
import kotlinx.android.synthetic.main.activity_laws_list.*

class LawsListActivity : BaseActivity() {

    private var lawsList: MutableList<LawsListBean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laws_list)
        initView()
        setData(intent.getStringExtra("id"),intent.getStringExtra("pos"))
    }

    private fun initView() {
        setTextTitle(intent.getStringExtra("title"))
        setLeftBtn(true)
        listView.setOnItemClickListener { adapterView, view, i, l ->

            val mIntent = Intent(this, Law1Activity::class.java)
            mIntent.putExtra("title", lawsList!![i].title)
            mIntent.putExtra("url", lawsList!![i].url)
            startActivity(mIntent)
        }
    }

    private fun setData(id: String, pos: String) {
        lawsList = ArrayList()
        if (pos == "0") {
            when (id) {
                "1" -> {
                    val homeBean1 = LawsListBean("长春市人民政府办公厅关于印发长春市统一被征地农民基本养老保险制度实施方案实施细则的通知（2012年11月19日长春市人民政府办公厅 长府办发[2012]44号公布）", "file:///android_asset/社保/被征地农民养老保险/长春市人民政府办公厅关于印发长春市统一被征地农民基本养老保险制度实施方案实施细则的通知（2012年11月19日长春市人民政府办公厅 长府办发[2012]44号公布）/sb_11.html")
                    lawsList!!.add(homeBean1)
                }
                "2" -> {
                    val homeBean1 = LawsListBean("吉林省关于调整城乡居民基本养老保险基础养老金标准、个人缴费标准的通知", "file:///android_asset/社保/城乡居民养老保险/吉林省关于调整城乡居民基本养老保险基础养老金标准、个人缴费标准的通知/sb_21.html")
                    val homeBean2 = LawsListBean("吉林省关于建立城乡居民基本养老保险待遇确定和基础养老金正常调整机制的实施意见", "file:///android_asset/社保/城乡居民养老保险/吉林省关于建立城乡居民基本养老保险待遇确定和基础养老金正常调整机制的实施意见/sb_22.html")
                    val homeBean3 = LawsListBean("长春市人民政府关于印发长春市统一城乡居民基本养老保险实施方案的通知（长府发[2014]19 号）", "file:///android_asset/社保/城乡居民养老保险/长春市人民政府关于印发长春市统一城乡居民基本养老保险实施方案的通知（长府发[2014]19 号）/sb_23.html")
                    lawsList!!.add(homeBean1)
                    lawsList!!.add(homeBean2)
                    lawsList!!.add(homeBean3)
                }
                "3" -> {
                    val homeBean1 = LawsListBean("关于进一步加强企业职工基本养老保险省级统筹行政管理工作的通知", "file:///android_asset/社保/城镇企业职工基本养老保险/关于进一步加强企业职工基本养老保险省级统筹行政管理工作的通知/sb_31.html")
                    val homeBean2 = LawsListBean("人力资源社会保障部办公厅财政部办公厅关于公布2016年职工基本养老保险个人账户记账利率等参数的通知（人社厅发[2017]71号）", "file:///android_asset/社保/城镇企业职工基本养老保险/人力资源社会保障部办公厅财政部办公厅关于公布2016年职工基本养老保险个人账户记账利率等参数的通知（人社厅发[2017]71号）/sb_32.html")
                    val homeBean3 = LawsListBean("长春社保2018年第1号文", "file:///android_asset/社保/城镇企业职工基本养老保险/长春社保2018年第1号文/sb_33.html")
                    lawsList!!.add(homeBean1)
                    lawsList!!.add(homeBean2)
                    lawsList!!.add(homeBean3)
                }
                "4" -> {
                    val homeBean1 = LawsListBean("人力资源社会保障部办公厅关于印发《机关事业单位基本养老保险关系和职业年金转移接续经办规程（暂行）》的通知", "file:///android_asset/社保/机关事业单位养老保险/人力资源社会保障部办公厅关于印发《机关事业单位基本养老保险关系和职业年金转移接续经办规程（暂行）》的通知/sb_41.html")
                    val homeBean2 = LawsListBean("人社部财政部关于贯彻落实《国务院关于机关事业单位工作人员养老保险制度改革的决定》的通知", "file:///android_asset/社保/机关事业单位养老保险/人社部财政部关于贯彻落实《国务院关于机关事业单位工作人员养老保险制度改革的决定》的通知/sb_42.html")
                    val homeBean3 = LawsListBean("人社部规[2017]1号 人力资源和社会保障部、财政部关于机关事业单位基本养老保险关系和职业年金转移接续有关问题的通知", "file:///android_asset/社保/机关事业单位养老保险/人社部规[2017]1号 人力资源和社会保障部、财政部关于机关事业单位基本养老保险关系和职业年金转移接续有关问题的通知/sb_43.html")
                    lawsList!!.add(homeBean1)
                    lawsList!!.add(homeBean2)
                    lawsList!!.add(homeBean3)
                }
                "5" -> {
                    val homeBean1 = LawsListBean("关于失业保险支持参保职工提升职业技能有关问题的补充通知", "file:///android_asset/社保/失业保险/关于失业保险支持参保职工提升职业技能有关问题的补充通知/sb_51.html")
                    val homeBean2 = LawsListBean("关于失业保险支持参保职工提升职业技能有关问题的通知", "file:///android_asset/社保/失业保险/关于失业保险支持参保职工提升职业技能有关问题的通知/sb_52.html")
                    val homeBean3 = LawsListBean("关于提高失业保险金标准的通知（吉人社办字[2017]21号）", "file:///android_asset/社保/失业保险/关于提高失业保险金标准的通知（吉人社办字[2017]21号）/sb_53.html")
                    val homeBean4 = LawsListBean("关于提高失业保险金标准的通知（吉人社办字〔2018〕47号）", "file:///android_asset/社保/失业保险/关于提高失业保险金标准的通知（吉人社办字〔2018〕47号）/sb_54.html")
                    lawsList!!.add(homeBean1)
                    lawsList!!.add(homeBean2)
                    lawsList!!.add(homeBean3)
                    lawsList!!.add(homeBean4)
                }
                "6" -> {
                    val homeBean1 = LawsListBean("关于领取失业保险金人员参加职工基本医疗保险有关问题的通知", "file:///android_asset/社保/其他/关于领取失业保险金人员参加职工基本医疗保险有关问题的通知/sb_61.html")
                    val homeBean2 = LawsListBean("长春市企业退休人员档案管理中心档案利用的意见", "file:///android_asset/社保/其他/长春市企业退休人员档案管理中心档案利用的意见/sb_62.html")
                    val homeBean3 = LawsListBean("长春市人民政府办公厅关于转发长春市企业退休人员档案管理中心档案实行集中统一管理有关有关问题的通知", "file:///android_asset/社保/其他/长春市人民政府办公厅关于转发长春市企业退休人员档案管理中心档案实行集中统一管理有关有关问题的通知/sb_63.html")
                    val homeBean4 = LawsListBean("转发人力资源和社会保障部、财政部《关于领取失业保险金人员参加职工基本医疗保险有关问题的通知》的通知", "file:///android_asset/社保/其他/转发人力资源和社会保障部、财政部《关于领取失业保险金人员参加职工基本医疗保险有关问题的通知》的通知/sb_64.html")
                    lawsList!!.add(homeBean1)
                    lawsList!!.add(homeBean2)
                    lawsList!!.add(homeBean3)
                    lawsList!!.add(homeBean4)
                }
            }
        } else if (pos == "1") {
            when (id) {
                "1" -> {
                    val homeBean1 = LawsListBean("部分行业企业工伤保险费缴纳办法", "file:///android_asset/医保/工伤保险/部分行业企业工伤保险费缴纳办法/yb_11.html")
                    val homeBean2 = LawsListBean("关于进一步做好建筑施工企业农民工参加工伤保险工作的通知", "file:///android_asset/医保/工伤保险/关于进一步做好建筑施工企业农民工参加工伤保险工作的通知/yb_12.html")
                    val homeBean3 = LawsListBean("关于调整工伤保险待遇的通知", "file:///android_asset/医保/工伤保险/关于调整工伤保险待遇的通知/yb_13.html")
                    val homeBean4 = LawsListBean("吉林省工伤康复管理暂行办法", "file:///android_asset/医保/工伤保险/吉林省工伤康复管理暂行办法/yb_14.html")
                    val homeBean5 = LawsListBean("长春市基本医疗、工伤、生育保险待遇支付与审批的管理细则", "file:///android_asset/医保/工伤保险/长春市基本医疗、工伤、生育保险待遇支付与审批的管理细则/yb_15.html")
                    val homeBean6 = LawsListBean("长春市人民政府办公厅关于调整全市工伤职工伤残津贴标准和生活护理费标准的通知", "file:///android_asset/医保/工伤保险/长春市人民政府办公厅关于调整全市工伤职工伤残津贴标准和生活护理费标准的通知/yb_16.html")
                    lawsList!!.add(homeBean1)
                    lawsList!!.add(homeBean2)
                    lawsList!!.add(homeBean3)
                    lawsList!!.add(homeBean4)
                    lawsList!!.add(homeBean5)
                    lawsList!!.add(homeBean6)
                }
                "2" -> {
                    val homeBean1 = LawsListBean("关于调整2016年城镇居民基本医疗保险缴费及补助标准的通知", "file:///android_asset/医保/居民医疗保险/关于调整2016年城镇居民基本医疗保险缴费及补助标准的通知/yb_21.html")
                    val homeBean2 = LawsListBean("关于印发《长春市失能人员医疗照护保险实施办法（试行）》的通知", "file:///android_asset/医保/居民医疗保险/关于印发《长春市失能人员医疗照护保险实施办法（试行）》的通知/yb_22.html")
                    val homeBean3 = LawsListBean("长春市人民政府办公厅关于建立失能人员医疗照护保险制度的意见", "file:///android_asset/医保/居民医疗保险/长春市人民政府办公厅关于建立失能人员医疗照护保险制度的意见/yb_23.html")

                    lawsList!!.add(homeBean1)
                    lawsList!!.add(homeBean2)
                    lawsList!!.add(homeBean3)
                }
                "3" -> {
                    val homeBean1 = LawsListBean("关于生育保险项目纳入定点医疗机构管理的通知", "file:///android_asset/医保/生育保险/关于生育保险项目纳入定点医疗机构管理的通知/yb_31.html")
                    val homeBean2 = LawsListBean("关于育产期女职工围产保健待遇结算的通知", "file:///android_asset/医保/生育保险/关于育产期女职工围产保健待遇结算的通知/yb_32.html")
                    val homeBean3 = LawsListBean("长春市人民政府办公厅关于实施《吉林省城镇职工生育保险办法》的通知", "file:///android_asset/医保/生育保险/长春市人民政府办公厅关于实施《吉林省城镇职工生育保险办法》的通知/yb_33.html")
                    val homeBean4 = LawsListBean("长春市人民政府办公厅关于调整和增加城镇职工生育保险有关待遇的通知", "file:///android_asset/医保/生育保险/长春市人民政府办公厅关于调整和增加城镇职工生育保险有关待遇的通知/yb_34.html")
                    val homeBean5 = LawsListBean("长春市人民政府办公厅关于调整完善城镇职工和居民基本医疗保险有关政策的通知", "file:///android_asset/医保/生育保险/长春市人民政府办公厅关于调整完善城镇职工和居民基本医疗保险有关政策的通知/yb_35.html")
                    val homeBean6 = LawsListBean("长春市人民政府办公厅关于印发长春市城镇居民生育保险试点实施方案的通知", "file:///android_asset/医保/生育保险/长春市人民政府办公厅关于印发长春市城镇居民生育保险试点实施方案的通知/yb_36.html")
                    lawsList!!.add(homeBean1)
                    lawsList!!.add(homeBean2)
                    lawsList!!.add(homeBean3)
                    lawsList!!.add(homeBean4)
                    lawsList!!.add(homeBean5)
                    lawsList!!.add(homeBean6)
                }
                "4" -> {
                    val homeBean1 = LawsListBean("关于印发《长春市失能人员医疗照护保险实施办法（试行）》的通知", "file:///android_asset/医保/职工医疗保险/关于印发《长春市失能人员医疗照护保险实施办法（试行）》的通知/yb_41.html")
                    val homeBean2 = LawsListBean("人力资源社会保障部关于进一步加强基本医疗保险医疗服务监管的意见", "file:///android_asset/医保/职工医疗保险/人力资源社会保障部关于进一步加强基本医疗保险医疗服务监管的意见/yb_42.html")
                    val homeBean3 = LawsListBean("人力资源社会保障部关于完善基本医疗保险定点医药机构协议管理的指导意见", "file:///android_asset/医保/职工医疗保险/人力资源社会保障部关于完善基本医疗保险定点医药机构协议管理的指导意见/yb_43.html")
                    val homeBean4 = LawsListBean("长春市人民政府办公厅关于建立失能人员医疗照护保险制度的意见", "file:///android_asset/医保/职工医疗保险/长春市人民政府办公厅关于建立失能人员医疗照护保险制度的意见/yb_44.html")
                    lawsList!!.add(homeBean1)
                    lawsList!!.add(homeBean2)
                    lawsList!!.add(homeBean3)
                    lawsList!!.add(homeBean4)
                }

            }

        }
        listView.adapter = LawsListAdapter(lawsList, this)
    }

}
