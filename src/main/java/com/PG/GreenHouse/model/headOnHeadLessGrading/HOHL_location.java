package com.PG.GreenHouse.model.headOnHeadLessGrading;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HOHL_location implements Serializable {
    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("Location")
    private String Location;

    public HOHL_location(String status, String message, String location) {
        this.status = status;
        this.message = message;
        Location = location;
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

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
