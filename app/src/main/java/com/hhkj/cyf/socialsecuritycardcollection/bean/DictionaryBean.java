package com.hhkj.cyf.socialsecuritycardcollection.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class DictionaryBean implements Serializable {
    private ArrayList<ListBean> ryztMap;//人员状态
    private ArrayList<ListBean> hjxzMap;//户籍性质
    private ArrayList<ListBean> xbMap;//性别
    private ArrayList<ListBean> zjlxMap;//证件类型  监护人证件类型
    private ArrayList<ListBean> zszyMap;//专属职业
    private ArrayList<ListBean> gjMap;//国籍
    private ArrayList<ListBean> zshyMap;//专属行业
    private ArrayList<ListBean> mzMap;//民族
    private ArrayList<ListBean> klmyhMap;//卡联名银行

    public ArrayList<ListBean> getRyztMap() {
        return ryztMap;
    }

    public void setRyztMap(ArrayList<ListBean> ryztMap) {
        this.ryztMap = ryztMap;
    }

    public ArrayList<ListBean> getHjxzMap() {
        return hjxzMap;
    }

    public void setHjxzMap(ArrayList<ListBean> hjxzMap) {
        this.hjxzMap = hjxzMap;
    }

    public ArrayList<ListBean> getXbMap() {
        return xbMap;
    }

    public void setXbMap(ArrayList<ListBean> xbMap) {
        this.xbMap = xbMap;
    }

    public ArrayList<ListBean> getZjlxMap() {
        return zjlxMap;
    }

    public void setZjlxMap(ArrayList<ListBean> zjlxMap) {
        this.zjlxMap = zjlxMap;
    }

    public ArrayList<ListBean> getZszyMap() {
        return zszyMap;
    }

    public void setZszyMap(ArrayList<ListBean> zszyMap) {
        this.zszyMap = zszyMap;
    }

    public ArrayList<ListBean> getGjMap() {
        return gjMap;
    }

    public void setGjMap(ArrayList<ListBean> gjMap) {
        this.gjMap = gjMap;
    }

    public ArrayList<ListBean> getZshyMap() {
        return zshyMap;
    }

    public void setZshyMap(ArrayList<ListBean> zshyMap) {
        this.zshyMap = zshyMap;
    }

    public ArrayList<ListBean> getMzMap() {
        return mzMap;
    }

    public void setMzMap(ArrayList<ListBean> mzMap) {
        this.mzMap = mzMap;
    }

    public ArrayList<ListBean> getKlmyhMap() {
        return klmyhMap;
    }

    public void setKlmyhMap(ArrayList<ListBean> klmyhMap) {
        this.klmyhMap = klmyhMap;
    }

    public class ListBean implements Serializable{
        private String id;
        private String name;

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

        @Override
        public String toString() {
            return "ListBean{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DictionaryBean{" +
                "ryztMap=" + ryztMap +
                ", hjxzMap=" + hjxzMap +
                ", xbMap=" + xbMap +
                ", zjlxMap=" + zjlxMap +
                ", zszyMap=" + zszyMap +
                ", gjMap=" + gjMap +
                ", zshyMap=" + zshyMap +
                ", mzMap=" + mzMap +
                ", klmyhMap=" + klmyhMap +
                '}';
    }
}