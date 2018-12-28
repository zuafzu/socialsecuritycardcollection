package com.hhkj.cyf.socialsecuritycardcollection.bean;

public class QueryBean {

    private String id;
    private String name;// 名字
    private String zhengjianNum;//身份证号
    private String state;// 状态
    private String stateStr;// 状态描述
    private String remark;// 状态描述
    private String type;// 状态描述

    public QueryBean() {
        super();
    }

    public QueryBean(String id, String name, String zhengjianNum, String state, String stateStr, String remark, String type) {
        this.id = id;
        this.name = name;
        this.zhengjianNum = zhengjianNum;
        this.state = state;
        this.stateStr = stateStr;
        this.remark = remark;
        this.type = type;
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

    public String getZhengjianNum() {
        return zhengjianNum;
    }

    public void setZhengjianNum(String zhengjianNum) {
        this.zhengjianNum = zhengjianNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateStr() {
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
                ", zhengjianNum='" + zhengjianNum + '\'' +
                ", state='" + state + '\'' +
                ", stateStr='" + stateStr + '\'' +
                ", remark='" + remark + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
