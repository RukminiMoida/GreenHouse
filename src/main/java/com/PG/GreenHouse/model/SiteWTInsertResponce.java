package com.PG.GreenHouse.model;

import com.google.gson.annotations.SerializedName;

public class SiteWTInsertResponce {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    public SiteWTInsertResponce(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
