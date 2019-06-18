package com.PG.GreenHouse.model.FactoryWeighment;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class FactoryWeighmentCodes implements Serializable {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName(" Data")
    private Codes Data;

    public FactoryWeighmentCodes(String status, String message, Codes data) {
        this.status = status;
        this.message = message;
        Data = data;
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

    public Codes getData() {
        return Data;
    }

    public void setData(Codes data) {
        Data = data;
    }
}
