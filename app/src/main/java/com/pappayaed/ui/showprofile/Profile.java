package com.pappayaed.ui.showprofile;

/**
 * Created by yasar on 11/5/17.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Profile {

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("user_id")
    @Expose
    private Long userId;
    @SerializedName("zip")
    @Expose
    private String zip;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("street1")
    @Expose
    private String street1;
    @SerializedName("street2")
    @Expose
    private String street2;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("student_list")
    @Expose
    private List<StudentList> studentList = null;
    @SerializedName("id")
    @Expose
    private Long id;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<StudentList> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentList> studentList) {
        this.studentList = studentList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
