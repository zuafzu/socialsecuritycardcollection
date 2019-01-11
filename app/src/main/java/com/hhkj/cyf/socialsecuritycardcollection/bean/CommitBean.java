package com.hhkj.cyf.socialsecuritycardcollection.bean;

import java.io.Serializable;

public class CommitBean implements Serializable {

    private String id;//人员id
    private String xmStr1 = "";// 姓名
    private String xb = "";// 性别。type_value=XB
    private String xbName = "";// 性别。type_value=XB
    private String zjlx = "";// 证件类型。type_value=ZJLX
    private String zjlxName = "";// 证件类型。type_value=ZJLX
    private String zjhm = "";// 证件号码
    private String csrq = "";// 出生日期
    private String csrqStr = "";// 出生日期
    private String zjyxq = "";// 证件有效期
    private String mz = "";// 民族。type_value=MZ
    private String mzName = "";// 民族。type_value=MZ
    private String txdz = "";// 通信地址

    private String jhrzjlx = "";// 监护人证件类型
    private String jhrzjlxName = "";// 监护人证件类型
    private String jhrzh = "";// 监护人证号
    private String jhrxm = "";// 监护人姓名

    private String ryzt = "";// 人员状态。type_value=RYZT。'1'在业，'2'离休，'3'退休，'4'退职，'5'失业，'6'无业，'7'从未就业，'8'其他。
    private String ryztName = "";// 人员状态。type_value=RYZT。'1'在业，'2'离休，'3'退休，'4'退职，'5'失业，'6'无业，'7'从未就业，'8'其他。
    private String gj = "";// 国籍
    private String gjName = "";// 国籍
    private String hjxz = "";// 户籍性质
    private String hjxzName = "";// 户籍性质
    private String lxsjStr1 = "";// 联系手机
    private String lxdhStr1 = "";// 联系电话
    private String yzbm = "";// 邮政编码
    private String klmyh = "";// 卡联名银行。type_value=KLMYH
    private String klmyhName = "";// 卡联名银行。type_value=KLMYH
    private String zszy = "";// 专属职业。type_value=ZSZY
    private String zszyName = "";// 专属职业。type_value=ZSZY
    private String zshy = "";// 专属行业。type_value=ZSHY
    private String zshyName = "";// 专属行业。type_value=ZSHY
    private String yjdz = "";// 邮寄地址

    private String zp = "";// 照片路径

    private String dbr_xm = "";// 代办人姓名
//    private String dbr_xb = "";// 代办人性别
    private String dbr_sfzhm = "";// ybbh代办人身份证号码
    private String dbr_lxdh = "";// 代办人联系电话
    /**
     * 邮寄区域
     */
    private String provinceId = "";//省id
    private String cityId = "";//市id
    private String regionId = "";//区id
    private String provinceName = "";
    private String cityName = "";
    private String regionName = "";

    private String sh = "";// 审核
    private String sfdy = "0";// 是否打印过。0表示没有打印过，1-表示打印过
    private String dwmc = "";// 单位名称
//    private String createDate = "";// 建立日期
    private String createDate1 = "";// 建立日期
    private String lryId = "";// 录入员ID

//    private String hkdz = "";// 户口地址
//    private String dzyx = "";// 电子邮箱
//    private String dwbh = "";// 单位编号
//    private String dwlx = "";// 单位类型：A-公司，B-社区，C-学生，０－无
//    private String ydwbh = "";// 源单位编号
//    private String ydwmc = "";// 源单位名称
//    private String dhlx = "";// 电话类型
//    private String sbkh = "";// 社保卡号
//    private String klb = "";// 卡类别
//    private String sqlx = "";// 申请类型
//    private String status = "";// 状态
//    private String modifyDate = "";// 修改日期
//    private String lryName = "";// 录入员姓名
//    private String modifyOperatorId = "";// 修改操作员ID
//    private String modifyOperatorName = "";// 修改操作员姓名
//    private String pcbh = "";// 批次编号
//    private String errorNumber = "";// 错误编号
//    private String photoStatus = "";// 照片状态。“0”：初始状态，“1”：合格照片，“2”：照片不存在，“3”：照片不合格
//    private String jdmc = "";// 街道名称
//    private String qxmc = "";// 区县名称
//    private String gridStatus = "";// 表格状态。'1'：初始状态，'2'：一致，'3'：注销，'4'：查无此人，'5'：名对身份证号不对，'6'：身份证号一致姓名不一致。
//    private String appendStatus = "0";// 新增人员状态：'0'是非新增人员；'1'是新增人员。
//    private String webStatus = "0";// 网络上传数据状态：'0'是局域网数据；'1'是网络上传数据。
//    private String yhbh = "";// 用户编号
//    private int lineIndex;// 人员转出标记 0-未转出, 1-转出
//    private String ybbh = "";// 医保编号
//    private String cunchu = "";// 用来存储一些需要值的字段
//    private String yzjhm = "";// 用来存储修改之前的证件号码
//    private String ssjgmc = "";// 所属机构名称
//    private String cunzjhm = "";// 用来存储修改前的证件号码
//    private String xm1 = "";
//    private String lxdh1 = "";
//    private String lxsj1 = "";


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSh() {
        return sh;
    }

