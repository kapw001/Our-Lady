package com.pappayaed.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yasar on 14/6/18.
 */

public class PersonalInformation implements Parcelable
{

    @SerializedName("marital")
    @Expose
    private String marital;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("gross_salary")
    @Expose
    private String grossSalary;
    @SerializedName("total_salary")
    @Expose
    private String totalSalary;
    @SerializedName("driving_license")
    @Expose
    private String drivingLicense;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("religion")
    @Expose
    private String religion;
    @SerializedName("pf")
    @Expose
    private String pf;
    @SerializedName("aadhaar_no")
    @Expose
    private String aadhaarNo;
    @SerializedName("caste")
    @Expose
    private String caste;
    public final static Parcelable.Creator<PersonalInformation> CREATOR = new Creator<PersonalInformation>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PersonalInformation createFromParcel(Parcel in) {
            return new PersonalInformation(in);
        }

        public PersonalInformation[] newArray(int size) {
            return (new PersonalInformation[size]);
        }

    }
            ;

    protected PersonalInformation(Parcel in) {
        this.marital = ((String) in.readValue((String.class.getClassLoader())));
        this.birthday = ((String) in.readValue((String.class.getClassLoader())));
        this.grossSalary = ((String) in.readValue((String.class.getClassLoader())));
        this.totalSalary = ((String) in.readValue((String.class.getClassLoader())));
        this.drivingLicense = ((String) in.readValue((String.class.getClassLoader())));
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((String) in.readValue((String.class.getClassLoader())));
        this.age = ((String) in.readValue((String.class.getClassLoader())));
        this.countryId = ((String) in.readValue((String.class.getClassLoader())));
        this.religion = ((String) in.readValue((String.class.getClassLoader())));
        this.pf = ((String) in.readValue((String.class.getClassLoader())));
        this.aadhaarNo = ((String) in.readValue((String.class.getClassLoader())));
        this.caste = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PersonalInformation() {
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(String grossSalary) {
        this.grossSalary = grossSalary;
    }

    public String getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(String totalSalary) {
        this.totalSalary = totalSalary;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public String getAadhaarNo() {
        return aadhaarNo;
    }

    public void setAadhaarNo(String aadhaarNo) {
        this.aadhaarNo = aadhaarNo;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(marital);
        dest.writeValue(birthday);
        dest.writeValue(grossSalary);
        dest.writeValue(totalSalary);
        dest.writeValue(drivingLicense);
        dest.writeValue(userId);
        dest.writeValue(gender);
        dest.writeValue(age);
        dest.writeValue(countryId);
        dest.writeValue(religion);
        dest.writeValue(pf);
        dest.writeValue(aadhaarNo);
        dest.writeValue(caste);
    }

    public int describeContents() {
        return 0;
    }

}