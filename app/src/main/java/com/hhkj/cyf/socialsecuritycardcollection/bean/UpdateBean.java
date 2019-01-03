package com.hhkj.cyf.socialsecuritycardcollection.bean;

public class UpdateBean {

    private String updateStatus;
    private String fileUrl;
    private String versionDesc;
    private String versionName;
    private String versionCode;

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getVersionDesc() {
        return versionDesc;
    }

    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    @Override
    public String toString() {
        return "UpdateBean{" +
                "updateStatus='" + updateStatus + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", versionDesc='" + versionDesc + '\'' +
                ", versionName='" + versionName + '\'' +
                ", versionCode='" + versionCode + '\'' +
                '}';
    }
}
