package com.hhkj.cyf.socialsecuritycardcollection.bean;

public class LawsListBean {

    private String title;
    private String url;

    public LawsListBean() {
        super();
    }

    public LawsListBean(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "LawsListBean{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
