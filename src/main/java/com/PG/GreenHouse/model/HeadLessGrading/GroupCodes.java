package com.PG.GreenHouse.model.HeadLessGrading;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GroupCodes implements Serializable {

    @SerializedName("FP_Group_Code")
    private String FP_Group_Code;

    @SerializedName("FP_Group_Name")
    private String FP_Group_Name;

    public GroupCodes(String FP_Group_Code, String FP_Group_Name) {
        this.FP_Group_Code = FP_Group_Code;
        this.FP_Group_Name = FP_Group_Name;
    }

    public String getFP_Group_Code() {
        return FP_Group_Code;
    }

    public void setFP_Group_Code(String FP_Group_Code) {
        this.FP_Group_Code = FP_Group_Code;
    }

    public String getFP_Group_Name() {
        return FP_Group_Name;
    }

    public void setFP_Group_Name(String FP_Group_Name) {
        this.FP_Group_Name = FP_Group_Name;
    }
}
