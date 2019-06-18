package com.PG.GreenHouse.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Process_Location implements Serializable {
    @SerializedName("Org_office_no")
    private String office_no;

    @SerializedName("Org_office_Name")
    private String office_name;

    @SerializedName("Issued_Org_Office_No")
    private String org_office_no;

    public Process_Location(String office_no, String office_name, String org_office_no) {
        this.office_no = office_no;
        this.office_name = office_name;
        this.org_office_no = org_office_no;
    }

    public String getOffice_no() {
        return office_no;
    }

    public void setOffice_no(String office_no) {
        this.office_no = office_no;
    }

    public String getOffice_name() {
        return office_name;
    }

    public void setOffice_name(String office_name) {
        this.office_name = office_name;
    }

    public String getOrg_office_no() {
        return org_office_no;
    }

    public void setOrg_office_no(String org_office_no) {
        this.org_office_no = org_office_no;
    }
}
