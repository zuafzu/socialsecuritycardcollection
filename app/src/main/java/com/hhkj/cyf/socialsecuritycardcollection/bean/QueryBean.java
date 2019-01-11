package com.hhkj.cyf.socialsecuritycardcollection.bean;

public class QueryBean {

    private String id;
    private String name;// 名字
    private String zjhm;//身份证号
    private String status;// 状态
    private String statusMsg;// 状态描述
    private String remake;// 状态描述
    private String type;// 状态描述

    public QueryBean() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "QueryBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", zjhm='" + zjhm + '\'' +
                ", status='" + status + '\'' +
                ", statusMsg='" + statusMsg + '\'' +
                ", remake='" + remake + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
