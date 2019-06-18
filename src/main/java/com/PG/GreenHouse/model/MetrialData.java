package com.PG.GreenHouse.model;

import com.google.gson.annotations.SerializedName;

public class MetrialData {

    @SerializedName("Material_Group_Code")
    private String Material_Group_Code;

    @SerializedName("Material_Group_Name")
    private String Material_Group_Name;

    @SerializedName("Product_Variety_Code")
    private String Product_Variety_Code;

    @SerializedName("Product_Variety_Name")
    private String Product_Variety_Name;

    @SerializedName("Variety_Count_Code")
    private String Variety_Count_Code;

    @SerializedName("Variety_Count")
    private String Variety_Count;

    public MetrialData(String material_Group_Code, String material_Group_Name, String product_Variety_Code, String product_Variety_Name, String variety_Count_Code, String variety_Count) {
        Material_Group_Code = material_Group_Code;
        Material_Group_Name = material_Group_Name;
        Product_Variety_Code = product_Variety_Code;
        Product_Variety_Name = product_Variety_Name;
        Variety_Count_Code = variety_Count_Code;
        Variety_Count = variety_Count;
    }

    public String getMaterial_Group_Code() {
        return Material_Group_Code;
    }

    public void setMaterial_Group_Code(String material_Group_Code) {
        Material_Group_Code = material_Group_Code;
    }

    public String getMaterial_Group_Name() {
        return Material_Group_Name;
    }

    public void setMaterial_Group_Name(String material_Group_Name) {
        Material_Group_Name = material_Group_Name;
    }

    public String getProduct_Variety_Code() {
        return Product_Variety_Code;
    }

    public void setProduct_Variety_Code(String product_Variety_Code) {
        Product_Variety_Code = product_Variety_Code;
    }

    public String getProduct_Variety_Name() {
        return Product_Variety_Name;
    }

    public void setProduct_Variety_Name(String product_Variety_Name) {
        Product_Variety_Name = product_Variety_Name;
    }

    public String getVariety_Count_Code() {
        return Variety_Count_Code;
    }

    public void setVariety_Count_Code(String variety_Count_Code) {
        Variety_Count_Code = variety_Count_Code;
    }

    public String getVariety_Count() {
        return Variety_Count;
    }

    public void setVariety_Count(String variety_Count) {
        Variety_Count = variety_Count;
    }
}
