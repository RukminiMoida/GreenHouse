package com.PG.GreenHouse.model.headOnHeadLessGrading;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class InsertDataHOHL implements Serializable {
    @SerializedName("HLS_Emp_Id")
    private String HLS_Emp_Id;

    @SerializedName("HOHL_Net_Weight")
    private ArrayList<String> HOHL_Net_Weight;

    @SerializedName("HOHL_No_of_Nets")
    private ArrayList<String> HOHL_No_of_Nets;

    @SerializedName("HOHL_Tare_Weight")
    private ArrayList<String> HOHL_Tare_Weight;

    @SerializedName("HOHL_Total_Tare_Weight")
    private ArrayList<String> HOHL_Total_Tare_Weight;

    @SerializedName("HOHL_Total_Weight")
    private ArrayList<String> HOHL_Total_Weight;

    @SerializedName("HOHL_Weighment_Date_Time")
    private ArrayList<String> HOHL_Weighment_Date_Time;


    @SerializedName("Variety_Count_Code")
    private String Variety_Count_Code;

    @SerializedName("HLGH_Emp_Id")
    private ArrayList<String> HLGH_Emp_Id;

    @SerializedName("Lot_Date")
    private String Lot_Date;

    @SerializedName("Lot_No")
    private String Lot_No;

    @SerializedName("Material_Group_Code")
    private String Material_Group_Code;

    @SerializedName("Product_Variety_Code")
    private String Product_Variety_Code;

    public InsertDataHOHL(String HLS_Emp_Id, ArrayList<String> HOHL_Net_Weight, ArrayList<String> HOHL_No_of_Nets, ArrayList<String> HOHL_Tare_Weight, ArrayList<String> HOHL_Total_Tare_Weight, ArrayList<String> HOHL_Total_Weight, ArrayList<String> HOHL_Weighment_Date_Time, String variety_Count_Code, ArrayList<String> HLGH_Emp_Id, String lot_Date, String lot_No, String material_Group_Code, String product_Variety_Code) {
        this.HLS_Emp_Id = HLS_Emp_Id;
        this.HOHL_Net_Weight = HOHL_Net_Weight;
        this.HOHL_No_of_Nets = HOHL_No_of_Nets;
        this.HOHL_Tare_Weight = HOHL_Tare_Weight;
        this.HOHL_Total_Tare_Weight = HOHL_Total_Tare_Weight;
        this.HOHL_Total_Weight = HOHL_Total_Weight;
        this.HOHL_Weighment_Date_Time = HOHL_Weighment_Date_Time;
        Variety_Count_Code = variety_Count_Code;
        this.HLGH_Emp_Id = HLGH_Emp_Id;
        Lot_Date = lot_Date;
        Lot_No = lot_No;
        Material_Group_Code = material_Group_Code;
        Product_Variety_Code = product_Variety_Code;
    }

    public String getHLS_Emp_Id() {
        return HLS_Emp_Id;
    }

    public void setHLS_Emp_Id(String HLS_Emp_Id) {
        this.HLS_Emp_Id = HLS_Emp_Id;
    }

    public ArrayList<String> getHOHL_Net_Weight() {
        return HOHL_Net_Weight;
    }

    public void setHOHL_Net_Weight(ArrayList<String> HOHL_Net_Weight) {
        this.HOHL_Net_Weight = HOHL_Net_Weight;
    }

    public ArrayList<String> getHOHL_No_of_Nets() {
        return HOHL_No_of_Nets;
    }

    public void setHOHL_No_of_Nets(ArrayList<String> HOHL_No_of_Nets) {
        this.HOHL_No_of_Nets = HOHL_No_of_Nets;
    }

    public ArrayList<String> getHOHL_Tare_Weight() {
        return HOHL_Tare_Weight;
    }

    public void setHOHL_Tare_Weight(ArrayList<String> HOHL_Tare_Weight) {
        this.HOHL_Tare_Weight = HOHL_Tare_Weight;
    }

    public ArrayList<String> getHOHL_Total_Tare_Weight() {
        return HOHL_Total_Tare_Weight;
    }

    public void setHOHL_Total_Tare_Weight(ArrayList<String> HOHL_Total_Tare_Weight) {
        this.HOHL_Total_Tare_Weight = HOHL_Total_Tare_Weight;
    }

    public ArrayList<String> getHOHL_Total_Weight() {
        return HOHL_Total_Weight;
    }

    public void setHOHL_Total_Weight(ArrayList<String> HOHL_Total_Weight) {
        this.HOHL_Total_Weight = HOHL_Total_Weight;
    }

    public ArrayList<String> getHOHL_Weighment_Date_Time() {
        return HOHL_Weighment_Date_Time;
    }

    public void setHOHL_Weighment_Date_Time(ArrayList<String> HOHL_Weighment_Date_Time) {
        this.HOHL_Weighment_Date_Time = HOHL_Weighment_Date_Time;
    }

    public String getVariety_Count_Code() {
        return Variety_Count_Code;
    }

    public void setVariety_Count_Code(String variety_Count_Code) {
        Variety_Count_Code = variety_Count_Code;
    }

    public ArrayList<String> getHLGH_Emp_Id() {
        return HLGH_Emp_Id;
    }

    public void setHLGH_Emp_Id(ArrayList<String> HLGH_Emp_Id) {
        this.HLGH_Emp_Id = HLGH_Emp_Id;
    }

    public String getLot_Date() {
        return Lot_Date;
    }

    public void setLot_Date(String lot_Date) {
        Lot_Date = lot_Date;
    }

    public String getLot_No() {
        return Lot_No;
    }

    public void setLot_No(String lot_No) {
        Lot_No = lot_No;
    }

    public String getMaterial_Group_Code() {
        return Material_Group_Code;
    }

    public void setMaterial_Group_Code(String material_Group_Code) {
        Material_Group_Code = material_Group_Code;
    }

    public String getProduct_Variety_Code() {
        return Product_Variety_Code;
    }

    public void setProduct_Variety_Code(String product_Variety_Code) {
        Product_Variety_Code = product_Variety_Code;
    }
}
