package com.PG.GreenHouse.model.HeadLessGrading;

import com.PG.GreenHouse.model.ActualDetails;
import com.PG.GreenHouse.model.FactoryWeighment.ActualCodes;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class GetCodes implements Serializable {
    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("Codes")
    private ArrayList<ActualCodes> Codes;

    public GetCodes(String status, String message, ArrayList<ActualCodes> codes) {
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

    public ArrayList<ActualCodes> getCodes() {
        return Codes;
    }

    public void setCodes(ArrayList<ActualCodes> codes) {
        Codes = codes;
    }
}
