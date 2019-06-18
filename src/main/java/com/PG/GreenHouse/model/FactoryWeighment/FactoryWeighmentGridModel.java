package com.PG.GreenHouse.model.FactoryWeighment;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FactoryWeighmentGridModel {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("Lot Numbers")
    private ArrayList<FTLotNumbers> Lot_Numbers;

    public FactoryWeighmentGridModel(String status, String message, ArrayList<FTLotNumbers> lot_Numbers) {
        this.status = status;
        this.message = message;
        Lot_Numbers = lot_Numbers;
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

    public ArrayList<FTLotNumbers> getLot_Numbers() {
        return Lot_Numbers;
    }

    public void setLot_Numbers(ArrayList<FTLotNumbers> lot_Numbers) {
        Lot_Numbers = lot_Numbers;
    }
}
