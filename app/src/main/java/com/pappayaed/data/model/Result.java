package com.pappayaed.data.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pappayaed.ui.showprofile.StudentList;

public class Result implements Serializable {


    @SerializedName("homework_list")
    @Expose
    private List<HomeworkList> homeworkList = null;

    public List<HomeworkList> getHomeworkList() {
        return homeworkList;
    }

    public void setHomeworkList(List<HomeworkList> homeworkList) {
        this.homeworkList = homeworkList;
    }

    @SerializedName("attendance_sheet_list")
    @Expose
    private List<AttendanceSheetList> attendanceSheetList = null;

    public List<AttendanceSheetList> getAttendanceSheetList() {
        return attendanceSheetList;
    }

    public void setAttendanceSheetList(List<AttendanceSheetList> attendanceSheetList) {
        this.attendanceSheetList = attendanceSheetList;
    }

    @SerializedName("teacher_id")
    @Expose
    private Long teacherId;
    @SerializedName("teacher_middle_name")
    @Expose
    private String teacherMiddleName;

    @SerializedName("rfidcard_no")
    @Expose
    private String rfidcardNo;
    @SerializedName("attendance")
    @Expose
    private List<Attendance> attendance = null;
    @SerializedName("subjects")
    @Expose
    private List<Subject> subjects = null;
    @SerializedName("teacher_last_name")
    @Expose
    private String teacherLastName;
    @SerializedName("qualification")
    @Expose
    private List<Qualification> qualification = null;
    @SerializedName("general_information")
    @Expose
    private GeneralInformation generalInformation;
    @SerializedName("personal_information")
    @Expose
    private PersonalInformation personalInformation;
    @SerializedName("teacher_first_name")
    @Expose
    private String teacherFirstName;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherMiddleName() {
        return teacherMiddleName;
    }

    public void setTeacherMiddleName(String teacherMiddleName) {
        this.teacherMiddleName = teacherMiddleName;
    }

    public String getRfidcardNo() {
        return rfidcardNo;
    }

    public void setRfidcardNo(String rfidcardNo) {
        this.rfidcardNo = rfidcardNo;
    }

    public List<Attendance> getAttendance() {
        return attendance;
    }

