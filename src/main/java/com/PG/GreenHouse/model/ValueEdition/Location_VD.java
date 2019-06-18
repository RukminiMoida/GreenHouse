package com.PG.GreenHouse.model.ValueEdition;

import com.google.gson.annotations.SerializedName;

public class Location_VD {

    @SerializedName("Org_office_Name")
    private String Org_office_Name;

    @SerializedName("Location_Office_Address")
    private String Location_Office_Address;


    public Location_VD(String org_office_Name, String location_Office_Address) {
        Org_office_Name = org_office_Name;
        Location_Office_Address = location_Office_Address;
    }

    public String getOrg_office_Name() {
        return Org_office_Name;
    }

    public void setOrg_office_Name(String org_office_Name) {
        Org_office_Name = org_office_Name;
    }

    public String getLocation_Office_Address() {
        return Location_Office_Address;
    }

    public void setLocation_Office_Address(String location_Office_Address) {
        Location_Office_Address = location_Office_Address;
    }
}
