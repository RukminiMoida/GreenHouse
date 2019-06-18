package com.PG.GreenHouse.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GettingProcesses {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private ArrayList<Processes_data> data;

    @SerializedName("getdata")
    private ArrayList<Process_Location> locations;

    public GettingProcesses(String status, String message, ArrayList<Processes_data> data, ArrayList<Process_Location> locations) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.locations = locations;
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

    public ArrayList<Processes_data> getData() {
        return data;
    }

    public void setData(ArrayList<Processes_data> data) {
        this.data = data;
    }

    public ArrayList<Process_Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Process_Location> locations) {
        this.locations = locations;
    }

    //    public GettingProcesses(String status, String message, ArrayList<Processes_data> data) {
//        this.status = status;
//        this.message = message;
//        this.data = data;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public ArrayList<Processes_data> getData() {
//        return data;
//    }
//
//    public void setData(ArrayList<Processes_data> data) {
//        this.data = data;
//    }
}
