package com.PG.GreenHouse.model.HeadLessGrading;

import com.google.gson.annotations.SerializedName;

public class GetGradeCodes {

    @SerializedName("FP_Production_Grade_No")
    private String FP_Production_Grade_No;

    @SerializedName("FP_Production_Grade_Code")
    private String FP_Production_Grade_Code;

    public GetGradeCodes(String FP_Production_Grade_No, String FP_Production_Grade_Code) {
        this.FP_Production_Grade_No = FP_Production_Grade_No;
        this.FP_Production_Grade_Code = FP_Production_Grade_Code;
    }

    public String getFP_Production_Grade_No() {
        return FP_Production_Grade_No;
    }

    public void setFP_Production_Grade_No(String FP_Production_Grade_No) {
        this.FP_Production_Grade_No = FP_Production_Grade_No;
    }

    public String getFP_Production_Grade_Code() {
        return FP_Production_Grade_Code;
    }

    public void setFP_Production_Grade_Code(String FP_Production_Grade_Code) {
        this.FP_Production_Grade_Code = FP_Production_Grade_Code;
    }
}
