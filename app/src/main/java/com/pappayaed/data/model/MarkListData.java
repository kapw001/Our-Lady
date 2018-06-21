package com.pappayaed.data.model;

import java.io.Serializable;

/**
 * Created by yasar on 21/8/17.
 */

public class MarkListData implements Serializable {

    private String status;
    private String total_marks;
    private String student_id;
    private String per;
    private String exam_id;
    private String marks;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotal_marks() {
        return total_marks;
    }

    public void setTotal_marks(String total_marks) {
        this.total_marks = total_marks;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getPer() {
        return per;
    }

    public void setPer(String per) {
        this.per = per;
    }

    public String getExam_id() {
        return exam_id;
    }

    public void setExam_id(String exam_id) {
        this.exam_id = exam_id;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    private String subject;
}
