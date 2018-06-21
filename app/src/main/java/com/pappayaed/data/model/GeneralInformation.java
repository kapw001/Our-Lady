package com.pappayaed.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yasar on 14/6/18.
 */

public class GeneralInformation implements Parcelable
{

    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("staff_id")
    @Expose
    private String staffId;
    @SerializedName("designation")
    @Expose
    private String designation;
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
    @SerializedName("total_year_of_service")
    @Expose
    private String totalYearOfService;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("date_of_joining")
    @Expose
    private String dateOfJoining;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("employee_type")
    @Expose
    private String employeeType;
    @SerializedName("email")
    @Expose
    private String  email;
    public final static Parcelable.Creator<GeneralInformation> CREATOR = new Creator<GeneralInformation>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GeneralInformation createFromParcel(Parcel in) {
            return new GeneralInformation(in);
        }

        public GeneralInformation[] newArray(int size) {
            return (new GeneralInformation[size]);
        }

    }
            ;

    protected GeneralInformation(Parcel in) {
        this.category = ((String) in.readValue((String.class.getClassLoader())));
        this.city = ((String) in.readValue((String.class.getClassLoader())));
        this.staffId = ((String) in.readValue((String.class.getClassLoader())));
        this.designation = ((String) in.readValue((String.class.getClassLoader())));
        this.zip = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.street1 = ((String) in.readValue((String.class.getClassLoader())));
        this.street2 = ((String) in.readValue((String.class.getClassLoader())));
        this.totalYearOfService = ((String) in.readValue((String.class.getClassLoader())));
        this.phone = ((String) in.readValue((String.class.getClassLoader())));
        this.state = ((String) in.readValue((String.class.getClassLoader())));
        this.dateOfJoining = ((String) in.readValue((String.class.getClassLoader())));
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        this.department = ((String) in.readValue((String.class.getClassLoader())));
        this.employeeType = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String ) in.readValue((Boolean.class.getClassLoader())));
    }

    public GeneralInformation() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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

    public String getTotalYearOfService() {
        return totalYearOfService;
    }

    public void setTotalYearOfService(String totalYearOfService) {
        this.totalYearOfService = totalYearOfService;
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

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String  email) {
        this.email = email;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(category);
        dest.writeValue(city);
        dest.writeValue(staffId);
        dest.writeValue(designation);
        dest.writeValue(zip);
        dest.writeValue(mobile);
        dest.writeValue(street1);
        dest.writeValue(street2);
        dest.writeValue(totalYearOfService);
        dest.writeValue(phone);
        dest.writeValue(state);
        dest.writeValue(dateOfJoining);
        dest.writeValue(country);
        dest.writeValue(department);
        dest.writeValue(employeeType);
        dest.writeValue(email);
    }

    public int describeContents() {
        return 0;
    }

}