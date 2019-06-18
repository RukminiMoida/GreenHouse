package com.PG.GreenHouse.model.HeadOnGrading;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class InsertDataHeadon implements Serializable {

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


    @SerializedName("HOGR_Weighment_Date_Time")
    private ArrayList<String> HOGR_Weighment_Date_Time;

    @SerializedName("HOG_No_of_Nets")
    private ArrayList<String> HOG_No_of_Nets;

    @SerializedName("HOG_Total_Weight")
    private ArrayList<String> HOG_Total_Weight;

    @SerializedName("HOG_Tare_Weight")
    private ArrayList<String> HOG_Tare_Weight;

    @SerializedName("HOG_Total_Tare_Weight")
    private ArrayList<String> HOG_Total_Tare_Weight;

    @SerializedName("HOG_Net_Weight")
    private ArrayList<String> HOG_Net_Weight;

    @SerializedName("HOGS_Emp_id")
    private String HOGS_Emp_id;

    @SerializedName("Variety_Count_Code")
    private String HLGS_Count_Code;

    public InsertDataHeadon(String lot_No, String lot_Date, ArrayList<String> FP_Group_Code, ArrayList<String> FP_Variety_Code, ArrayList<String> FP_Production_Grade_Code, ArrayList<String> HOGR_Weighment_Date_Time, ArrayList<String> HOG_No_of_Nets, ArrayList<String> HOG_Total_Weight, ArrayList<String> HOG_Tare_Weight, ArrayList<String> HOG_Total_Tare_Weight, ArrayList<String> HOG_Net_Weight, String HOGS_Emp_id, String HLGS_Count_Code) {
        Lot_No = lot_No;
        Lot_Date = lot_Date;
        this.FP_Group_Code = FP_Group_Code;
        this.FP_Variety_Code = FP_Variety_Code;
        this.FP_Production_Grade_Code = FP_Production_Grade_Code;
        this.HOGR_Weighment_Date_Time = HOGR_Weighment_Date_Time;
        this.HOG_No_of_Nets = HOG_No_of_Nets;
        this.HOG_Total_Weight = HOG_Total_Weight;
        this.HOG_Tare_Weight = HOG_Tare_Weight;
        this.HOG_Total_Tare_Weight = HOG_Total_Tare_Weight;
        this.HOG_Net_Weight = HOG_Net_Weight;
        this.HOGS_Emp_id = HOGS_Emp_id;
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

    public ArrayList<String> getHOGR_Weighment_Date_Time() {
        return HOGR_Weighment_Date_Time;
    }

    public void setHOGR_Weighment_Date_Time(ArrayList<String> HOGR_Weighment_Date_Time) {
        this.HOGR_Weighment_Date_Time = HOGR_Weighment_Date_Time;
    }

    public ArrayList<String> getHOG_No_of_Nets() {
        return HOG_No_of_Nets;
    }

    public void setHOG_No_of_Nets(ArrayList<String> HOG_No_of_Nets) {
        this.HOG_No_of_Nets = HOG_No_of_Nets;
    }

    public ArrayList<String> getHOG_Total_Weight() {
        return HOG_Total_Weight;
    }

    public void setHOG_Total_Weight(ArrayList<String> HOG_Total_Weight) {
        this.HOG_Total_Weight = HOG_Total_Weight;
    }

    public ArrayList<String> getHOG_Tare_Weight() {
        return HOG_Tare_Weight;
    }

    public void setHOG_Tare_Weight(ArrayList<String> HOG_Tare_Weight) {
        this.HOG_Tare_Weight = HOG_Tare_Weight;
    }

    public ArrayList<String> getHOG_Total_Tare_Weight() {
        return HOG_Total_Tare_Weight;
    }

    public void setHOG_Total_Tare_Weight(ArrayList<String> HOG_Total_Tare_Weight) {
        this.HOG_Total_Tare_Weight = HOG_Total_Tare_Weight;
    }

    public ArrayList<String> getHOG_Net_Weight() {
        return HOG_Net_Weight;
    }

    public void setHOG_Net_Weight(ArrayList<String> HOG_Net_Weight) {
        this.HOG_Net_Weight = HOG_Net_Weight;
    }

    public String getHOGS_Emp_id() {
        return HOGS_Emp_id;
    }

    public void setHOGS_Emp_id(String HOGS_Emp_id) {
        this.HOGS_Emp_id = HOGS_Emp_id;
    }

    public String getHLGS_Count_Code() {
        return HLGS_Count_Code;
    }

    public void setHLGS_Count_Code(String HLGS_Count_Code) {
        this.HLGS_Count_Code = HLGS_Count_Code;
    }
}
