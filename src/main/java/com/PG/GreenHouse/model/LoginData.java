package com.PG.GreenHouse.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData {

    @SerializedName("Date_Permission")
    @Expose
    private String Date_Permission;

    @SerializedName("Emp_id")
    @Expose
    private String Emp_id;

    @SerializedName("Dept_id")
    @Expose
    private String Dept_id;

    @SerializedName("User_Emp_Id")
    @Expose
    private String User_Emp_Id;

    @SerializedName("User_Mobile_No")
    @Expose
    private String User_Mobile_No;

    @SerializedName("User_Name")
    @Expose
    private String User_Name;

    @SerializedName("User_Mail_Id")
    @Expose
    private String User_Mail_Id;

    @SerializedName("User_Passward")
    @Expose
    private String User_Passward;

    @SerializedName("Message")
    @Expose
    private String Message;

    @SerializedName("COMP_CODE")
    @Expose
    private String COMP_CODE;


    public LoginData(String date_Permission, String emp_id, String dept_id, String user_Emp_Id, String user_Mobile_No, String user_Name, String user_Mail_Id, String user_Passward, String message, String COMP_CODE) {
        Date_Permission = date_Permission;
        Emp_id = emp_id;
        Dept_id = dept_id;
        User_Emp_Id = user_Emp_Id;
        User_Mobile_No = user_Mobile_No;
        User_Name = user_Name;
        User_Mail_Id = user_Mail_Id;
        User_Passward = user_Passward;
        Message = message;
        this.COMP_CODE = COMP_CODE;
    }

    public String getDate_Permission() {
        return Date_Permission;
    }

    public void setDate_Permission(String date_Permission) {
        Date_Permission = date_Permission;
    }

    public String getEmp_id() {
        return Emp_id;
    }

    public void setEmp_id(String emp_id) {
        Emp_id = emp_id;
    }

    public String getDept_id() {
        return Dept_id;
    }

    public void setDept_id(String dept_id) {
        Dept_id = dept_id;
    }

    public String getUser_Emp_Id() {
        return User_Emp_Id;
    }

    public void setUser_Emp_Id(String user_Emp_Id) {
        User_Emp_Id = user_Emp_Id;
    }

    public String getUser_Mobile_No() {
        return User_Mobile_No;
    }

    public void setUser_Mobile_No(String user_Mobile_No) {
        User_Mobile_No = user_Mobile_No;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getUser_Mail_Id() {
        return User_Mail_Id;
    }

    public void setUser_Mail_Id(String user_Mail_Id) {
        User_Mail_Id = user_Mail_Id;
    }

    public String getUser_Passward() {
        return User_Passward;
    }

    public void setUser_Passward(String user_Passward) {
        User_Passward = user_Passward;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getCOMP_CODE() {
        return COMP_CODE;
    }

    public void setCOMP_CODE(String COMP_CODE) {
        this.COMP_CODE = COMP_CODE;
    }
}
