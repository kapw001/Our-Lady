package com.pappayaed.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.ViewLayout;

import java.io.Serializable;

/**
 * Created by yasar on 18/6/18.
 */

public class AttendanceLine implements Serializable, ViewLayout {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("student_full_name")
    @Expose
    private String studentFullName;
    @SerializedName("attendance_id")
    @Expose
    private Long attendanceId;
    @SerializedName("enrollment_num")
    @Expose
    private String enrollmentNum;
    @SerializedName("student_id")
    @Expose
    private Long studentId;
    @SerializedName("attendance_date")
    @Expose
    private String attendanceDate;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("school_id")
    @Expose
    private Long schoolId;

//    @Expose(serialize = false, deserialize = false)
    private boolean is_changed = false;

    public boolean is_changed() {
        return is_changed;
    }

    public void setIs_changed(boolean is_changed) {
        this.is_changed = is_changed;
    }

    private final static long serialVersionUID = -3731841101853323390L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        this.studentFullName = studentFullName;
    }

    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getEnrollmentNum() {
        return enrollmentNum;
    }

    public void setEnrollmentNum(String enrollmentNum) {
        this.enrollmentNum = enrollmentNum;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.presentabsent_row;
    }
}