    public void setAttendance(List<Attendance> attendance) {
        this.attendance = attendance;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    public List<Qualification> getQualification() {
        return qualification;
    }

    public void setQualification(List<Qualification> qualification) {
        this.qualification = qualification;
    }

    public GeneralInformation getGeneralInformation() {
        return generalInformation;
    }

    public void setGeneralInformation(GeneralInformation generalInformation) {
        this.generalInformation = generalInformation;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }

    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @SerializedName("miscellaneous_fees_collection_list")
    @Expose
    private List<MiscellaneousFeesCollectionList> miscellaneousFeesCollectionList = null;
    @SerializedName("academic_year")
    @Expose
    private String academicYear;

    @SerializedName("home_works")
    @Expose
    private List<HomeWork> homeWorks = null;

    @SerializedName("circulars")
    @Expose
    private List<Circular> circulars = null;

    @SerializedName("from_date")
    @Expose
    private String fromDate;

    @SerializedName("heartrate_list")
    @Expose
    private List<HeartrateList> heartrateList = null;

    public List<HeartrateList> getHeartrateList() {
        return heartrateList;
    }

    public void setHeartrateList(List<HeartrateList> heartrateList) {
        this.heartrateList = heartrateList;
    }

    public List<HomeWork> getHomeWorks() {
        return homeWorks;
    }

    public void setHomeWorks(List<HomeWork> homeWorks) {
        this.homeWorks = homeWorks;
    }

    public List<Circular> getCirculars() {
        return circulars;
    }

    public void setCirculars(List<Circular> circulars) {
        this.circulars = circulars;
    }

    @SerializedName("attendance_list")
    @Expose
    private List<AttendanceList> attendanceList = null;
    @SerializedName("to_date")
    @Expose
    private String toDate;

    public String getFromDate() {
        return fromDate;
    }

    public List<FeesPaymentHistoryList> getFeesPaymentHistoryList() {
        return feesPaymentHistoryList;
    }

    public void setFeesPaymentHistoryList(List<FeesPaymentHistoryList> feesPaymentHistoryList) {
        this.feesPaymentHistoryList = feesPaymentHistoryList;
    }

    @SerializedName("fees_payment_history_list")
    @Expose
    private List<FeesPaymentHistoryList> feesPaymentHistoryList = null;

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public List<AttendanceList> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<AttendanceList> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    @SerializedName("student_id")

    @Expose
    private Long studentId;

    @SerializedName("user_type")
    @Expose
    private String userType;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<TermFeesCollectionList> getTermFeesCollectionList() {
        return termFeesCollectionList;
    }

    public void setTermFeesCollectionList(List<TermFeesCollectionList> termFeesCollectionList) {
        this.termFeesCollectionList = termFeesCollectionList;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    @SerializedName("term_fees_collection_list")
    @Expose
    private List<TermFeesCollectionList> termFeesCollectionList = null;

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
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
    private Integer id;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @SerializedName("assignment_id")
    @Expose
    private Integer assignmentId;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("error_msg")
    @Expose
    private String error_msg;

    public List<SubmissionList> getSubmissionList() {
        return submissionList;
    }

    public void setSubmissionList(List<SubmissionList> submissionList) {
        this.submissionList = submissionList;
    }

    @SerializedName("submission_list")
    @Expose
    private List<SubmissionList> submissionList = null;

    @SerializedName("user_types")
    @Expose
    private String userTypes;
    @SerializedName("assignment_list")
    @Expose
    private List<AssignmentList> assignmentList = null;
    private final static long serialVersionUID = -2664528074184562113L;

    @SerializedName("student_holidays_status_data")
    @Expose
    private List<StudentHolidaysStatusDatum> studentHolidaysStatusData = null;

    @SerializedName("stu_marksheet_line_data")
    @Expose
    private List<StuMarksheetLineDatum> stuMarksheetLineData = null;

    @SerializedName("bystatus_assignment_list_data")
    @Expose
    private List<BystatusAssignmentListDatum> bystatusAssignmentListData = null;

    @SerializedName("attachment_detail")
    @Expose
    private AttachmentDetail attachmentDetail;

    public AttachmentDetail getAttachmentDetail() {
        return attachmentDetail;
    }

    public void setAttachmentDetail(AttachmentDetail attachmentDetail) {
        this.attachmentDetail = attachmentDetail;
    }

    @SerializedName("marksheet_data")
    @Expose
    private List<MarksheetDatum> marksheetData = null;

    public List<MarksheetDatum> getMarksheetData() {
        return marksheetData;
    }

    public void setMarksheetData(List<MarksheetDatum> marksheetData) {
        this.marksheetData = marksheetData;
    }

    @SerializedName("student_data")
    @Expose
    private List<StudentDatum> studentData = null;

    public List<StudentDatum> getStudentData() {
        return studentData;
    }

    public void setStudentData(List<StudentDatum> studentData) {
        this.studentData = studentData;
    }

    public List<BystatusAssignmentListDatum> getBystatusAssignmentListData() {
        return bystatusAssignmentListData;
    }

    public void setBystatusAssignmentListData(List<BystatusAssignmentListDatum> bystatusAssignmentListData) {
        this.bystatusAssignmentListData = bystatusAssignmentListData;
    }

    public List<StudentMarksheet> getStudentMarksheet() {
        return studentMarksheet;
    }

    public void setStudentMarksheet(List<StudentMarksheet> studentMarksheet) {
        this.studentMarksheet = studentMarksheet;
    }

    @SerializedName("student_marksheet")

    @Expose
    private List<StudentMarksheet> studentMarksheet = null;

    public List<StuMarksheetLineDatum> getStuMarksheetLineData() {
        return stuMarksheetLineData;
    }

    public List<StudentHolidaysStatusDatum> getStudentHolidaysStatusData() {
        return studentHolidaysStatusData;
    }

    public void setStudentHolidaysStatusData(List<StudentHolidaysStatusDatum> studentHolidaysStatusData) {
        this.studentHolidaysStatusData = studentHolidaysStatusData;
    }

    public void setStuMarksheetLineData(List<StuMarksheetLineDatum> stuMarksheetLineData) {
        this.stuMarksheetLineData = stuMarksheetLineData;
    }

    @SerializedName("assignment_form_data")
    @Expose
    private List<AssignmentFormDatum> assignmentFormData = null;

    public List<FacultyTimetableFormDatum> getFacultyTimetableFormData() {
        return facultyTimetableFormData;
    }

    public void setFacultyTimetableFormData(List<FacultyTimetableFormDatum> facultyTimetableFormData) {
        this.facultyTimetableFormData = facultyTimetableFormData;
    }

    @SerializedName("faculty_timetable_form_data")
    @Expose
    private List<FacultyTimetableFormDatum> facultyTimetableFormData = null;

    @SerializedName("student_holidays_data")
    @Expose
    private List<StudentHolidaysDatum> studentHolidaysData = null;


    @SerializedName("timetable_data")
    @Expose
    private List<TimetableDatum> timetableData = null;

    public List<TimetableDatum> getTimetableData() {
        return timetableData;
    }

    public void setTimetableData(List<TimetableDatum> timetableData) {
        this.timetableData = timetableData;
    }

    public List<StudentHolidaysDatum> getStudentHolidaysData() {
        return studentHolidaysData;
    }

    public void setStudentHolidaysData(List<StudentHolidaysDatum> studentHolidaysData) {
        this.studentHolidaysData = studentHolidaysData;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(String userTypes) {
        this.userTypes = userTypes;
    }

    public List<AssignmentList> getAssignmentList() {
        return assignmentList;
    }

    public void setAssignmentList(List<AssignmentList> assignmentList) {
        this.assignmentList = assignmentList;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public List<AssignmentFormDatum> getAssignmentFormData() {
        return assignmentFormData;
    }

    public void setAssignmentFormData(List<AssignmentFormDatum> assignmentFormData) {
        this.assignmentFormData = assignmentFormData;
    }

    @Override
    public String toString() {
        return "Result{" +
                "assignmentId=" + assignmentId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userTypes='" + userTypes + '\'' +
                ", assignmentList=" + assignmentList +
                ", assignmentFormData=" + assignmentFormData +
                '}';
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public List<MiscellaneousFeesCollectionList> getMiscellaneousFeesCollectionList() {
        return miscellaneousFeesCollectionList;
    }

    public void setMiscellaneousFeesCollectionList(List<MiscellaneousFeesCollectionList> miscellaneousFeesCollectionList) {
        this.miscellaneousFeesCollectionList = miscellaneousFeesCollectionList;
    }
}