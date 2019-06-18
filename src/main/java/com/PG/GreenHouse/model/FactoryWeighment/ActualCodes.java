package com.PG.GreenHouse.model.FactoryWeighment;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ActualCodes implements Serializable {

    @SerializedName("vcode")
    private String vcode;

    @SerializedName("vcount")
    private String vcount;

    public ActualCodes(String vcode, String vcount) {
        this.vcode = vcode;
        this.vcount = vcount;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public String getVcount() {
        return vcount;
    }

    public void setVcount(String vcount) {
        this.vcount = vcount;
    }
}
