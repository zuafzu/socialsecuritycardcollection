package com.hhkj.cyf.socialsecuritycardcollection.bean;

import java.io.Serializable;

public class LoginBean implements Serializable{

    /**
     * zhikajinduUrl : 1
     * phone : 13566664488
     * shebaoUrl : 1
     * userName : 不清楚
     * yibaoUrl : 1
     * headPhoto : null
     */

    private String zhikajinduUrl;
    private String phone;
    private String shebaoUrl;
    private String userName;
    private String yibaoUrl;
    private String headPhoto;
    private String token;

    public String getZhikajinduUrl() {
        return zhikajinduUrl;
    }

    public void setZhikajinduUrl(String zhikajinduUrl) {
        this.zhikajinduUrl = zhikajinduUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShebaoUrl() {
        return shebaoUrl;
    }

    public void setShebaoUrl(String shebaoUrl) {
        this.shebaoUrl = shebaoUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getYibaoUrl() {
        return yibaoUrl;
    }

    public void setYibaoUrl(String yibaoUrl) {
        this.yibaoUrl = yibaoUrl;
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "zhikajinduUrl='" + zhikajinduUrl + '\'' +
                ", phone='" + phone + '\'' +
                ", shebaoUrl='" + shebaoUrl + '\'' +
                ", userName='" + userName + '\'' +
                ", yibaoUrl='" + yibaoUrl + '\'' +
                ", headPhoto='" + headPhoto + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
