package com.pappayaed.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yasar on 1/8/17.
 */

public class SubmissionList implements Serializable {

    @SerializedName("submission_date")
    @Expose
    private String submissionDate;
    @SerializedName("act_submission_date")
    @Expose
    private String actSubmissionDate;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("attachment_file_ids")
    @Expose
    private List<AttachmentFileId> attachmentFileIds = null;
    @SerializedName("student_id")
    @Expose
    private Integer studentId;
    @SerializedName("assignment_name")
    @Expose
    private String assignmentName;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("assignment_id")
    @Expose
    private Integer assignmentId;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("assignemnt_sub_line_id")
    @Expose
    private Integer assignemntSubLineId;
    @SerializedName("student_name")
    @Expose
    private String studentName;
    private final static long serialVersionUID = 6870076239416331086L;

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getActSubmissionDate() {
        return actSubmissionDate;
    }

    public void setActSubmissionDate(String actSubmissionDate) {
        this.actSubmissionDate = actSubmissionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AttachmentFileId> getAttachmentFileIds() {
        return attachmentFileIds;
    }

    public void setAttachmentFileIds(List<AttachmentFileId> attachmentFileIds) {
        this.attachmentFileIds = attachmentFileIds;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getAssignemntSubLineId() {
        return assignemntSubLineId;
    }

    public void setAssignemntSubLineId(Integer assignemntSubLineId) {
        this.assignemntSubLineId = assignemntSubLineId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}