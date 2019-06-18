package com.PG.GreenHouse.model;

import com.google.gson.annotations.SerializedName;

public class GettingScheduleDetails {
    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("Procurement Schedule Date")
    private ActualDetails Schedule_details;

    public GettingScheduleDetails(String status, String message, ActualDetails schedule_details) {
        this.status = status;
        this.message = message;
        Schedule_details = schedule_details;
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

    public ActualDetails getSchedule_details() {
        return Schedule_details;
    }

    public void setSchedule_details(ActualDetails schedule_details) {
        Schedule_details = schedule_details;
    }
}
