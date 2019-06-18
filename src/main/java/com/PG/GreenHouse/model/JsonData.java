package com.PG.GreenHouse.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JsonData {

    @SerializedName("Production_Process_Schedule_No")
    private String Production_Process_Schedule_No;

    @SerializedName("Lot_No")
    private String Lot_No;

    @SerializedName("Org_office_no")
    private String Org_office_no;

    @SerializedName("Product_Process_Code")
    private String Product_Process_Code;

    @SerializedName("VAP_Weight_No")
    private String VAP_Weight_No;

    @SerializedName("VAP_Workers_Nos")
    private String VAP_Workers_Nos;

    @SerializedName("VAP_Tables_No")
    private String VAP_Tables_No;


    @SerializedName("VAP_Supervisor_Emp_Id")
    private String VAP_Supervisor_Emp_Id;


    @SerializedName("VAP_Process_No")
    private String VAP_Process_No;


    @SerializedName("c87")
    private double c87;

    @SerializedName("VAP_Group_Total_Qty")
    private double VAP_Group_Total_Qty;

    @SerializedName("VAP_Weighment_Date_Time")
    private ArrayList<String> VAP_Weighment_Date_Time;

    @SerializedName("VAP_No_of_Nets")
    private ArrayList<String> VAP_No_of_Nets;

    @SerializedName("VAP_Net_Tare_Wt")
    private ArrayList<String> VAP_Net_Tare_Wt;

    @SerializedName("VAP_Total_Weight")
    private ArrayList<String> VAP_Total_Weight;

    @SerializedName("VAP_Total_Tare_Weight")
    private ArrayList<String> VAP_Total_Tare_Weight;

    @SerializedName("VAP_Net_Weight")
    private ArrayList<String> VAP_Net_Weight;

    @SerializedName("VAP_Group_Emp_id")
    private ArrayList<String> VAP_Group_Emp_id;

    @SerializedName("Variety_Count_Code")
    private ArrayList<String> Variety_Count_Code;

    public JsonData(String production_Process_Schedule_No, String lot_No, String org_office_no, String product_Process_Code, String VAP_Weight_No, String VAP_Workers_Nos, String VAP_Tables_No, String VAP_Supervisor_Emp_Id, String VAP_Process_No, double c87, double VAP_Group_Total_Qty, ArrayList<String> VAP_Weighment_Date_Time, ArrayList<String> VAP_No_of_Nets, ArrayList<String> VAP_Net_Tare_Wt, ArrayList<String> VAP_Total_Weight, ArrayList<String> VAP_Total_Tare_Weight, ArrayList<String> VAP_Net_Weight, ArrayList<String> VAP_Group_Emp_id, ArrayList<String> variety_Count_Code) {
        this.Production_Process_Schedule_No = production_Process_Schedule_No;
        this.Lot_No = lot_No;
        this.Org_office_no = org_office_no;
        this.Product_Process_Code = product_Process_Code;
        this.VAP_Weight_No = VAP_Weight_No;
        this.VAP_Workers_Nos = VAP_Workers_Nos;
        this.VAP_Tables_No = VAP_Tables_No;
        this.VAP_Supervisor_Emp_Id = VAP_Supervisor_Emp_Id;
        this.VAP_Process_No = VAP_Process_No;
        this.c87 = c87;
        this.VAP_Group_Total_Qty = VAP_Group_Total_Qty;
        this.VAP_Weighment_Date_Time = VAP_Weighment_Date_Time;
        this.VAP_No_of_Nets = VAP_No_of_Nets;
        this.VAP_Net_Tare_Wt = VAP_Net_Tare_Wt;
        this.VAP_Total_Weight = VAP_Total_Weight;
        this.VAP_Total_Tare_Weight = VAP_Total_Tare_Weight;
        this.VAP_Net_Weight = VAP_Net_Weight;
        this.VAP_Group_Emp_id = VAP_Group_Emp_id;
        this.Variety_Count_Code = variety_Count_Code;
    }

    public String getProduction_Process_Schedule_No() {
        return Production_Process_Schedule_No;
    }

    public void setProduction_Process_Schedule_No(String production_Process_Schedule_No) {
        Production_Process_Schedule_No = production_Process_Schedule_No;
    }

    public String getLot_No() {
        return Lot_No;
    }

    public void setLot_No(String lot_No) {
        Lot_No = lot_No;
    }

    public String getOrg_office_no() {
        return Org_office_no;
    }

    public void setOrg_office_no(String org_office_no) {
        Org_office_no = org_office_no;
    }

    public String getProduct_Process_Code() {
        return Product_Process_Code;
    }

    public void setProduct_Process_Code(String product_Process_Code) {
        Product_Process_Code = product_Process_Code;
    }

    public String getVAP_Weight_No() {
        return VAP_Weight_No;
    }

    public void setVAP_Weight_No(String VAP_Weight_No) {
        this.VAP_Weight_No = VAP_Weight_No;
    }

    public String getVAP_Workers_Nos() {
        return VAP_Workers_Nos;
    }

    public void setVAP_Workers_Nos(String VAP_Workers_Nos) {
        this.VAP_Workers_Nos = VAP_Workers_Nos;
    }

    public String getVAP_Tables_No() {
        return VAP_Tables_No;
    }

    public void setVAP_Tables_No(String VAP_Tables_No) {
        this.VAP_Tables_No = VAP_Tables_No;
    }

    public String getVAP_Supervisor_Emp_Id() {
        return VAP_Supervisor_Emp_Id;
    }

    public void setVAP_Supervisor_Emp_Id(String VAP_Supervisor_Emp_Id) {
        this.VAP_Supervisor_Emp_Id = VAP_Supervisor_Emp_Id;
    }

    public String getVAP_Process_No() {
        return VAP_Process_No;
    }

    public void setVAP_Process_No(String VAP_Process_No) {
        this.VAP_Process_No = VAP_Process_No;
    }

    public double getC87() {
        return c87;
    }

    public void setC87(double c87) {
        this.c87 = c87;
    }

    public double getVAP_Group_Total_Qty() {
        return VAP_Group_Total_Qty;
    }

    public void setVAP_Group_Total_Qty(double VAP_Group_Total_Qty) {
        this.VAP_Group_Total_Qty = VAP_Group_Total_Qty;
    }

    public ArrayList<String> getVAP_Weighment_Date_Time() {
        return VAP_Weighment_Date_Time;
    }

    public void setVAP_Weighment_Date_Time(ArrayList<String> VAP_Weighment_Date_Time) {
        this.VAP_Weighment_Date_Time = VAP_Weighment_Date_Time;
    }

    public ArrayList<String> getVAP_No_of_Nets() {
        return VAP_No_of_Nets;
    }

    public void setVAP_No_of_Nets(ArrayList<String> VAP_No_of_Nets) {
        this.VAP_No_of_Nets = VAP_No_of_Nets;
    }

    public ArrayList<String> getVAP_Net_Tare_Wt() {
        return VAP_Net_Tare_Wt;
    }

    public void setVAP_Net_Tare_Wt(ArrayList<String> VAP_Net_Tare_Wt) {
        this.VAP_Net_Tare_Wt = VAP_Net_Tare_Wt;
    }

    public ArrayList<String> getVAP_Total_Weight() {
        return VAP_Total_Weight;
    }

    public void setVAP_Total_Weight(ArrayList<String> VAP_Total_Weight) {
        this.VAP_Total_Weight = VAP_Total_Weight;
    }

    public ArrayList<String> getVAP_Total_Tare_Weight() {
        return VAP_Total_Tare_Weight;
    }

    public void setVAP_Total_Tare_Weight(ArrayList<String> VAP_Total_Tare_Weight) {
        this.VAP_Total_Tare_Weight = VAP_Total_Tare_Weight;
    }

    public ArrayList<String> getVAP_Net_Weight() {
        return VAP_Net_Weight;
    }

    public void setVAP_Net_Weight(ArrayList<String> VAP_Net_Weight) {
        this.VAP_Net_Weight = VAP_Net_Weight;
    }

    public ArrayList<String> getVAP_Group_Emp_id() {
        return VAP_Group_Emp_id;
    }

    public void setVAP_Group_Emp_id(ArrayList<String> VAP_Group_Emp_id) {
        this.VAP_Group_Emp_id = VAP_Group_Emp_id;
    }

    public ArrayList<String> getVariety_Count_Code() {
        return Variety_Count_Code;
    }

    public void setVariety_Count_Code(ArrayList<String> variety_Count_Code) {
        Variety_Count_Code = variety_Count_Code;
    }
}