    public void setSh(String sh) {
        this.sh = sh;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getZjlx() {
        return zjlx;
    }

    public void setZjlx(String zjlx) {
        this.zjlx = zjlx;
    }

    public String getXmStr1() {
        return xmStr1;
    }

    public void setXmStr1(String xmStr1) {
        this.xmStr1 = xmStr1;
    }

    public String getLxdhStr1() {
        return lxdhStr1;
    }

    public void setLxdhStr1(String lxdhStr1) {
        this.lxdhStr1 = lxdhStr1;
    }

    public String getLxsjStr1() {
        return lxsjStr1;
    }

    public void setLxsjStr1(String lxsjStr1) {
        this.lxsjStr1 = lxsjStr1;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getZjyxq() {
        return zjyxq;
    }

    public void setZjyxq(String zjyxq) {
        this.zjyxq = zjyxq;
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    public String getRyzt() {
        return ryzt;
    }

    public void setRyzt(String ryzt) {
        this.ryzt = ryzt;
    }

    public String getHjxz() {
        return hjxz;
    }

    public void setHjxz(String hjxz) {
        this.hjxz = hjxz;
    }

//    public String getHkdz() {
//        return hkdz;
//    }
//
//    public void setHkdz(String hkdz) {
//        this.hkdz = hkdz;
//    }

    public String getGj() {
        return gj;
    }

    public void setGj(String gj) {
        this.gj = gj;
    }

    public String getTxdz() {
        return txdz;
    }

    public void setTxdz(String txdz) {
        this.txdz = txdz;
    }

    public String getYzbm() {
        return yzbm;
    }

    public void setYzbm(String yzbm) {
        this.yzbm = yzbm;
    }

//    public String getDzyx() {
//        return dzyx;
//    }
//
//    public void setDzyx(String dzyx) {
//        this.dzyx = dzyx;
//    }
//
//    public String getDwbh() {
//        return dwbh;
//    }
//
//    public void setDwbh(String dwbh) {
//        this.dwbh = dwbh;
//    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

//    public String getDwlx() {
//        return dwlx;
//    }
//
//    public void setDwlx(String dwlx) {
//        this.dwlx = dwlx;
//    }

//    public String getYdwbh() {
//        return ydwbh;
//    }
//
//    public void setYdwbh(String ydwbh) {
//        this.ydwbh = ydwbh;
//    }
//
//    public String getYdwmc() {
//        return ydwmc;
//    }
//
//    public void setYdwmc(String ydwmc) {
//        this.ydwmc = ydwmc;
//    }

    public String getJhrzh() {
        return jhrzh;
    }

    public void setJhrzh(String jhrzh) {
        this.jhrzh = jhrzh;
    }

    public String getJhrxm() {
        return jhrxm;
    }

    public void setJhrxm(String jhrxm) {
        this.jhrxm = jhrxm;
    }

//    public String getDhlx() {
//        return dhlx;
//    }
//
//    public void setDhlx(String dhlx) {
//        this.dhlx = dhlx;
//    }

    public String getJhrzjlx() {
        return jhrzjlx;
    }

    public void setJhrzjlx(String jhrzjlx) {
        this.jhrzjlx = jhrzjlx;
    }

//    public String getSbkh() {
//        return sbkh;
//    }
//
//    public void setSbkh(String sbkh) {
//        this.sbkh = sbkh;
//    }

//    public String getKlb() {
//        return klb;
//    }
//
//    public void setKlb(String klb) {
//        this.klb = klb;
//    }
//
//    public String getSqlx() {
//        return sqlx;
//    }
//
//    public void setSqlx(String sqlx) {
//        this.sqlx = sqlx;
//    }

    public String getZp() {
        return zp;
    }

    public void setZp(String zp) {
        this.zp = zp;
    }

    public String getZszy() {
        return zszy;
    }

    public void setZszy(String zszy) {
        this.zszy = zszy;
    }

    public String getZshy() {
        return zshy;
    }

    public void setZshy(String zshy) {
        this.zshy = zshy;
    }

    public String getKlmyh() {
        return klmyh;
    }

    public void setKlmyh(String klmyh) {
        this.klmyh = klmyh;
    }

    public String getDbr_xm() {
        return dbr_xm;
    }

    public void setDbr_xm(String dbr_xm) {
        this.dbr_xm = dbr_xm;
    }

//    public String getDbr_xb() {
//        return dbr_xb;
//    }
//
//    public void setDbr_xb(String dbr_xb) {
//        this.dbr_xb = dbr_xb;
//    }

    public String getDbr_sfzhm() {
        return dbr_sfzhm;
    }

    public void setDbr_sfzhm(String dbr_sfzhm) {
        this.dbr_sfzhm = dbr_sfzhm;
    }

    public String getDbr_lxdh() {
        return dbr_lxdh;
    }

    public void setDbr_lxdh(String dbr_lxdh) {
        this.dbr_lxdh = dbr_lxdh;
    }

//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }

//    public String getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(String createDate) {
//        this.createDate = createDate;
//    }

//    public String getModifyDate() {
//        return modifyDate;
//    }
//
//    public void setModifyDate(String modifyDate) {
//        this.modifyDate = modifyDate;
//    }

    public String getLryId() {
        return lryId;
    }

    public void setLryId(String lryId) {
        this.lryId = lryId;
    }

//    public String getLryName() {
//        return lryName;
//    }
//
//    public void setLryName(String lryName) {
//        this.lryName = lryName;
//    }
//
//    public String getModifyOperatorId() {
//        return modifyOperatorId;
//    }
//
//    public void setModifyOperatorId(String modifyOperatorId) {
//        this.modifyOperatorId = modifyOperatorId;
//    }

//    public String getModifyOperatorName() {
//        return modifyOperatorName;
//    }
//
//    public void setModifyOperatorName(String modifyOperatorName) {
//        this.modifyOperatorName = modifyOperatorName;
//    }
//
//    public String getPcbh() {
//        return pcbh;
//    }
//
//    public void setPcbh(String pcbh) {
//        this.pcbh = pcbh;
//    }
//
//    public String getErrorNumber() {
//        return errorNumber;
//    }
//
//    public void setErrorNumber(String errorNumber) {
//        this.errorNumber = errorNumber;
//    }
//
//    public String getPhotoStatus() {
//        return photoStatus;
//    }
//
//    public void setPhotoStatus(String photoStatus) {
//        this.photoStatus = photoStatus;
//    }
//
//    public String getJdmc() {
//        return jdmc;
//    }
//
//    public void setJdmc(String jdmc) {
//        this.jdmc = jdmc;
//    }

//    public String getQxmc() {
//        return qxmc;
//    }
//
//    public void setQxmc(String qxmc) {
//        this.qxmc = qxmc;
//    }
//
//    public String getGridStatus() {
//        return gridStatus;
//    }
//
//    public void setGridStatus(String gridStatus) {
//        this.gridStatus = gridStatus;
//    }
//
//    public String getAppendStatus() {
//        return appendStatus;
//    }
//
//    public void setAppendStatus(String appendStatus) {
//        this.appendStatus = appendStatus;
//    }
//
//    public String getWebStatus() {
//        return webStatus;
//    }
//
//    public void setWebStatus(String webStatus) {
//        this.webStatus = webStatus;
//    }
//
//    public String getYhbh() {
//        return yhbh;
//    }
//
//    public void setYhbh(String yhbh) {
//        this.yhbh = yhbh;
//    }

//    public int getLineIndex() {
//        return lineIndex;
//    }
//
//    public void setLineIndex(int lineIndex) {
//        this.lineIndex = lineIndex;
//    }

    public String getSfdy() {
        return sfdy;
    }

    public void setSfdy(String sfdy) {
        this.sfdy = sfdy;
    }

//    public String getYbbh() {
//        return ybbh;
//    }
//
//    public void setYbbh(String ybbh) {
//        this.ybbh = ybbh;
//    }

//    public String getCunchu() {
//        return cunchu;
//    }
//
//    public void setCunchu(String cunchu) {
//        this.cunchu = cunchu;
//    }
//
//    public String getYzjhm() {
//        return yzjhm;
//    }
//
//    public void setYzjhm(String yzjhm) {
//        this.yzjhm = yzjhm;
//    }
//
//    public String getSsjgmc() {
//        return ssjgmc;
//    }
//
//    public void setSsjgmc(String ssjgmc) {
//        this.ssjgmc = ssjgmc;
//    }
//
//    public String getCunzjhm() {
//        return cunzjhm;
//    }
//
//    public void setCunzjhm(String cunzjhm) {
//        this.cunzjhm = cunzjhm;
//    }
//
//    public String getXm1() {
//        return xm1;
//    }
//
//    public void setXm1(String xm1) {
//        this.xm1 = xm1;
//    }
//
//    public String getLxdh1() {
//        return lxdh1;
//    }

//    public void setLxdh1(String lxdh1) {
//        this.lxdh1 = lxdh1;
//    }
//
//    public String getLxsj1() {
//        return lxsj1;
//    }
//
//    public void setLxsj1(String lxsj1) {
//        this.lxsj1 = lxsj1;
//    }

    public String getYjdz() {
        return yjdz;
    }

    public void setYjdz(String yjdz) {
        this.yjdz = yjdz;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getXbName() {
        return xbName;
    }

    public void setXbName(String xbName) {
        this.xbName = xbName;
    }

    public String getZjlxName() {
        return zjlxName;
    }

    public void setZjlxName(String zjlxName) {
        this.zjlxName = zjlxName;
    }

    public String getMzName() {
        return mzName;
    }

    public void setMzName(String mzName) {
        this.mzName = mzName;
    }

    public String getJhrzjlxName() {
        return jhrzjlxName;
    }

    public void setJhrzjlxName(String jhrzjlxName) {
        this.jhrzjlxName = jhrzjlxName;
    }

    public String getRyztName() {
        return ryztName;
    }

    public void setRyztName(String ryztName) {
        this.ryztName = ryztName;
    }

    public String getHjxzName() {
        return hjxzName;
    }

    public void setHjxzName(String hjxzName) {
        this.hjxzName = hjxzName;
    }

    public String getGjName() {
        return gjName;
    }

    public void setGjName(String gjName) {
        this.gjName = gjName;
    }

    public String getZszyName() {
        return zszyName;
    }

    public void setZszyName(String zszyName) {
        this.zszyName = zszyName;
    }

    public String getZshyName() {
        return zshyName;
    }

    public void setZshyName(String zshyName) {
        this.zshyName = zshyName;
    }

    public String getKlmyhName() {
        return klmyhName;
    }

    public void setKlmyhName(String klmyhName) {
        this.klmyhName = klmyhName;
    }

    public String getCsrqStr() {
        return csrqStr;
    }

    public void setCsrqStr(String csrqStr) {
        this.csrqStr = csrqStr;
    }

    public String getCreateDate1() {
        return createDate1;
    }

    public void setCreateDate1(String createDate1) {
        this.createDate1 = createDate1;
    }

    @Override
    public String toString() {
        return "CommitBean{" +
                "id='" + id + '\'' +
                "zjhm='" + zjhm + '\'' +
                ", zjlx='" + zjlx + '\'' +
                ", zjlxName='" + zjlxName + '\'' +
                ", xmStr1='" + xmStr1 + '\'' +
                ", xb='" + xb + '\'' +
                ", xbName='" + xbName + '\'' +
                ", zjyxq='" + zjyxq + '\'' +
                ", csrq='" + csrq + '\'' +
                ", csrqStr='" + csrqStr + '\'' +
                ", mz='" + mz + '\'' +
                ", mzName='" + mzName + '\'' +
                ", ryzt='" + ryzt + '\'' +
                ", ryztName='" + ryztName + '\'' +
                ", hjxz='" + hjxz + '\'' +
                ", hjxzName='" + hjxzName + '\'' +
//                ", hkdz='" + hkdz + '\'' +
                ", lxdhStr1='" + lxdhStr1 + '\'' +
                ", lxsjStr1='" + lxsjStr1 + '\'' +
                ", gj='" + gj + '\'' +
                ", gjName='" + gjName + '\'' +
                ", txdz='" + txdz + '\'' +
                ", yzbm='" + yzbm + '\'' +
//                ", dzyx='" + dzyx + '\'' +
//                ", dwbh='" + dwbh + '\'' +
                ", dwmc='" + dwmc + '\'' +
//                ", dwlx='" + dwlx + '\'' +
//                ", ydwbh='" + ydwbh + '\'' +
//                ", ydwmc='" + ydwmc + '\'' +
                ", jhrzh='" + jhrzh + '\'' +
                ", jhrxm='" + jhrxm + '\'' +
//                ", dhlx='" + dhlx + '\'' +
                ", jhrzjlx='" + jhrzjlx + '\'' +
                ", jhrzjlxName='" + jhrzjlxName + '\'' +
//                ", sbkh='" + sbkh + '\'' +
//                ", klb='" + klb + '\'' +
//                ", sqlx='" + sqlx + '\'' +
                ", zp='" + zp + '\'' +
                ", sh='" + sh + '\'' +
                ", zszy='" + zszy + '\'' +
                ", zszyName='" + zszyName + '\'' +
                ", zshy='" + zshy + '\'' +
                ", zshyName='" + zshyName + '\'' +
                ", klmyh='" + klmyh + '\'' +
                ", klmyhName='" + klmyhName + '\'' +
                ", dbr_xm='" + dbr_xm + '\'' +
//                ", dbr_xb='" + dbr_xb + '\'' +
                ", dbr_sfzhm='" + dbr_sfzhm + '\'' +
                ", dbr_lxdh='" + dbr_lxdh + '\'' +
//                ", status='" + status + '\'' +
//                ", createDate='" + createDate + '\'' +
                ", createDate1='" + createDate1 + '\'' +
//                ", modifyDate='" + modifyDate + '\'' +
                ", lryId='" + lryId + '\'' +
//                ", lryName='" + lryName + '\'' +
//                ", modifyOperatorId='" + modifyOperatorId + '\'' +
//                ", modifyOperatorName='" + modifyOperatorName + '\'' +
//                ", pcbh='" + pcbh + '\'' +
//                ", errorNumber='" + errorNumber + '\'' +
//                ", photoStatus='" + photoStatus + '\'' +
//                ", jdmc='" + jdmc + '\'' +
//                ", qxmc='" + qxmc + '\'' +
//                ", gridStatus='" + gridStatus + '\'' +
//                ", appendStatus='" + appendStatus + '\'' +
//                ", webStatus='" + webStatus + '\'' +
//                ", yhbh='" + yhbh + '\'' +
//                ", lineIndex=" + lineIndex +
                ", sfdy='" + sfdy + '\'' +
//                ", ybbh='" + ybbh + '\'' +
//                ", cunchu='" + cunchu + '\'' +
//                ", yzjhm='" + yzjhm + '\'' +
//                ", ssjgmc='" + ssjgmc + '\'' +
//                ", cunzjhm='" + cunzjhm + '\'' +
//                ", xm1='" + xm1 + '\'' +
//                ", lxdh1='" + lxdh1 + '\'' +
//                ", lxsj1='" + lxsj1 + '\'' +
                ", provinceId='" + provinceId + '\'' +
                ", cityId='" + cityId + '\'' +
                ", regionId='" + regionId + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", regionName='" + regionName + '\'' +
                ", yjdz='" + yjdz + '\'' +
                '}';
    }
}
