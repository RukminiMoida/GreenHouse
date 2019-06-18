package com.PG.GreenHouse.model.FactoryWeighment;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Codes implements Serializable {

    @SerializedName("Product_Variety_Code")
    private String Product_Variety_Code;

    @SerializedName("Product_Variety_Name")
    private String Product_Variety_Name;

    @SerializedName("Fk_Material_Group_Code")
    private String Fk_Material_Group_Code;

    @SerializedName("Material_Group_Name")
    private String Material_Group_Name;

    @SerializedName("Codes")
    private ArrayList<ActualCodes> Variety_Count;

    public Codes(String product_Variety_Code, String product_Variety_Name, String fk_Material_Group_Code, String material_Group_Name, ArrayList<ActualCodes> variety_Count) {
        Product_Variety_Code = product_Variety_Code;
        Product_Variety_Name = product_Variety_Name;
        Fk_Material_Group_Code = fk_Material_Group_Code;
        Material_Group_Name = material_Group_Name;
        Variety_Count = variety_Count;
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

    public String getFk_Material_Group_Code() {
        return Fk_Material_Group_Code;
    }

    public void setFk_Material_Group_Code(String fk_Material_Group_Code) {
        Fk_Material_Group_Code = fk_Material_Group_Code;
    }

    public String getMaterial_Group_Name() {
        return Material_Group_Name;
    }

    public void setMaterial_Group_Name(String material_Group_Name) {
        Material_Group_Name = material_Group_Name;
    }

    public ArrayList<ActualCodes> getVariety_Count() {
        return Variety_Count;
    }

    public void setVariety_Count(ArrayList<ActualCodes> variety_Count) {
        Variety_Count = variety_Count;
    }
}
