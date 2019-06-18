package com.PG.GreenHouse.model.LocationPlacement;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BarcodeResponce {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("Palatte Details")
    private ArrayList<BarcodeResults> Palatte_Details;

    public BarcodeResponce(String status, String message, ArrayList<BarcodeResults> palatte_Details) {
        this.status = status;
        this.message = message;
        Palatte_Details = palatte_Details;
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

    public ArrayList<BarcodeResults> getPalatte_Details() {
        return Palatte_Details;
    }

    public void setPalatte_Details(ArrayList<BarcodeResults> palatte_Details) {
        Palatte_Details = palatte_Details;
    }
}
