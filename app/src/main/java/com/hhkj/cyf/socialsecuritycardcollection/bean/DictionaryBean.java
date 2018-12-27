package com.hhkj.cyf.socialsecuritycardcollection.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class DictionaryBean implements Serializable {
    private ArrayList<ListBean> xbMap;
    private ArrayList<ListBean> zjlxMap;
    private ArrayList<ListBean> mzMap;
    private ArrayList<ListBean> ryztMap;
    private ArrayList<ListBean> gjMap;
    private ArrayList<ListBean> hjxzMap;
    private ArrayList<ListBean> klmyhMap;
    private ArrayList<ListBean> zszyMap;
    private ArrayList<ListBean> zshyMap;
    private ArrayList<ListBean> zhrlxMap;

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

    public ArrayList<ListBean> getMzMap() {
        return mzMap;
    }

    public void setMzMap(ArrayList<ListBean> mzMap) {
        this.mzMap = mzMap;
    }

    public ArrayList<ListBean> getRyztMap() {
        return ryztMap;
    }

    public void setRyztMap(ArrayList<ListBean> ryztMap) {
        this.ryztMap = ryztMap;
    }

    public ArrayList<ListBean> getGjMap() {
        return gjMap;
    }

    public void setGjMap(ArrayList<ListBean> gjMap) {
        this.gjMap = gjMap;
    }

    public ArrayList<ListBean> getHjxzMap() {
        return hjxzMap;
    }

    public void setHjxzMap(ArrayList<ListBean> hjxzMap) {
        this.hjxzMap = hjxzMap;
    }

    public ArrayList<ListBean> getKlmyhMap() {
        return klmyhMap;
    }

    public void setKlmyhMap(ArrayList<ListBean> klmyhMap) {
        this.klmyhMap = klmyhMap;
    }

    public ArrayList<ListBean> getZszyMap() {
        return zszyMap;
    }

    public void setZszyMap(ArrayList<ListBean> zszyMap) {
        this.zszyMap = zszyMap;
    }

    public ArrayList<ListBean> getZshyMap() {
        return zshyMap;
    }

    public void setZshyMap(ArrayList<ListBean> zshyMap) {
        this.zshyMap = zshyMap;
    }

    public ArrayList<ListBean> getZhrlxMap() {
        return zhrlxMap;
    }

    public void setZhrlxMap(ArrayList<ListBean> zhrlxMap) {
        this.zhrlxMap = zhrlxMap;
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
                "xbMap=" + xbMap +
                ", zjlxMap=" + zjlxMap +
                ", mzMap=" + mzMap +
                ", ryztMap=" + ryztMap +
                ", gjMap=" + gjMap +
                ", hjxzMap=" + hjxzMap +
                ", klmyhMap=" + klmyhMap +
                ", zszyMap=" + zszyMap +
                ", zshyMap=" + zshyMap +
                ", zhrlxMap=" + zhrlxMap +
                '}';
    }
}