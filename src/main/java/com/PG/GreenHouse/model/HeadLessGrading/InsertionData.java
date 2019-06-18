package com.PG.GreenHouse.model.HeadLessGrading;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class InsertionData implements Serializable {

    @SerializedName("Lot_No")
    private String Lot_No;

    @SerializedName("Lot_Date")
    private String Lot_Date;

    @SerializedName("FP_Group_Code")
    private ArrayList<String> FP_Group_Code;

    @SerializedName("FP_Variety_Code")
    private ArrayList<String> FP_Variety_Code;

    @SerializedName("FP_Production_Grade_Code")
    private ArrayList<String> FP_Production_Grade_Code;


    @SerializedName("HLW_Weighment_Date_Time")
    private ArrayList<String> HLW_Weighment_Date_Time;

    @SerializedName("HLG_No_of_Nets")
    private ArrayList<String> HLG_No_of_Nets;

    @SerializedName("HLG_Total_Weight")
    private ArrayList<String> HLG_Total_Weight;

    @SerializedName("HLG_Tare_Weight")
    private ArrayList<String> HLG_Tare_Weight;

    @SerializedName("HLG_Total_Tare_Weight")
    private ArrayList<String> HLG_Total_Tare_Weight;

    @SerializedName("HLG_Net_Weight")
    private ArrayList<String> HLG_Net_Weight;

    @SerializedName("HLGS_Emp_id")
    private String HLGS_Emp_id;

    @SerializedName("Variety_Count_Code")
    private String HLGS_Count_Code;


    public InsertionData(String lot_No, String lot_Date, ArrayList<String> FP_Group_Code, ArrayList<String> FP_Variety_Code, ArrayList<String> FP_Production_Grade_Code, ArrayList<String> HLW_Weighment_Date_Time, ArrayList<String> HLG_No_of_Nets, ArrayList<String> HLG_Total_Weight, ArrayList<String> HLG_Tare_Weight, ArrayList<String> HLG_Total_Tare_Weight, ArrayList<String> HLG_Net_Weight, String HLGS_Emp_id, String HLGS_Count_Code) {
        Lot_No = lot_No;
        Lot_Date = lot_Date;
        this.FP_Group_Code = FP_Group_Code;
        this.FP_Variety_Code = FP_Variety_Code;
        this.FP_Production_Grade_Code = FP_Production_Grade_Code;
        this.HLW_Weighment_Date_Time = HLW_Weighment_Date_Time;
        this.HLG_No_of_Nets = HLG_No_of_Nets;
        this.HLG_Total_Weight = HLG_Total_Weight;
        this.HLG_Tare_Weight = HLG_Tare_Weight;
        this.HLG_Total_Tare_Weight = HLG_Total_Tare_Weight;
        this.HLG_Net_Weight = HLG_Net_Weight;
        this.HLGS_Emp_id = HLGS_Emp_id;
        this.HLGS_Count_Code = HLGS_Count_Code;
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

    public ArrayList<String> getFP_Group_Code() {
        return FP_Group_Code;
    }

    public void setFP_Group_Code(ArrayList<String> FP_Group_Code) {
        this.FP_Group_Code = FP_Group_Code;
    }

    public ArrayList<String> getFP_Variety_Code() {
        return FP_Variety_Code;
    }

    public void setFP_Variety_Code(ArrayList<String> FP_Variety_Code) {
        this.FP_Variety_Code = FP_Variety_Code;
    }

    public ArrayList<String> getFP_Production_Grade_Code() {
        return FP_Production_Grade_Code;
    }

    public void setFP_Production_Grade_Code(ArrayList<String> FP_Production_Grade_Code) {
        this.FP_Production_Grade_Code = FP_Production_Grade_Code;
    }

    public ArrayList<String> getHLW_Weighment_Date_Time() {
        return HLW_Weighment_Date_Time;
    }

    public void setHLW_Weighment_Date_Time(ArrayList<String> HLW_Weighment_Date_Time) {
        this.HLW_Weighment_Date_Time = HLW_Weighment_Date_Time;
    }

    public ArrayList<String> getHLG_No_of_Nets() {
        return HLG_No_of_Nets;
    }

    public void setHLG_No_of_Nets(ArrayList<String> HLG_No_of_Nets) {
        this.HLG_No_of_Nets = HLG_No_of_Nets;
    }

    public ArrayList<String> getHLG_Total_Weight() {
        return HLG_Total_Weight;
    }

    public void setHLG_Total_Weight(ArrayList<String> HLG_Total_Weight) {
        this.HLG_Total_Weight = HLG_Total_Weight;
    }

    public ArrayList<String> getHLG_Tare_Weight() {
        return HLG_Tare_Weight;
    }

    public void setHLG_Tare_Weight(ArrayList<String> HLG_Tare_Weight) {
        this.HLG_Tare_Weight = HLG_Tare_Weight;
    }

    public ArrayList<String> getHLG_Total_Tare_Weight() {
        return HLG_Total_Tare_Weight;
    }

    public void setHLG_Total_Tare_Weight(ArrayList<String> HLG_Total_Tare_Weight) {
        this.HLG_Total_Tare_Weight = HLG_Total_Tare_Weight;
    }

    public ArrayList<String> getHLG_Net_Weight() {
        return HLG_Net_Weight;
    }

    public void setHLG_Net_Weight(ArrayList<String> HLG_Net_Weight) {
        this.HLG_Net_Weight = HLG_Net_Weight;
    }

    public String getHLGS_Emp_id() {
        return HLGS_Emp_id;
    }

    public void setHLGS_Emp_id(String HLGS_Emp_id) {
        this.HLGS_Emp_id = HLGS_Emp_id;
    }

    public String getHLGS_Count_Code() {
        return HLGS_Count_Code;
    }

    public void setHLGS_Count_Code(String HLGS_Count_Code) {
        this.HLGS_Count_Code = HLGS_Count_Code;
    }
}
