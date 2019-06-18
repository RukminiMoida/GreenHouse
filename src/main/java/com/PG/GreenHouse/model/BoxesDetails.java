package com.PG.GreenHouse.model;

import com.google.gson.annotations.SerializedName;

public class BoxesDetails {
    @SerializedName("Procurement_Schedule_No")
    private String Procurement_Schedule_No;

    @SerializedName("Box_Required")
    private String Box_Required;

    @SerializedName("net")
    private String net;

    public BoxesDetails(String procurement_Schedule_No, String box_Required, String net) {
        Procurement_Schedule_No = procurement_Schedule_No;
        Box_Required = box_Required;
        this.net = net;
    }

    public String getProcurement_Schedule_No() {
        return Procurement_Schedule_No;
    }

    public void setProcurement_Schedule_No(String procurement_Schedule_No) {
        Procurement_Schedule_No = procurement_Schedule_No;
    }

    public String getBox_Required() {
        return Box_Required;
    }

    public void setBox_Required(String box_Required) {
        Box_Required = box_Required;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }
}
