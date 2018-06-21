package com.pappayaed.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.ViewLayout;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yasar on 19/6/18.
 */

public class HomeworkList implements Serializable, ViewLayout {

    @SerializedName("academic_year")
    @Expose
    private String academicYear;
    @SerializedName("secondary_tutor_name")
    @Expose
    private String secondaryTutorName;
    @SerializedName("homework_ids")
    @Expose
    private List<HomeworkId> homeworkIds = null;
    @SerializedName("primary_tutor_name")
    @Expose
    private String primaryTutorName;
    @SerializedName("section_id")
    @Expose
    private Long sectionId;
    @SerializedName("school_name")
    @Expose
    private String schoolName;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("grade_id")
    @Expose
    private Long gradeId;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("grade_name")
    @Expose
    private String gradeName;
    @SerializedName("homework_id")
    @Expose
    private Long homeworkId;
    @SerializedName("academic_year_id")
    @Expose
    private Long academicYearId;
    @SerializedName("secondary_tutor_id")
    @Expose
    private String secondaryTutorId;
    @SerializedName("primary_tutor_id")
    @Expose
    private Long primaryTutorId;
    private final static long serialVersionUID = -6977927253012843438L;

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getSecondaryTutorName() {
        return secondaryTutorName;
    }

    public void setSecondaryTutorName(String secondaryTutorName) {
        this.secondaryTutorName = secondaryTutorName;
    }

    public List<HomeworkId> getHomeworkIds() {
        return homeworkIds;
    }

    public void setHomeworkIds(List<HomeworkId> homeworkIds) {
        this.homeworkIds = homeworkIds;
    }

    public String getPrimaryTutorName() {
        return primaryTutorName;
    }

    public void setPrimaryTutorName(String primaryTutorName) {
        this.primaryTutorName = primaryTutorName;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Long getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    public String getSecondaryTutorId() {
        return secondaryTutorId;
    }

    public void setSecondaryTutorId(String secondaryTutorId) {
        this.secondaryTutorId = secondaryTutorId;
    }

    public Long getPrimaryTutorId() {
        return primaryTutorId;
    }

    public void setPrimaryTutorId(Long primaryTutorId) {
        this.primaryTutorId = primaryTutorId;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.headerrow_row;
    }
}