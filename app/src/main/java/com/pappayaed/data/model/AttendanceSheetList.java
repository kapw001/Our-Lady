package com.pappayaed.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yasar on 18/6/18.
 */

public class AttendanceSheetList implements Serializable
{

    @SerializedName("register_id")
    @Expose
    private Long registerId;
    @SerializedName("section_id")
    @Expose
    private Long sectionId;
    @SerializedName("register_name")
    @Expose
    private String registerName;
    @SerializedName("school_name")
    @Expose
    private String schoolName;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("total_absent")
    @Expose
    private Long totalAbsent;
    @SerializedName("attendance_sheet_id")
    @Expose
    private Long attendanceSheetId;
    @SerializedName("grade_id")
    @Expose
    private Long gradeId;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("grade_name")
    @Expose
    private String gradeName;
    @SerializedName("attendance_date")
    @Expose
    private String attendanceDate;
    @SerializedName("progress")
    @Expose
    private Double progress;
    @SerializedName("attendance_line")
    @Expose
    private List<AttendanceLine> attendanceLine = null;
    @SerializedName("total_present")
    @Expose
    private Long totalPresent;
    private final static long serialVersionUID = 6715955726224917360L;

    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Long getTotalAbsent() {
        return totalAbsent;
    }

    public void setTotalAbsent(Long totalAbsent) {
        this.totalAbsent = totalAbsent;
    }

    public Long getAttendanceSheetId() {
        return attendanceSheetId;
    }

    public void setAttendanceSheetId(Long attendanceSheetId) {
        this.attendanceSheetId = attendanceSheetId;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public List<AttendanceLine> getAttendanceLine() {
        return attendanceLine;
    }

    public void setAttendanceLine(List<AttendanceLine> attendanceLine) {
        this.attendanceLine = attendanceLine;
    }

    public Long getTotalPresent() {
        return totalPresent;
    }

    public void setTotalPresent(Long totalPresent) {
        this.totalPresent = totalPresent;
    }

}