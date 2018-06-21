package com.pappayaed.data.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssignmentList implements Serializable {

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

    @SerializedName("attachment_file_ids")
    @Expose
    private List<AttachmentFileId> attachmentFileIds = null;

    public List<AttachmentFileId> getAttachmentFileIds() {
        return attachmentFileIds;
    }

    public void setAttachmentFileIds(List<AttachmentFileId> attachmentFileIds) {
        this.attachmentFileIds = attachmentFileIds;
    }

    private final static long serialVersionUID = 8676273007336687052L;

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
        return "AssignmentList{" +
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
                '}';
    }
}