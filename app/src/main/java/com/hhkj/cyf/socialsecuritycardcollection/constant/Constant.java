package com.hhkj.cyf.socialsecuritycardcollection.constant;

import com.hhkj.cyf.socialsecuritycardcollection.bean.SelectItemBean;

import java.util.ArrayList;

public class Constant {

    /**
     * 性别
     * @return
     */
    public static ArrayList<SelectItemBean> getSexs() {
        ArrayList<SelectItemBean> list = new ArrayList<>();
        list.add(new SelectItemBean("男", "1"));
        list.add(new SelectItemBean("女", "2"));
        list.add(new SelectItemBean("未说明性别", "9"));
        return list;
    }

    /**
     * 证件类型
     * @return
     */
    public static ArrayList<SelectItemBean> getCardType() {
        ArrayList<SelectItemBean> list = new ArrayList<>();
        list.add(new SelectItemBean("身份证", "A"));
        list.add(new SelectItemBean("临时身份证", "B"));
        list.add(new SelectItemBean("户口本", "C"));
        list.add(new SelectItemBean("军官证", "D"));
        list.add(new SelectItemBean("士兵证", "E"));
        list.add(new SelectItemBean("警官证", "F"));
        list.add(new SelectItemBean("港澳台通行证", "G"));
        list.add(new SelectItemBean("护照", "H"));
        list.add(new SelectItemBean("未确定证件", "J"));
        return list;
    }

    /**
     * 民族
     * @return
     */
    public static ArrayList<SelectItemBean> getNationality() {
        ArrayList<SelectItemBean> list = new ArrayList<>();
        list.add(new SelectItemBean("汉族", "01"));
        list.add(new SelectItemBean("蒙古族", "02"));
        list.add(new SelectItemBean("回族", "03"));
        list.add(new SelectItemBean("藏族", "04"));
        list.add(new SelectItemBean("维吾尔族", "05"));
        list.add(new SelectItemBean("苗族", "06"));
        return list;
    }

}
