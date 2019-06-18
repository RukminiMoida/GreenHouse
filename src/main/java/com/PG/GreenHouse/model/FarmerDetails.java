package com.PG.GreenHouse.model;

import com.google.gson.annotations.SerializedName;

public class FarmerDetails {
    @SerializedName("Aqua_Agent_Name")
    private String Aqua_Agent_Name;

    @SerializedName("Aqua_Farmer_Name")
    private String Aqua_Farmer_Name;

    @SerializedName("Aqua_Farmer_Bank_Place")
    private String Aqua_Farmer_Bank_Place;

    @SerializedName("Village_Name")
    private String Village_Name;

    @SerializedName("Product_Variety_Code")
    private String Product_Variety_Code;


    public FarmerDetails(String aqua_Agent_Name, String aqua_Farmer_Name, String aqua_Farmer_Bank_Place, String village_Name, String product_Variety_Code) {
        Aqua_Agent_Name = aqua_Agent_Name;
        Aqua_Farmer_Name = aqua_Farmer_Name;
        Aqua_Farmer_Bank_Place = aqua_Farmer_Bank_Place;
        Village_Name = village_Name;
        Product_Variety_Code = product_Variety_Code;
    }

    public String getAqua_Agent_Name() {
        return Aqua_Agent_Name;
    }

    public void setAqua_Agent_Name(String aqua_Agent_Name) {
        Aqua_Agent_Name = aqua_Agent_Name;
    }

    public String getAqua_Farmer_Name() {
        return Aqua_Farmer_Name;
    }

    public void setAqua_Farmer_Name(String aqua_Farmer_Name) {
        Aqua_Farmer_Name = aqua_Farmer_Name;
    }

    public String getAqua_Farmer_Bank_Place() {
        return Aqua_Farmer_Bank_Place;
    }

    public void setAqua_Farmer_Bank_Place(String aqua_Farmer_Bank_Place) {
        Aqua_Farmer_Bank_Place = aqua_Farmer_Bank_Place;
    }

    public String getVillage_Name() {
        return Village_Name;
    }

    public void setVillage_Name(String village_Name) {
        Village_Name = village_Name;
    }

    public String getProduct_Variety_Code() {
        return Product_Variety_Code;
    }

    public void setProduct_Variety_Code(String product_Variety_Code) {
        Product_Variety_Code = product_Variety_Code;
    }
}
