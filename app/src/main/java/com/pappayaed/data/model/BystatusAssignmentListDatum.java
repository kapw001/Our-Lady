package com.pappayaed.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yasar on 10/7/17.
 */

public class BystatusAssignmentListDatum implements Serializable
{

    @SerializedName("submission_date")
    @Expose
    private String submissionDate;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("act_submission_date")
    @Expose
    private String actSubmissionDate;
    @SerializedName("student_id")
    @Expose
    private Integer studentId;
    @SerializedName("assignment_id")
    @Expose
    private Integer assignmentId;
    @SerializedName("assignemnt_sub_line_id")
    @Expose
    private Integer assignemntSubLineId;
    @SerializedName("assignment_name")
    @Expose
    private String assignmentName;
    @SerializedName("student_name")
    @Expose
    private String studentName;
    private final static long serialVersionUID = -5724741685864551429L;

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getActSubmissionDate() {
        return actSubmissionDate;
    }

    public void setActSubmissionDate(String actSubmissionDate) {
        this.actSubmissionDate = actSubmissionDate;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Integer getAssignemntSubLineId() {
        return assignemntSubLineId;
    }

    public void setAssignemntSubLineId(Integer assignemntSubLineId) {
        this.assignemntSubLineId = assignemntSubLineId;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

}