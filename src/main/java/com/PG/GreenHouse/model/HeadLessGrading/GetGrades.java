package com.PG.GreenHouse.model.HeadLessGrading;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetGrades {


    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("FPVariety")
    private ArrayList<GetGradeCodes> Codes;

    public GetGrades(String status, String message, ArrayList<GetGradeCodes> codes) {
        this.status = status;
        this.message = message;
        Codes = codes;
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

    public ArrayList<GetGradeCodes> getCodes() {
        return Codes;
    }

    public void setCodes(ArrayList<GetGradeCodes> codes) {
        Codes = codes;
    }
}
