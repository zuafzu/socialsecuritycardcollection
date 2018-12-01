package com.hhkj.cyf.socialsecuritycardcollection.bean;

public class QueryBean {

    private String id;
    private String name;// 名字
    private String identity;//身份证号
    private String state;// 状态
    private String stateStr;// 状态描述

    public QueryBean() {
        super();
    }

    public QueryBean(String id, String name, String identity, String state, String stateStr) {
        this.id = id;
        this.name = name;
        this.identity = identity;
        this.state = state;
        this.stateStr = stateStr;
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

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
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

    @Override
    public String toString() {
        return "QueryBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", identity='" + identity + '\'' +
                ", state='" + state + '\'' +
                ", stateStr='" + stateStr + '\'' +
                '}';
    }
}
