package com.PG.GreenHouse.model.HeadLessGrading;

import com.PG.GreenHouse.model.FactoryWeighment.ActualCodes;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetGroupCodes {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("Codes")
    private ArrayList<GroupCodes> Codes;

    public GetGroupCodes(String status, String message, ArrayList<GroupCodes> codes) {
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

    public ArrayList<GroupCodes> getCodes() {
        return Codes;
    }

    public void setCodes(ArrayList<GroupCodes> codes) {
        Codes = codes;
    }
}
