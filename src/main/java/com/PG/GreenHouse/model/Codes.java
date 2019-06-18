package com.PG.GreenHouse.model;

import com.google.gson.annotations.SerializedName;

public class Codes {

    @SerializedName("Variety_Code")
    private String variety_code;

    @SerializedName("Variety_Count_Code")
    private String variaty_count_code;

    @SerializedName("Variety_Count")
    private String variety_count;

    @SerializedName("fk_Material_Group")
    private String fk_material_group;

    public Codes(String variety_code, String variaty_count_code, String variety_count, String fk_material_group) {
        this.variety_code = variety_code;
        this.variaty_count_code = variaty_count_code;
        this.variety_count = variety_count;
        this.fk_material_group = fk_material_group;
    }

    public String getVariety_code() {
        return variety_code;
    }

    public void setVariety_code(String variety_code) {
        this.variety_code = variety_code;
    }

    public String getVariaty_count_code() {
        return variaty_count_code;
    }

    public void setVariaty_count_code(String variaty_count_code) {
        this.variaty_count_code = variaty_count_code;
    }

    public String getVariety_count() {
        return variety_count;
    }

    public void setVariety_count(String variety_count) {
        this.variety_count = variety_count;
    }

    public String getFk_material_group() {
        return fk_material_group;
    }

    public void setFk_material_group(String fk_material_group) {
        this.fk_material_group = fk_material_group;
    }
}
