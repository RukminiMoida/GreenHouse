package com.PG.GreenHouse.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ActualDetails {
    @SerializedName("Procurement_Sch_Date")
    private String date;

    @SerializedName("Procurement_Schedule_No")
    private String sch_no;

    @SerializedName("Vehicle_ID")
    private String vehecial_id;

    @SerializedName("Grader_Emp_Id")
    private ArrayList<String> grader_emp_id;

    @SerializedName("Helper_Emp_Id_1")
    private ArrayList<String> helper_1;

    @SerializedName("Helper_Emp_Id_2")
    private ArrayList<String> helper_2;

    @SerializedName("enqlist")
    private ArrayList<String> enqlist;

    public ActualDetails(String date, String sch_no, String vehecial_id, ArrayList<String> grader_emp_id, ArrayList<String> helper_1, ArrayList<String> helper_2, ArrayList<String> enqlist) {
        this.date = date;
        this.sch_no = sch_no;
        this.vehecial_id = vehecial_id;
        this.grader_emp_id = grader_emp_id;
        this.helper_1 = helper_1;
        this.helper_2 = helper_2;
        this.enqlist = enqlist;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSch_no() {
        return sch_no;
    }

    public void setSch_no(String sch_no) {
        this.sch_no = sch_no;
    }

    public String getVehecial_id() {
        return vehecial_id;
    }

    public void setVehecial_id(String vehecial_id) {
        this.vehecial_id = vehecial_id;
    }

    public ArrayList<String> getGrader_emp_id() {
        return grader_emp_id;
    }

    public void setGrader_emp_id(ArrayList<String> grader_emp_id) {
        this.grader_emp_id = grader_emp_id;
    }

    public ArrayList<String> getHelper_1() {
        return helper_1;
    }

    public void setHelper_1(ArrayList<String> helper_1) {
        this.helper_1 = helper_1;
    }

    public ArrayList<String> getHelper_2() {
        return helper_2;
    }

    public void setHelper_2(ArrayList<String> helper_2) {
        this.helper_2 = helper_2;
    }

    public ArrayList<String> getEnqlist() {
        return enqlist;
    }

    public void setEnqlist(ArrayList<String> enqlist) {
        this.enqlist = enqlist;
    }
}
