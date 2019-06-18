package com.PG.GreenHouse.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetScheduleNo {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("Procurement Schedule No")
    private ArrayList<String> data;

    public GetScheduleNo(String status, String message, ArrayList<String> data) {
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

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }
}
