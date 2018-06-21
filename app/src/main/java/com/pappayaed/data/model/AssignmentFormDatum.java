package com.pappayaed.data.model;

/**
 * Created by yasar on 5/6/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AssignmentFormDatum implements Serializable {

    @SerializedName("submission_date")
    @Expose
    private String submissionDate;
    @SerializedName("faculty_id")
    @Expose
    private String facultyId;
    @SerializedName("university_id")
    @Expose
    private String universityId;
    @SerializedName("course_id")
    @Expose
    private String courseId;
    @SerializedName("reviewer")
    @Expose
    private String reviewer;
    @SerializedName("assignment_type_id")
    @Expose
    private String assignmentTypeId;
    @SerializedName("issued_date")
    @Expose
    private String issuedDate;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("assignment_id")
    @Expose
    private Integer assignmentId;
    @SerializedName("subject_id")
    @Expose
    private String subjectId;
    @SerializedName("company_id")
    @Expose
    private String companyId;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("marks")
    @Expose
    private Integer marks;
    @SerializedName("batch_id")
    @Expose
    private String batchId;
    @SerializedName("department_id")
    @Expose
    private String departmentId;

    public List<AttachmentFileId> getAttachmentFileIds() {
        return attachmentFileIds;
    }

    public void setAttachmentFileIds(List<AttachmentFileId> attachmentFileIds) {
        this.attachmentFileIds = attachmentFileIds;
    }

    @SerializedName("attachment_file_ids")
    @Expose
    private List<AttachmentFileId> attachmentFileIds = null;



    @SerializedName("assignment_sub_line")
    @Expose
    private List<AssignmentSubLine> assignmentSubLine = null;

    public List<AssignmentSubLine> getAssignmentSubLine() {
        return assignmentSubLine;
    }

    public void setAssignmentSubLine(List<AssignmentSubLine> assignmentSubLine) {
        this.assignmentSubLine = assignmentSubLine;
    }
    public String getSubmissionDate() {
        return submissionDate;
    }


    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getAssignmentTypeId() {
        return assignmentTypeId;
    }

    public void setAssignmentTypeId(String assignmentTypeId) {
        this.assignmentTypeId = assignmentTypeId;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "AssignmentFormDatum{" +
                "submissionDate='" + submissionDate + '\'' +
                ", facultyId='" + facultyId + '\'' +
                ", universityId='" + universityId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", reviewer='" + reviewer + '\'' +
                ", assignmentTypeId='" + assignmentTypeId + '\'' +
                ", issuedDate='" + issuedDate + '\'' +
                ", name='" + name + '\'' +
                ", assignmentId=" + assignmentId +
                ", subjectId='" + subjectId + '\'' +
                ", companyId='" + companyId + '\'' +
                ", state='" + state + '\'' +
                ", marks=" + marks +
                ", batchId='" + batchId + '\'' +
                ", departmentId='" + departmentId + '\'' +
//                ", attachmentFileIds=" + attachmentFileIds +
                '}';
    }
}