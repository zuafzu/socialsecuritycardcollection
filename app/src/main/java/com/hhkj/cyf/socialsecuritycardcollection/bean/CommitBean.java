package com.hhkj.cyf.socialsecuritycardcollection.bean;

import java.io.Serializable;

public class CommitBean implements Serializable{

    /**
     * 表格编号
     */
    private String bgbh;
    /**
     * 登记日期
     */
    private String djrq;// 登记日期
    /**
     * 所属城市
     */
    private String sscs;// 所属城市
    /**
     * 所属机构
     */
    private String ssjg;// 所属机构
    /**
     * 证件号码
     */
    private String zjhm;// 证件号码
    /**
     * 证件类型。type_value=ZJLX
     */
    private String zjlx;// 证件类型。type_value=ZJLX
    /**
     * 姓名
     */
    private String xm;// 姓名
    /**
     * 性别。type_value=XB
     */
    private String xb;// 性别。type_value=XB
    /**
     * 证件有效期
     */
    private String zjyxq;// 证件有效期
    /**
     * 出生日期
     */
    private String csrq;// 出生日期
    /**
     * 民族。type_value=MZ
     */
    private String mz;// 民族。type_value=MZ
    /**
     * 人员状态。type_value=RYZT。'1'在业，'2'离休，'3'退休，'4'退职，'5'失业，'6'无业，'7'从未就业，'8'其他。
     */
    private String ryzt;// 人员状态。type_value=RYZT。'1'在业，'2'离休，'3'退休，'4'退职，'5'失业，'6'无业，'7'从未就业，'8'其他。
    /**
     * 户籍性质
     */
    private String hjxz;// 户籍性质
    /**
     * 户口地址
     */
    private String hkdz;// 户口地址
    /**
     * 联系电话
     */
    private String lxdh;// 联系电话
    /**
     * 联系手机
     */
    private String lxsj;// 联系手机
    /**
     * 国籍
     */
    private String gj;// 国籍
    /**
     * 照片回执
     */
    private String zphz;// 照片回执
    /**
     * 通信地址
     */
    private String txdz;// 通信地址
    /**
     * 邮政编码
     */
    private String yzbm;// 邮政编码
    /**
     * 电子邮箱
     */
    private String dzyx;// 电子邮箱
    /**
     * 单位编号
     */
    private String dwbh;// 单位编号
    /**
     * 单位名称
     */
    private String dwmc;// 单位名称
    /**
     * 单位类型：A-公司，B-社区，C-学生，０－无
     */
    private String dwlx;// 单位类型：A-公司，B-社区，C-学生，０－无
    /**
     * 源单位编号
     */
    private String ydwbh;// 源单位编号
    /**
     * 源单位名称
     */
    private String ydwmc;// 源单位名称
    /**
     * 监护人证号
     */
    private String jhrzh;// 监护人证号
    /**
     * 监护人姓名
     */
    private String jhrxm;// 监护人姓名
    /**
     * 电话类型
     */
    private String dhlx;// 电话类型
    /**
     * 监护人证件类型
     */
    private String jhrzjlx;// 监护人证件类型
    /**
     * 社保卡号
     */
    private String sbkh;// 社保卡号
    /**
     * 卡类别
     */
    private String klb;// 卡类别
    /**
     * 申请类型
     */
    private String sqlx;// 申请类型
    /**
     * 照片路径
     */
    private String zp;// 照片路径
    /**
     * 专属职业。type_value=ZSZY
     */
    private String zszy;// 专属职业。type_value=ZSZY
    /**
     * 专属行业。type_value=ZSHY
     */
    private String zshy;// 专属行业。type_value=ZSHY
    /**
     * 卡联名银行。type_value=KLMYH
     */
    private String klmyh;// 卡联名银行。type_value=KLMYH
    /**
     * 代办人姓名
     */
    private String dbr_xm;// 代办人姓名
    /**
     * 代办人性别
     */
    private String dbr_xb;// 代办人性别
    /**
     * 代办人身份证号码
     */
    private String dbr_sfzhm;// 代办人身份证号码
    /**
     * 代办人联系电话
     */
    private String dbr_lxdh;// 代办人联系电话
    /**
     * 申领人签名
     */
    private String slrqm;// 申领人签名
    /**
     * 经办负责人签名
     */
    private String jbfzrqm;// 经办负责人签名
    /**
     * 申领人签名日期
     */
    private String slrqmrq;// 申领人签名日期
    /**
     * 经办负责人签名日期
     */
    private String jbfzrqmrq;// 经办负责人签名日期
    /**
     * 状态
     */
    private String status;// 状态
    /**
     * 建立日期
     */
    private String createDate;// 建立日期
    /**
     * 修改日期
     */
    private String modifyDate;// 修改日期

