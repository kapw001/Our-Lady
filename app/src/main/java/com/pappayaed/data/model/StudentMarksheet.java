package com.pappayaed.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yasar on 3/7/17.
 */

public class StudentMarksheet implements Serializable
{

    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("total_marks")
    @Expose
    private Integer totalMarks;
    @SerializedName("total_per")
    @Expose
    private Integer totalPer;
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("marksheet_reg_id")
    @Expose
    private String marksheetRegId;
    private final static long serialVersionUID = 2757024294535498959L;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(Integer totalMarks) {
        this.totalMarks = totalMarks;
    }

    public Integer getTotalPer() {
        return totalPer;
    }

    public void setTotalPer(Integer totalPer) {
        this.totalPer = totalPer;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMarksheetRegId() {
        return marksheetRegId;
    }

    public void setMarksheetRegId(String marksheetRegId) {
        this.marksheetRegId = marksheetRegId;
    }

}