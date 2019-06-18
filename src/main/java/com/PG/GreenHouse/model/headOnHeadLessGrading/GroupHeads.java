package com.PG.GreenHouse.model.headOnHeadLessGrading;

import com.google.gson.annotations.SerializedName;

public class GroupHeads {

    @SerializedName("Emp_id")
    private String Emp_id;

    @SerializedName("Emp_Name")
    private String Emp_Name;

    public GroupHeads(String emp_id, String emp_Name) {
        Emp_id = emp_id;
        Emp_Name = emp_Name;
    }

    public String getEmp_id() {
        return Emp_id;
    }

    public void setEmp_id(String emp_id) {
        Emp_id = emp_id;
    }

    public String getEmp_Name() {
        return Emp_Name;
    }

    public void setEmp_Name(String emp_Name) {
        Emp_Name = emp_Name;
    }
}
