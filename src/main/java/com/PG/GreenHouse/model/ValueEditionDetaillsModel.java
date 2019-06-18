package com.PG.GreenHouse.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ValueEditionDetaillsModel implements Serializable {

    private String time;
    private int no_of_nets;
    private float total_weight;
    private float total_tare_weight;
    private float net_weight;
    private float cummulative_weight;
    private String group_person;
    private String team_no;
    private String count_code;
    private String groupCode;
    private String varietyName;
    private String varietyCode;
    private String groupName;
    private String gradeCode;
    private String gradeNo;

    public ValueEditionDetaillsModel() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNo_of_nets() {
        return no_of_nets;
    }

    public void setNo_of_nets(int no_of_nets) {
        this.no_of_nets = no_of_nets;
    }

    public float getTotal_weight() {
        return total_weight;
    }

    public void setTotal_weight(float total_weight) {
        this.total_weight = total_weight;
    }

    public float getTotal_tare_weight() {
        return total_tare_weight;
    }

    public void setTotal_tare_weight(float total_tare_weight) {
        this.total_tare_weight = total_tare_weight;
    }

    public float getNet_weight() {
        return net_weight;
    }

    public void setNet_weight(float net_weight) {
        this.net_weight = net_weight;
    }

    public float getCummulative_weight() {
        return cummulative_weight;
    }

    public void setCummulative_weight(float cummulative_weight) {
        this.cummulative_weight = cummulative_weight;
    }

    public String getGroup_person() {
        return group_person;
    }

    public void setGroup_person(String group_person) {
        this.group_person = group_person;
    }

    public String getTeam_no() {
        return team_no;
    }

    public void setTeam_no(String team_no) {
        this.team_no = team_no;
    }

    public String getCount_code() {
        return count_code;
    }

    public void setCount_code(String count_code) {
        this.count_code = count_code;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getVarietyName() {
        return varietyName;
    }

    public void setVarietyName(String varietyName) {
        this.varietyName = varietyName;
    }

    public String getVarietyCode() {
        return varietyCode;
    }

    public void setVarietyCode(String varietyCode) {
        this.varietyCode = varietyCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getGradeNo() {
        return gradeNo;
    }

    public void setGradeNo(String gradeNo) {
        this.gradeNo = gradeNo;
    }
}
