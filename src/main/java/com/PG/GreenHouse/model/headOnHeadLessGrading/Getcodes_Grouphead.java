package com.PG.GreenHouse.model.headOnHeadLessGrading;

import com.PG.GreenHouse.model.FactoryWeighment.ActualCodes;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Getcodes_Grouphead implements Serializable {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("Codes")
    private ArrayList<ActualCodes> Codes;

    @SerializedName("Group Head")
    private ArrayList<GroupHeads> Group_Head;


    public Getcodes_Grouphead(String status, String message, ArrayList<ActualCodes> codes, ArrayList<GroupHeads> group_Head) {
        this.status = status;
        this.message = message;
        Codes = codes;
        Group_Head = group_Head;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ActualCodes> getCodes() {
        return Codes;
    }

    public void setCodes(ArrayList<ActualCodes> codes) {
        Codes = codes;
    }

    public ArrayList<GroupHeads> getGroup_Head() {
        return Group_Head;
    }

    public void setGroup_Head(ArrayList<GroupHeads> group_Head) {
        Group_Head = group_Head;
    }
}
