package com.PG.GreenHouse.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SiteWeighmentInsertionData {

    @SerializedName("Farmer_Pond_No")
    private String Farmer_Pond_No;

    @SerializedName("Farmer_Location")
    private String Farmer_Location;

    @SerializedName("Farmer_Pond_Navigation_Details")
    private String Farmer_Pond_Navigation_Details;

    @SerializedName("Procurement_Schedule_No")
    private String Procurement_Schedule_No;

    @SerializedName("Enquiry_No")
    private String Enquiry_No;

    @SerializedName("SW_No_of_Nets")
    private ArrayList<String> SW_No_of_Nets;

    @SerializedName("SW_Total_Weight")
    private ArrayList<String> SW_Total_Weight;

    @SerializedName("SW_Tare_Weight")
    private ArrayList<String> SW_Tare_Weight;

    @SerializedName("SW_Total_Tare_Weight")
    private ArrayList<String> SW_Total_Tare_Weight;

    @SerializedName("SW_Net_Weight")
    private ArrayList<String> SW_Net_Weight;

    @SerializedName("Emp_id")
    private String emp_id;

    @SerializedName("Site_Weighment_Date_Time")
    private ArrayList<String> Site_Weighment_Date_Time;

    @SerializedName("Site_Weighment_count")
    private ArrayList<String> Site_Weighment_count;

    public SiteWeighmentInsertionData(String farmer_Pond_No, String farmer_Location, String farmer_Pond_Navigation_Details, String procurement_Schedule_No, String enquiry_No, ArrayList<String> SW_No_of_Nets, ArrayList<String> SW_Total_Weight, ArrayList<String> SW_Tare_Weight, ArrayList<String> SW_Total_Tare_Weight, ArrayList<String> SW_Net_Weight, String emp_id, ArrayList<String> site_Weighment_Date_Time, ArrayList<String> site_Weighment_count) {
        Farmer_Pond_No = farmer_Pond_No;
        Farmer_Location = farmer_Location;
        Farmer_Pond_Navigation_Details = farmer_Pond_Navigation_Details;
        Procurement_Schedule_No = procurement_Schedule_No;
        Enquiry_No = enquiry_No;
        this.SW_No_of_Nets = SW_No_of_Nets;
        this.SW_Total_Weight = SW_Total_Weight;
        this.SW_Tare_Weight = SW_Tare_Weight;
        this.SW_Total_Tare_Weight = SW_Total_Tare_Weight;
        this.SW_Net_Weight = SW_Net_Weight;
        this.emp_id = emp_id;
        Site_Weighment_Date_Time = site_Weighment_Date_Time;
        Site_Weighment_count = site_Weighment_count;
    }

    public String getFarmer_Pond_No() {
        return Farmer_Pond_No;
    }

    public void setFarmer_Pond_No(String farmer_Pond_No) {
        Farmer_Pond_No = farmer_Pond_No;
    }

    public String getFarmer_Location() {
        return Farmer_Location;
    }

    public void setFarmer_Location(String farmer_Location) {
        Farmer_Location = farmer_Location;
    }

    public String getFarmer_Pond_Navigation_Details() {
        return Farmer_Pond_Navigation_Details;
    }

    public void setFarmer_Pond_Navigation_Details(String farmer_Pond_Navigation_Details) {
        Farmer_Pond_Navigation_Details = farmer_Pond_Navigation_Details;
    }

    public String getProcurement_Schedule_No() {
        return Procurement_Schedule_No;
    }

    public void setProcurement_Schedule_No(String procurement_Schedule_No) {
        Procurement_Schedule_No = procurement_Schedule_No;
    }

    public String getEnquiry_No() {
        return Enquiry_No;
    }

    public void setEnquiry_No(String enquiry_No) {
        Enquiry_No = enquiry_No;
    }

    public ArrayList<String> getSW_No_of_Nets() {
        return SW_No_of_Nets;
    }

    public void setSW_No_of_Nets(ArrayList<String> SW_No_of_Nets) {
        this.SW_No_of_Nets = SW_No_of_Nets;
    }

    public ArrayList<String> getSW_Total_Weight() {
        return SW_Total_Weight;
    }

    public void setSW_Total_Weight(ArrayList<String> SW_Total_Weight) {
        this.SW_Total_Weight = SW_Total_Weight;
    }

    public ArrayList<String> getSW_Tare_Weight() {
        return SW_Tare_Weight;
    }

    public void setSW_Tare_Weight(ArrayList<String> SW_Tare_Weight) {
        this.SW_Tare_Weight = SW_Tare_Weight;
    }

    public ArrayList<String> getSW_Total_Tare_Weight() {
        return SW_Total_Tare_Weight;
    }

    public void setSW_Total_Tare_Weight(ArrayList<String> SW_Total_Tare_Weight) {
        this.SW_Total_Tare_Weight = SW_Total_Tare_Weight;
    }

    public ArrayList<String> getSW_Net_Weight() {
        return SW_Net_Weight;
    }

    public void setSW_Net_Weight(ArrayList<String> SW_Net_Weight) {
        this.SW_Net_Weight = SW_Net_Weight;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public ArrayList<String> getSite_Weighment_Date_Time() {
        return Site_Weighment_Date_Time;
    }

    public void setSite_Weighment_Date_Time(ArrayList<String> site_Weighment_Date_Time) {
        Site_Weighment_Date_Time = site_Weighment_Date_Time;
    }

    public ArrayList<String> getSite_Weighment_count() {
        return Site_Weighment_count;
    }

    public void setSite_Weighment_count(ArrayList<String> site_Weighment_count) {
        Site_Weighment_count = site_Weighment_count;
    }
}
