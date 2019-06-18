package com.PG.GreenHouse.model;

import com.google.gson.annotations.SerializedName;

public class NetWeightDetails {

    @SerializedName("SW_Net_Weight")
    private String SW_Net_Weight;

    @SerializedName("Variety_Count_Code")
    private String Variety_Count_Code;

    public NetWeightDetails(String SW_Net_Weight, String variety_Count_Code) {
        this.SW_Net_Weight = SW_Net_Weight;
        Variety_Count_Code = variety_Count_Code;
    }

    public String getSW_Net_Weight() {
        return SW_Net_Weight;
    }

    public void setSW_Net_Weight(String SW_Net_Weight) {
        this.SW_Net_Weight = SW_Net_Weight;
    }

    public String getVariety_Count_Code() {
        return Variety_Count_Code;
    }

    public void setVariety_Count_Code(String variety_Count_Code) {
        Variety_Count_Code = variety_Count_Code;
    }
}
