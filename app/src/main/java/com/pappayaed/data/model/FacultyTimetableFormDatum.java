package com.pappayaed.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yasar on 14/6/17.
 */

public class FacultyTimetableFormDatum implements Serializable {

    @SerializedName("start_datetime")
    @Expose
    private String startDatetime;
    @SerializedName("faculty_id")
    @Expose
    private String facultyId;
    @SerializedName("period_id")
    @Expose
    private String periodId;
    @SerializedName("university_id")
    @Expose
    private String universityId;
    @SerializedName("course_id")
    @Expose
    private String courseId;
    @SerializedName("classroom_id")
    @Expose
    private String classroomId;
    @SerializedName("subject_id")
    @Expose
    private String subjectId;
    @SerializedName("company_id")
    @Expose
    private String companyId;
    @SerializedName("end_datetime")
    @Expose
    private String endDatetime;
    @SerializedName("batch_id")
    @Expose
    private String batchId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("department_id")
    @Expose
    private String departmentId;
    private final static long serialVersionUID = 7712770539386877721L;

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getPeriodId() {
        return periodId;
    }

    public void setPeriodId(String periodId) {
        this.periodId = periodId;
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

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
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

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

}