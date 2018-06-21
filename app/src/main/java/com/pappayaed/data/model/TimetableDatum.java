package com.pappayaed.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yasar on 28/7/17.
 */

public class TimetableDatum implements Serializable {

    @SerializedName("batch_id")
    @Expose
    private String batchId;
    @SerializedName("faculty_id")
    @Expose
    private String facultyId;
    @SerializedName("period_id")
    @Expose
    private String periodId;
    @SerializedName("end_datetime")
    @Expose
    private String endDatetime;
    @SerializedName("classroom_id")
    @Expose
    private String classroomId;
    @SerializedName("course_id")
    @Expose
    private String courseId;
    @SerializedName("student_name")
    @Expose
    private String studentName;
    @SerializedName("subject_id")
    @Expose
    private String subjectId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("start_datetime")
    @Expose
    private String startDatetime;
    private final static long serialVersionUID = 3494557878140103609L;

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
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

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }
}