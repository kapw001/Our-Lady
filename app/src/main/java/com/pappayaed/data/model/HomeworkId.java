package com.pappayaed.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.ViewLayout;

import java.io.Serializable;

/**
 * Created by yasar on 19/6/18.
 */

public class HomeworkId implements Serializable, ViewLayout {

    public boolean isTitle() {
        return isTitle;
    }

    public void setTitle(boolean title) {
        isTitle = title;
    }

    private boolean isTitle = false;

    @SerializedName("my_homework")
    @Expose
    private Boolean myHomework;
    @SerializedName("sub_primary_tutor_name")
    @Expose
    private String subPrimaryTutorName;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("sub_primary_tutor_id")
    @Expose
    private Long subPrimaryTutorId;
    @SerializedName("subject_id")
    @Expose
    private Long subjectId;
    @SerializedName("homework_subjects_id")
    @Expose
    private Long homeworkSubjectsId;
    @SerializedName("subject_name")
    @Expose
    private String subjectName;
    private final static long serialVersionUID = -4119074034669462546L;

    public Boolean getMyHomework() {
        return myHomework;
    }

    public void setMyHomework(Boolean myHomework) {
        this.myHomework = myHomework;
    }

    public String getSubPrimaryTutorName() {
        return subPrimaryTutorName;
    }

    public void setSubPrimaryTutorName(String subPrimaryTutorName) {
        this.subPrimaryTutorName = subPrimaryTutorName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSubPrimaryTutorId() {
        return subPrimaryTutorId;
    }

    public void setSubPrimaryTutorId(Long subPrimaryTutorId) {
        this.subPrimaryTutorId = subPrimaryTutorId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getHomeworkSubjectsId() {
        return homeworkSubjectsId;
    }

    public void setHomeworkSubjectsId(Long homeworkSubjectsId) {
        this.homeworkSubjectsId = homeworkSubjectsId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.subrow_row;
    }
}