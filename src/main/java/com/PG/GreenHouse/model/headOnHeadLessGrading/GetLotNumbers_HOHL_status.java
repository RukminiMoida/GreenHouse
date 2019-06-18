package com.PG.GreenHouse.model.headOnHeadLessGrading;

import com.PG.GreenHouse.model.HeadLessGrading.Lot_numbers;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class GetLotNumbers_HOHL_status implements Serializable {
    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("Lot No")
    private ArrayList<Lot_details> Lot_No;

    public GetLotNumbers_HOHL_status(String status, String message, ArrayList<Lot_details> lot_No) {
        this.status = status;
        this.message = message;
        Lot_No = lot_No;
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

    public ArrayList<Lot_details> getLot_No() {
        return Lot_No;
    }

    public void setLot_No(ArrayList<Lot_details> lot_No) {
        Lot_No = lot_No;
    }
}