    /**
     * 录入员ID
     */
    private String lryId;// 录入员ID
    /**
     * 录入员姓名
     */
    private String lryName;// 录入员姓名
    /**
     * 修改操作员ID
     */
    private String modifyOperatorId;// 修改操作员ID
    /**
     * 修改操作员姓名
     */
    private String modifyOperatorName;// 修改操作员姓名
    /**
     * 批次编号
     */
    private String pcbh;// 批次编号
    /**
     * 错误编号
     */
    private String errorNumber;// 错误编号
    /**
     * 照片状态。“0”：初始状态，“1”：合格照片，“2”：照片不存在，“3”：照片不合格
     */
    private String photoStatus;// 照片状态。“0”：初始状态，“1”：合格照片，“2”：照片不存在，“3”：照片不合格
    /**
     * 照片数据
     */
    private byte[] zpData;// 照片数据
    /**
     * 街道名称
     */
    private String jdmc;// 街道名称
    /**
     * 区县名称
     */
    private String qxmc;// 区县名称
    /**
     * 表格状态。'1'：初始状态，'2'：一致，'3'：注销，'4'：查无此人，'5'：名对身份证号不对，'6'：身份证号一致姓名不一致
     */
    private String gridStatus;// 表格状态。'1'：初始状态，'2'：一致，'3'：注销，'4'：查无此人，'5'：名对身份证号不对，'6'：身份证号一致姓名不一致。
    /**
     * 新增人员状态：'0'是非新增人员；'1'是新增人员。
     */
    private String appendStatus = "0";// 新增人员状态：'0'是非新增人员；'1'是新增人员。
    /**
     * 网络上传数据状态：'0'是局域网数据；'1'是网络上传数据。
     */
    private String webStatus = "0";// 网络上传数据状态：'0'是局域网数据；'1'是网络上传数据。
    /**
     * 档案袋编号
     */
    private String archiveBagNumber;// 档案袋编号
    /**
     * 档案袋内序号
     */
    private Long bagOrdinalNumber;// 档案袋内序号
    /**
     * 打包批次编号
     */
    private String zipPcbh;// 打包批次编号
    /**
     * 打包日期
     */
    private String zipDate;// 打包日期
    /**
     * 打包操作员ID
     */
    private Long zipOperatorId;// 打包操作员ID
    /**
     * 打包操作员姓名
     */
    private String zipOperatorName;// 打包操作员姓名
    /**
     * 打包回馈编码
     */
    private String zipEchoNumber;// 打包回馈编码

    /**
     * 用户编号
     */
    private String yhbh;// 用户编号
    /**
     * 人员转出标记 0-未转出, 1-转出
     */
    private int lineIndex;// 人员转出标记 0-未转出, 1-转出
    /**
     * 是否打印过。0表示没有打印过，1-表示打印过
     */
    private String sfdy;// 是否打印过。0表示没有打印过，1-表示打印过
    /**
     * 医保编号
     */
    private String ybbh;// 医保编号
    /**
     * 用来存储一些需要值的字段
     */
    private String cunchu;// 用来存储一些需要值的字段
    /**
     * 用来存储修改之前的证件号码
     */
    private String yzjhm;// 用来存储修改之前的证件号码
    /**
     * 所属机构名称
     */
    private String ssjgmc;// 所属机构名称
    /**
     * 用来存储修改前的证件号码
     */
    private String cunzjhm;// 用来存储修改前的证件号码
    private String xm1;
    private String lxdh1;
    private String lxsj1;
    /**
     * 邮寄标记
     */
    private String yj;
    /**
     * 邮寄地址
     */
    private String yjdz;

    private String glbm;// 管理部门

}
