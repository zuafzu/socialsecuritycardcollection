package com.hhkj.cyf.socialsecuritycardcollection.url;

public class Urls {
    public final static String ip = "218.27.84.59:18085";
//    public final static String ip = "124.235.218.193:8081";
//    public final static String ip = "124.235.218.86:8081";
    public final static String file_url = "http://"+ip;
    public final static String base_url = "http://"+ip+"/sjcj_web/phoneCon/";
//    public final static String base_url = "http://192.168.1.53:8080/sjcj_web/phoneCon/";

    public final static String fileAccessHost  = "http://"+ip+"/sjcj_web/kssh/getImg.do?cx=";

    public static String url_help_collect = "file:///android_asset/gr.html";// 个人帮助
    public static String url_help_collect2 = "file:///android_asset/db.html";// 采集帮助

    public final String auth_login = base_url + "login.do";// 登录
    public final String selfQuery = base_url + "selfQuery.do";// 采集进度查询(查询自己)
    public final String getUrls = base_url + "getUrls.do";// 网址
    public final String getWriteCount = base_url + "getWriteCount.do";// 接口名称: 获取已经录入数量
    public final String updateNickName = base_url + "updateNickName.do";// 更改昵称
    public final String suggest = base_url + "suggest.do";// 意见反馈
    public final String updatePassword = base_url + "updatePassword.do";// 改密码
    public final String idCardQuery = base_url + "idCardQuery.do";// 身份证查询

    public final String uploadPhoto = base_url + "uploadPhoto.do";// 上传图片
    public final String getDictionary = base_url + "getDictionary.do";// 国标项

    public final String scanQuery = base_url + "scanQuery.do";// 接口名称: 扫描身份证是否可用
    public final String scanCountQuery = base_url + "scanCountQuery.do";// 接口名称: 扫描身份证使用
    public final String forgetPassword = base_url + "forgetPassword.do";// 忘记密码
    public final String getCode = base_url + "getCode.do";// 获取验证码
    public final String register = base_url + "register.do";// 注册
    public final String uploadHeadPhoto = base_url + "uploadHeadPhoto.do";// 上传头像
    public final String addOrUpdate = base_url + "addOrUpdate.do";// 录入和修改

    public final String allPeopleQuery = base_url + "allPeopleQuery.do";// 采集进度查询
    public final String queryDetail = base_url + "queryDetail.do";// 接口名称: 查看详情
    public final String getNewVersion = base_url + "getNewVersion.do";// 接口名称: 查看详情
    public final String url_register_agreement = base_url + "toAgreement.do";// 隐私协议
}
