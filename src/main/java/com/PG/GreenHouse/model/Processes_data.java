package com.PG.GreenHouse.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Processes_data implements Serializable {
    @SerializedName("VAP_Process_No")
    private String process_no;

    @SerializedName("Production_Process_Schedule_No")
    private String process_schedule_no;

    @SerializedName("Lot_No")
    private String lot_no;

    @SerializedName("Org_office_no")
    private String office_no;

    @SerializedName("Variety_Count_Code")
    private String count_code;

    @SerializedName("Product_Process_Code")
    private String process_code;

    @SerializedName("VAP_Weight_No")
    private String weight_no;

    @SerializedName("VAP_Weighment_Date_Time")
    private String weightment_date_time;

    @SerializedName("VAP_Group_Emp_id")
    private String group_emp_id;

    @SerializedName("VAP_Workers_Nos")
    private String workers_nos;

    @SerializedName("VAP_Tables_No")
    private String tables_no;

    @SerializedName("VAP_No_of_Nets")
    private String no_of_nets;

    @SerializedName("VAP_Net_Tare_Wt")
    private String net_tare_wt;

    @SerializedName("VAP_Total_Weight")
    private String totel_weight;

    @SerializedName("VAP_Total_Tare_Weight")
    private String total_tare_weight;

    @SerializedName("VAP_Net_Weight")
    private String net_weight;

    public Processes_data(String process_no, String process_schedule_no, String lot_no, String office_no, String count_code, String process_code, String weight_no, String weightment_date_time, String group_emp_id, String workers_nos, String tables_no, String no_of_nets, String net_tare_wt, String totel_weight, String total_tare_weight, String net_weight) {
        this.process_no = process_no;
        this.process_schedule_no = process_schedule_no;
        this.lot_no = lot_no;
        this.office_no = office_no;
        this.count_code = count_code;
        this.process_code = process_code;
        this.weight_no = weight_no;
        this.weightment_date_time = weightment_date_time;
        this.group_emp_id = group_emp_id;
        this.workers_nos = workers_nos;
        this.tables_no = tables_no;
        this.no_of_nets = no_of_nets;
        this.net_tare_wt = net_tare_wt;
        this.totel_weight = totel_weight;
        this.total_tare_weight = total_tare_weight;
        this.net_weight = net_weight;
    }

    public Processes_data() {

    }

    public String getProcess_no() {
        return process_no;
    }

    public void setProcess_no(String process_no) {
        this.process_no = process_no;
    }

    public String getProcess_schedule_no() {
        return process_schedule_no;
    }

    public void setProcess_schedule_no(String process_schedule_no) {
        this.process_schedule_no = process_schedule_no;
    }

    public String getLot_no() {
        return lot_no;
    }

    public void setLot_no(String lot_no) {
        this.lot_no = lot_no;
    }

    public String getOffice_no() {
        return office_no;
    }

    public void setOffice_no(String office_no) {
        this.office_no = office_no;
    }

    public String getCount_code() {
        return count_code;
    }

    public void setCount_code(String count_code) {
        this.count_code = count_code;
    }

    public String getProcess_code() {
        return process_code;
    }

    public void setProcess_code(String process_code) {
        this.process_code = process_code;
    }

    public String getWeight_no() {
        return weight_no;
    }

    public void setWeight_no(String weight_no) {
        this.weight_no = weight_no;
    }

    public String getWeightment_date_time() {
        return weightment_date_time;
    }

    public void setWeightment_date_time(String weightment_date_time) {
        this.weightment_date_time = weightment_date_time;
    }

    public String getGroup_emp_id() {
        return group_emp_id;
    }

    public void setGroup_emp_id(String group_emp_id) {
        this.group_emp_id = group_emp_id;
    }

    public String getWorkers_nos() {
        return workers_nos;
    }

    public void setWorkers_nos(String workers_nos) {
        this.workers_nos = workers_nos;
    }

    public String getTables_no() {
        return tables_no;
    }

    public void setTables_no(String tables_no) {
        this.tables_no = tables_no;
    }

    public String getNo_of_nets() {
        return no_of_nets;
    }

    public void setNo_of_nets(String no_of_nets) {
        this.no_of_nets = no_of_nets;
    }

    public String getNet_tare_wt() {
        return net_tare_wt;
    }

    public void setNet_tare_wt(String net_tare_wt) {
        this.net_tare_wt = net_tare_wt;
    }

    public String getTotel_weight() {
        return totel_weight;
    }

    public void setTotel_weight(String totel_weight) {
        this.totel_weight = totel_weight;
    }

    public String getTotal_tare_weight() {
        return total_tare_weight;
    }

    public void setTotal_tare_weight(String total_tare_weight) {
        this.total_tare_weight = total_tare_weight;
    }

    public String getNet_weight() {
        return net_weight;
    }

    public void setNet_weight(String net_weight) {
        this.net_weight = net_weight;
    }
}
