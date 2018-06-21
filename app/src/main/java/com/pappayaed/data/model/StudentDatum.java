package com.pappayaed.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yasar on 18/7/17.
 */

public class StudentDatum implements Serializable {

    @SerializedName("student_name")
    @Expose
    private String studentName;
    @SerializedName("student_id")
    @Expose
    private Integer studentId;
    private final static long serialVersionUID = -5302847807193381845L;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return studentName;
    }
}