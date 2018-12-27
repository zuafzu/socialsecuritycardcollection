package com.hhkj.cyf.socialsecuritycardcollection.bean;

public class StatusBean {
    private String statusMsg;
    private String status;

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StatusBean{" +
                "statusMsg='" + statusMsg + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
