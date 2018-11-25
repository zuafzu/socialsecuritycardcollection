package com.hhkj.cyf.socialsecuritycardcollection.bean;

public class HomeBean {
    private int imageUrl;
    private String modelId;
    private String modelName;

    public HomeBean() {
    }

    public HomeBean(int imageUrl, String modelId, String modelName) {
        this.imageUrl = imageUrl;
        this.modelId = modelId;
        this.modelName = modelName;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public String toString() {
        return "HomeBean{" +
                "imageUrl=" + imageUrl +
                ", modelId='" + modelId + '\'' +
                ", modelName='" + modelName + '\'' +
                '}';
    }
}
