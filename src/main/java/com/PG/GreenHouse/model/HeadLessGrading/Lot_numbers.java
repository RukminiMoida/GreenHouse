package com.PG.GreenHouse.model.HeadLessGrading;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Lot_numbers implements Serializable {
    @SerializedName("Lot_No")
    private String Lot_No;

    @SerializedName("Lot_Date")
    private String Lot_Date;

    @SerializedName("Variety_Count")
    private String Variety_Count;

    @SerializedName("Quantity")
    private String Quantity;

    @SerializedName("Fk_Product_Variety_Code")
    private String Fk_Product_Variety_Code;

    @SerializedName("Product_Variety_Name")
    private String Product_Variety_Name;

    @SerializedName("Variety_Count_Code")
    private String Fk_Variety_Count_Code;

    @SerializedName("quantity")
    private String quantity;

    public Lot_numbers(String lot_No, String lot_Date, String variety_Count, String quantity, String fk_Product_Variety_Code, String product_Variety_Name, String fk_Variety_Count_Code, String quantity1) {
        Lot_No = lot_No;
        Lot_Date = lot_Date;
        Variety_Count = variety_Count;
        Quantity = quantity;
        Fk_Product_Variety_Code = fk_Product_Variety_Code;
        Product_Variety_Name = product_Variety_Name;
        Fk_Variety_Count_Code = fk_Variety_Count_Code;
        this.quantity = quantity1;
    }

    public String getLot_No() {
        return Lot_No;
    }

    public void setLot_No(String lot_No) {
        Lot_No = lot_No;
    }

    public String getLot_Date() {
        return Lot_Date;
    }

    public void setLot_Date(String lot_Date) {
        Lot_Date = lot_Date;
    }

    public String getVariety_Count() {
        return Variety_Count;
    }

    public void setVariety_Count(String variety_Count) {
        Variety_Count = variety_Count;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getFk_Product_Variety_Code() {
        return Fk_Product_Variety_Code;
    }

    public void setFk_Product_Variety_Code(String fk_Product_Variety_Code) {
        Fk_Product_Variety_Code = fk_Product_Variety_Code;
    }

    public String getProduct_Variety_Name() {
        return Product_Variety_Name;
    }

    public void setProduct_Variety_Name(String product_Variety_Name) {
        Product_Variety_Name = product_Variety_Name;
    }

    public String getFk_Variety_Count_Code() {
        return Fk_Variety_Count_Code;
    }

    public void setFk_Variety_Count_Code(String fk_Variety_Count_Code) {
        Fk_Variety_Count_Code = fk_Variety_Count_Code;
    }
}
