package com.PG.GreenHouse.model.LocationPlacement;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BarcodeResults {

    @SerializedName("PP_Number")
    private String PP_Number;

    @SerializedName("PP_Date")
    private String PP_Date;

    @SerializedName("FK_CS_Pellet_Location_No")
    private String FK_CS_Pellet_Location_No;

    @SerializedName("FK_CS_Pellet_Location_Visible_No")
    private String FK_CS_Pellet_Location_Visible_No;

    @SerializedName("PP_Total_No_of_Cartons")
    private String PP_Total_No_of_Cartons;

    @SerializedName("FK_Existing_Stock_Number")
    private String FK_Existing_Stock_Number;

    @SerializedName("Status")
    private String Status;

    public BarcodeResults(String PP_Number, String PP_Date, String FK_CS_Pellet_Location_No, String FK_CS_Pellet_Location_Visible_No, String PP_Total_No_of_Cartons, String FK_Existing_Stock_Number, String status) {
        this.PP_Number = PP_Number;
        this.PP_Date = PP_Date;
        this.FK_CS_Pellet_Location_No = FK_CS_Pellet_Location_No;
        this.FK_CS_Pellet_Location_Visible_No = FK_CS_Pellet_Location_Visible_No;
        this.PP_Total_No_of_Cartons = PP_Total_No_of_Cartons;
        this.FK_Existing_Stock_Number = FK_Existing_Stock_Number;
        Status = status;
    }

    public String getPP_Number() {
        return PP_Number;
    }

    public void setPP_Number(String PP_Number) {
        this.PP_Number = PP_Number;
    }

    public String getPP_Date() {
        return PP_Date;
    }

    public void setPP_Date(String PP_Date) {
        this.PP_Date = PP_Date;
    }

    public String getFK_CS_Pellet_Location_No() {
        return FK_CS_Pellet_Location_No;
    }

    public void setFK_CS_Pellet_Location_No(String FK_CS_Pellet_Location_No) {
        this.FK_CS_Pellet_Location_No = FK_CS_Pellet_Location_No;
    }

    public String getFK_CS_Pellet_Location_Visible_No() {
        return FK_CS_Pellet_Location_Visible_No;
    }

    public void setFK_CS_Pellet_Location_Visible_No(String FK_CS_Pellet_Location_Visible_No) {
        this.FK_CS_Pellet_Location_Visible_No = FK_CS_Pellet_Location_Visible_No;
    }

    public String getPP_Total_No_of_Cartons() {
        return PP_Total_No_of_Cartons;
    }

    public void setPP_Total_No_of_Cartons(String PP_Total_No_of_Cartons) {
        this.PP_Total_No_of_Cartons = PP_Total_No_of_Cartons;
    }

    public String getFK_Existing_Stock_Number() {
        return FK_Existing_Stock_Number;
    }

    public void setFK_Existing_Stock_Number(String FK_Existing_Stock_Number) {
        this.FK_Existing_Stock_Number = FK_Existing_Stock_Number;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
