package com.PG.GreenHouse.model;

import com.google.gson.annotations.SerializedName;

public class GetEnquiryRespone {
    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName(" Data")
    private GetEnquiryFullDetails data;

    public GetEnquiryRespone(String status, String message, GetEnquiryFullDetails data) {
        this.status = status;
        this.message = message;
        this.data = data;
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

    public GetEnquiryFullDetails getData() {
        return data;
    }

    public void setData(GetEnquiryFullDetails data) {
        this.data = data;
    }
}
