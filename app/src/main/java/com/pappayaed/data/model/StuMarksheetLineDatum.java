package com.pappayaed.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yasar on 19/6/17.
 */

public class StuMarksheetLineDatum implements Serializable {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("total_marks")
    @Expose
    private Integer totalMarks;
    @SerializedName("total_per")
    @Expose
    private Integer totalPer;
    @SerializedName("university_id")
    @Expose
    private String universityId;
    @SerializedName("marksheet_reg_id")
    @Expose
    private String marksheetRegId;
    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("company_id")
    @Expose
    private String companyId;
    @SerializedName("department_id")
    @Expose
    private String departmentId;
    private final static long serialVersionUID = -2162579616707842598L;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(Integer totalMarks) {
        this.totalMarks = totalMarks;
    }

    public Integer getTotalPer() {
        return totalPer;
    }

    public void setTotalPer(Integer totalPer) {
        this.totalPer = totalPer;
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public String getMarksheetRegId() {
        return marksheetRegId;
    }

    public void setMarksheetRegId(String marksheetRegId) {
        this.marksheetRegId = marksheetRegId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}
