package com.hhkj.cyf.socialsecuritycardcollection.bean;

public class SelectItemBean {

    private String name;
    private String id;

    public SelectItemBean() {
        super();
    }

    public SelectItemBean(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SelectItemBean{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
