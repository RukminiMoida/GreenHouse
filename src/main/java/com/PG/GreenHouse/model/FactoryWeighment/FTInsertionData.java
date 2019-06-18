package com.PG.GreenHouse.model.FactoryWeighment;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FTInsertionData {

    @SerializedName("fws_emp_id")
    private String fws_emp_id;

    @SerializedName("Lot_No")
    private String Lot_No;

    @SerializedName("Material_Group_Code")
    private String Material_Group_Code;

    @SerializedName("Product_Variety_Code")
    private String Product_Variety_Code;

    @SerializedName("Variety_Count_Code")
    private ArrayList<String> Variety_Count_Code;

    @SerializedName("Factory_Weighment_Date_Time")
    private ArrayList<String> Factory_Weighment_Date_Time;

    @SerializedName("FW_No_of_Nets")
    private ArrayList<String> FW_No_of_Nets;

    @SerializedName("FW_Total_Weight")
    private ArrayList<String> FW_Total_Weight;

    @SerializedName("FW_Tare_Weight")
    private ArrayList<String> FW_Tare_Weight;

    @SerializedName("FW_Total_Tare_Weight")
    private ArrayList<String> FW_Total_Tare_Weight;

    @SerializedName("FW_Net_Weight")
    private ArrayList<String> FW_Net_Weight;


    public FTInsertionData(String fws_emp_id, String lot_No, String material_Group_Code, String product_Variety_Code, ArrayList<String> variety_Count_Code, ArrayList<String> factory_Weighment_Date_Time, ArrayList<String> FW_No_of_Nets, ArrayList<String> FW_Total_Weight, ArrayList<String> FW_Tare_Weight, ArrayList<String> FW_Total_Tare_Weight, ArrayList<String> FW_Net_Weight) {
        this.fws_emp_id = fws_emp_id;
        Lot_No = lot_No;
        Material_Group_Code = material_Group_Code;
        Product_Variety_Code = product_Variety_Code;
        Variety_Count_Code = variety_Count_Code;
        Factory_Weighment_Date_Time = factory_Weighment_Date_Time;
        this.FW_No_of_Nets = FW_No_of_Nets;
        this.FW_Total_Weight = FW_Total_Weight;
        this.FW_Tare_Weight = FW_Tare_Weight;
        this.FW_Total_Tare_Weight = FW_Total_Tare_Weight;
        this.FW_Net_Weight = FW_Net_Weight;
    }

    public String getFws_emp_id() {
        return fws_emp_id;
    }

    public void setFws_emp_id(String fws_emp_id) {
        this.fws_emp_id = fws_emp_id;
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

    public ArrayList<String> getVariety_Count_Code() {
        return Variety_Count_Code;
    }

    public void setVariety_Count_Code(ArrayList<String> variety_Count_Code) {
        Variety_Count_Code = variety_Count_Code;
    }

    public ArrayList<String> getFactory_Weighment_Date_Time() {
        return Factory_Weighment_Date_Time;
    }

    public void setFactory_Weighment_Date_Time(ArrayList<String> factory_Weighment_Date_Time) {
        Factory_Weighment_Date_Time = factory_Weighment_Date_Time;
    }

    public ArrayList<String> getFW_No_of_Nets() {
        return FW_No_of_Nets;
    }

    public void setFW_No_of_Nets(ArrayList<String> FW_No_of_Nets) {
        this.FW_No_of_Nets = FW_No_of_Nets;
    }

    public ArrayList<String> getFW_Total_Weight() {
        return FW_Total_Weight;
    }

    public void setFW_Total_Weight(ArrayList<String> FW_Total_Weight) {
        this.FW_Total_Weight = FW_Total_Weight;
    }

    public ArrayList<String> getFW_Tare_Weight() {
        return FW_Tare_Weight;
    }

    public void setFW_Tare_Weight(ArrayList<String> FW_Tare_Weight) {
        this.FW_Tare_Weight = FW_Tare_Weight;
    }

    public ArrayList<String> getFW_Total_Tare_Weight() {
        return FW_Total_Tare_Weight;
    }

    public void setFW_Total_Tare_Weight(ArrayList<String> FW_Total_Tare_Weight) {
        this.FW_Total_Tare_Weight = FW_Total_Tare_Weight;
    }

    public ArrayList<String> getFW_Net_Weight() {
        return FW_Net_Weight;
    }

    public void setFW_Net_Weight(ArrayList<String> FW_Net_Weight) {
        this.FW_Net_Weight = FW_Net_Weight;
    }
}
