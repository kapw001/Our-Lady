package com.pappayaed.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yasar on 20/6/17.
 */

public class StudentHolidaysDatum implements Serializable {

    @SerializedName("leave_type")
    @Expose
    private String leaveType;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("date_to")
    @Expose
    private String dateTo;
    @SerializedName("student_name")
    @Expose
    private String studentName;
    @SerializedName("date_from")
    @Expose
    private String dateFrom;
    @SerializedName("number_of_days")
    @Expose
    private Integer numberOfDays;
    @SerializedName("id")
    @Expose
    private Integer id;
    private final static long serialVersionUID = -3438108206484160137L;

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}