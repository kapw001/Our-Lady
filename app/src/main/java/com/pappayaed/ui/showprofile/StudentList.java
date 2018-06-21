package com.pappayaed.ui.showprofile;

/**
 * Created by yasar on 23/5/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StudentList implements Parcelable, Serializable {

    @SerializedName("academic_year")
    @Expose
    private String academicYear;
    @SerializedName("blood_group")
    @Expose
    private String bloodGroup;
    @SerializedName("grade")
    @Expose
    private String grade;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("grade_id")
    @Expose
    private Long gradeId;
    //    @SerializedName("id")
    @SerializedName("student_id")
    @Expose
    private Long id;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("zip")
    @Expose
    private String zip;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("academic_year_id")
    @Expose
    private Long academicYearId;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("street1")
    @Expose
    private String street1;
    @SerializedName("street2")
    @Expose
    private String street2;
    @SerializedName("section_id")
    @Expose
    private Long sectionId;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("date_of_joining")
    @Expose
    private String dateOfJoining;
    @SerializedName("nationality")
    @Expose
    private String nationality;
    @SerializedName("current_roll_number")
    @Expose
    private String currentRollNumber;
    @SerializedName("school")
    @Expose
    private String school;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("school_id")
    @Expose
    private Long schoolId;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("birth_date")
    @Expose
    private String birthDate;

    private boolean isSelected = false;

    private int color;

    public final static Parcelable.Creator<StudentList> CREATOR = new Creator<StudentList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public StudentList createFromParcel(Parcel in) {
            return new StudentList(in);
        }

        public StudentList[] newArray(int size) {
            return (new StudentList[size]);
        }

    };
    private final static long serialVersionUID = -6523479954414367803L;

    protected StudentList(Parcel in) {
        this.academicYear = ((String) in.readValue((String.class.getClassLoader())));
        this.bloodGroup = ((String) in.readValue((String.class.getClassLoader())));
        this.grade = ((String) in.readValue((String.class.getClassLoader())));
        this.photo = ((String) in.readValue((String.class.getClassLoader())));
        this.gradeId = ((Long) in.readValue((Long.class.getClassLoader())));
        this.id = ((Long) in.readValue((Long.class.getClassLoader())));
        this.city = ((String) in.readValue((String.class.getClassLoader())));
        this.zip = ((String) in.readValue((String.class.getClassLoader())));
        this.section = ((String) in.readValue((String.class.getClassLoader())));
        this.state = ((String) in.readValue((String.class.getClassLoader())));
        this.academicYearId = ((Long) in.readValue((Long.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.street1 = ((String) in.readValue((String.class.getClassLoader())));
        this.street2 = ((String) in.readValue((String.class.getClassLoader())));
        this.sectionId = ((Long) in.readValue((Long.class.getClassLoader())));
        this.phone = ((String) in.readValue((String.class.getClassLoader())));
        this.dateOfJoining = ((String) in.readValue((String.class.getClassLoader())));
        this.nationality = ((String) in.readValue((String.class.getClassLoader())));
        this.currentRollNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.school = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((String) in.readValue((String.class.getClassLoader())));
        this.schoolId = ((Long) in.readValue((Long.class.getClassLoader())));
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        this.birthDate = ((String) in.readValue((String.class.getClassLoader())));
        this.isSelected = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.color = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public StudentList() {
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCurrentRollNumber() {
        return currentRollNumber;
    }

    public void setCurrentRollNumber(String currentRollNumber) {
        this.currentRollNumber = currentRollNumber;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(academicYear);
        dest.writeValue(bloodGroup);
        dest.writeValue(grade);
        dest.writeValue(photo);
        dest.writeValue(gradeId);
        dest.writeValue(id);
        dest.writeValue(city);
        dest.writeValue(zip);
        dest.writeValue(section);
        dest.writeValue(state);
        dest.writeValue(academicYearId);
        dest.writeValue(email);
        dest.writeValue(street1);
        dest.writeValue(street2);
        dest.writeValue(sectionId);
        dest.writeValue(phone);
        dest.writeValue(dateOfJoining);
        dest.writeValue(nationality);
        dest.writeValue(currentRollNumber);
        dest.writeValue(school);
        dest.writeValue(name);
        dest.writeValue(mobile);
        dest.writeValue(gender);
        dest.writeValue(schoolId);
        dest.writeValue(country);
        dest.writeValue(birthDate);
        dest.writeValue(isSelected);
        dest.writeValue(color);
    }

    public int describeContents() {
        return 0;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
