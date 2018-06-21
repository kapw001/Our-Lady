package com.pappayaed.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yasar on 28/7/17.
 */

public class MarksheetDatum implements Serializable {

    @SerializedName("total_marks")
    @Expose
    private Integer totalMarks;
    @SerializedName("marksheet_reg_id")
    @Expose
    private String marksheetRegId;
    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("result_line")
    @Expose
    private List<Integer> resultLine = null;
    @SerializedName("total_per")
    @Expose
    private Integer totalPer;
    @SerializedName("result")
    @Expose
    private String result;
    private final static long serialVersionUID = 5980248807865488827L;

    public Integer getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(Integer totalMarks) {
        this.totalMarks = totalMarks;
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

    public List<Integer> getResultLine() {
        return resultLine;
    }

    public void setResultLine(List<Integer> resultLine) {
        this.resultLine = resultLine;
    }

    public Integer getTotalPer() {
        return totalPer;
    }

    public void setTotalPer(Integer totalPer) {
        this.totalPer = totalPer;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}