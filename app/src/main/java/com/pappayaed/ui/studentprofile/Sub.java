package com.pappayaed.ui.studentprofile;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.ViewLayout;

import java.io.Serializable;

/**
 * Created by yasar on 14/6/18.
 */

public class Sub implements Serializable, ViewLayout {


    private String Academic_Year;
    private String Category;
    private String grade;
    private String Section;
    private String Subject;

    public String getAcademic_Year() {
        return Academic_Year;
    }

    public void setAcademic_Year(String academic_Year) {
        Academic_Year = academic_Year;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.sub_row;
    }
}
