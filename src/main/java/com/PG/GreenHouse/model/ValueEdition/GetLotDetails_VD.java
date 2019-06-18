package com.PG.GreenHouse.model.ValueEdition;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class GetLotDetails_VD implements Serializable {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("Lotno")
    private ArrayList<LotNoDetails_VD> Lotno;

    @SerializedName("location")
    private String location;
}
