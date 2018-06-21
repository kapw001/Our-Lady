package com.pappayaed.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yasar on 18/7/17.
 */

public class AssignmentSubLine implements Serializable {

    @SerializedName("submission_id")
    @Expose
    private Integer submissionId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("attachment_file_ids")
    @Expose
    private List<AttachmentFileId> attachmentFileIds = null;
    @SerializedName("student_id")
    @Expose
    private Integer studentId;
    @SerializedName("note")
    @Expose
    private Boolean note;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("student_name")
    @Expose
    private String studentName;


    private String submissiondate;

    private String grade;

    private String subject;

    public String getSubmissiondate() {
        return submissiondate;
    }

    public void setSubmissiondate(String submissiondate) {
        this.submissiondate = submissiondate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    private final static long serialVersionUID = 9166088051010110854L;

    public Integer getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Integer submissionId) {
        this.submissionId = submissionId;
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

    public Boolean getNote() {
        return note;
    }

    public void setNote(Boolean note) {
        this.note = note;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

}