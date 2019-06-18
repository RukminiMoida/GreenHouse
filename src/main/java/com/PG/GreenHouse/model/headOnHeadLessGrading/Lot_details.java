package com.PG.GreenHouse.model.headOnHeadLessGrading;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Lot_details implements Serializable {

    @SerializedName("LotNo")
    private String LotNo;

    @SerializedName("ProductVarietyCode")
    private String pvc;

    @SerializedName("VarietyCountCode")
    private String vcc;

    @SerializedName("Lot_date")
    private String Lot_date;

    @SerializedName("Product_Variety_Name")
    private String Product_Variety_Name;

    @SerializedName("Variety_Count")
    private String Variety_Count;

    @SerializedName("MaterialGroupCode")
    private String MaterialGroupCode;

    @SerializedName("Material_Group_Name")
    private String Material_Group_Name;

    @SerializedName("quantity")
    private String quantity;

    public Lot_details(String lotNo, String pvc, String vcc, String lot_date, String product_Variety_Name, String variety_Count, String materialGroupCode, String material_Group_Name, String quantity) {
        LotNo = lotNo;
        this.pvc = pvc;
        this.vcc = vcc;
        Lot_date = lot_date;
        Product_Variety_Name = product_Variety_Name;
        Variety_Count = variety_Count;
        MaterialGroupCode = materialGroupCode;
        Material_Group_Name = material_Group_Name;
        this.quantity = quantity;
    }

    public String getLotNo() {
        return LotNo;
    }

    public void setLotNo(String lotNo) {
        LotNo = lotNo;
    }

    public String getPvc() {
        return pvc;
    }

    public void setPvc(String pvc) {
        this.pvc = pvc;
    }

    public String getVcc() {
        return vcc;
    }

    public void setVcc(String vcc) {
        this.vcc = vcc;
    }

    public String getLot_date() {
        return Lot_date;
    }

    public void setLot_date(String lot_date) {
        Lot_date = lot_date;
    }

    public String getProduct_Variety_Name() {
        return Product_Variety_Name;
    }

    public void setProduct_Variety_Name(String product_Variety_Name) {
        Product_Variety_Name = product_Variety_Name;
    }

    public String getVariety_Count() {
        return Variety_Count;
    }

    public void setVariety_Count(String variety_Count) {
        Variety_Count = variety_Count;
    }

    public String getMaterialGroupCode() {
        return MaterialGroupCode;
    }

    public void setMaterialGroupCode(String materialGroupCode) {
        MaterialGroupCode = materialGroupCode;
    }

    public String getMaterial_Group_Name() {
        return Material_Group_Name;
    }

    public void setMaterial_Group_Name(String material_Group_Name) {
        Material_Group_Name = material_Group_Name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
