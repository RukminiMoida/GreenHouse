package com.PG.GreenHouse.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetEnquiryFullDetails {

    @SerializedName("former")
    private ArrayList<FarmerDetails> farmerDetails;


    @SerializedName("boxrequired")
    private ArrayList<BoxesDetails> boxesDetails;

    public GetEnquiryFullDetails(ArrayList<FarmerDetails> farmerDetails, ArrayList<BoxesDetails> boxesDetails) {
        this.farmerDetails = farmerDetails;
        this.boxesDetails = boxesDetails;
    }

    public ArrayList<FarmerDetails> getFarmerDetails() {
        return farmerDetails;
    }

    public void setFarmerDetails(ArrayList<FarmerDetails> farmerDetails) {
        this.farmerDetails = farmerDetails;
    }

    public ArrayList<BoxesDetails> getBoxesDetails() {
        return boxesDetails;
    }

    public void setBoxesDetails(ArrayList<BoxesDetails> boxesDetails) {
        this.boxesDetails = boxesDetails;
    }
}
