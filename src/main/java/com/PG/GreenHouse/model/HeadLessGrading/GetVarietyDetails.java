package com.PG.GreenHouse.model.HeadLessGrading;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class GetVarietyDetails implements Serializable {


    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("FPVariety")
    private ArrayList<VarietyCodes> Codes;

    public GetVarietyDetails(String status, String message, ArrayList<VarietyCodes> codes) {
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

    public ArrayList<VarietyCodes> getCodes() {
        return Codes;
    }

    public void setCodes(ArrayList<VarietyCodes> codes) {
        Codes = codes;
    }
}
