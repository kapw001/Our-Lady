package com.pappayaed.ui.showprofile;

import com.google.gson.Gson;
import com.pappayaed.App.App;
import com.pappayaed.R;
import com.pappayaed.common.Utils;
import com.pappayaed.data.DataSource;
import com.pappayaed.data.listener.DataListener;
import com.pappayaed.data.model.Attendance;
import com.pappayaed.data.model.GeneralInformation;
import com.pappayaed.data.model.PersonalInformation;
import com.pappayaed.data.model.Qualification;
import com.pappayaed.data.model.Result;
import com.pappayaed.data.model.ResultResponse;
import com.pappayaed.data.model.Subject;
import com.pappayaed.data.parser.Parser;
import com.pappayaed.ui.parentprofile.PersonalInfo;
import com.pappayaed.ui.studentprofile.Attend;
import com.pappayaed.ui.studentprofile.Header;
import com.pappayaed.ui.studentprofile.Qualify;
import com.pappayaed.ui.studentprofile.StudentDetails;
import com.pappayaed.ui.studentprofile.Sub;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yasar on 26/3/18.
 */

public class ProfileIntractorImpl implements IProfileIntractor {


    private static final String CACHED_PROFILE = "profile";

    private DataSource dataSource;
    private Map<String, String> cached;

    public ProfileIntractorImpl(DataSource dataSource, Map<String, String> cached) {
        this.dataSource = dataSource;
        this.cached = cached;
    }


    @Override
    public void getProfile(OnProfileListener onProfileListener) {

        String profileImage = dataSource.getProfileImage();
        String profileName = dataSource.getProfileName();
        String userType = dataSource.getUserType();

        onProfileListener.displayProfile(profileName, userType, profileImage);
    }

    @Override
    public void getAllProfile(final OnFinishedListener onFinishedListener) {

//
//        String s = App.getApp().getProfile();
//
//        ResultResponse resultResponse = new Gson().fromJson(s, ResultResponse.class);
//
//        Result result = resultResponse.getResult();
//
//        parseData(result, onFinishedListener);


        Map<Object, Object> json = new HashMap<>();
        Map<Object, Object> params = new HashMap<>();
        params.put("login", dataSource.getEmailOrUsername());
        params.put("password", dataSource.getPassword());
        params.put("user_type", dataSource.getUserType());

        json.put("params", params);

        final String js = new JSONObject(json).toString();


        dataSource.getAllProfile(js, new DataListener() {
            @Override
            public void onSuccess(Object object) {

                ResultResponse resultResponse = (ResultResponse) object;

                Result result = resultResponse.getResult();

                parseData(result, onFinishedListener);


            }

            @Override
            public void onFail(Throwable throwable) {
                onFinishedListener.onFail(throwable);
            }

            @Override
            public void onNetworkFailure() {
                onFinishedListener.onNetworkFailure();
            }
        });


    }


    private void parseData(Result result, OnFinishedListener onFinishedListener) {

        Map<Object, Object> map = new LinkedHashMap<>();

        if (result != null) {

            PersonalInfo personalInfo = new PersonalInfo();

            personalInfo.setRfId(result.getRfidcardNo());

            List general = new ArrayList();

            general.add(new Header("Contact Information"));

            GeneralInformation gi = result.getGeneralInformation();

            StudentDetails name = new StudentDetails("Name", result.getTeacherFirstName() + " " + result.getTeacherLastName(), R.drawable.ic_account_circle_black_24dp, true);
            general.add(name);


            if (gi != null) {

                StudentDetails staff_id = new StudentDetails("Staff ID", gi.getStaffId(), R.drawable.ic_account_circle_black_24dp, true);
                general.add(staff_id);
                personalInfo.setStaffid(gi.getStaffId());
                personalInfo.setMobile(gi.getMobile());
                personalInfo.setDepartment(gi.getDepartment());

                general.add(new StudentDetails("Email", gi.getEmail().equalsIgnoreCase("false") ? "" : gi.getEmail(), R.drawable.ic_email_black_24dp));
                general.add(new StudentDetails("Mobile", gi.getMobile(), R.drawable.ic_phone_black_24dp));
                general.add(new StudentDetails("Phone", gi.getPhone(), R.drawable.ic_phone_black_24dp));

                StringBuffer address = new StringBuffer(gi.getStreet1());
                address.append("," + gi.getStreet2());
                address.append("," + gi.getCity());
                address.append("," + gi.getState());
                address.append("," + gi.getCountry());
                address.append("," + gi.getZip());

                general.add(new StudentDetails("Address", address.toString(), R.drawable.ic_account_circle_black_24dp));

                general.add(new Header("Position"));

                general.add(new StudentDetails("Category", gi.getCategory(), R.drawable.ic_account_circle_black_24dp));
                general.add(new StudentDetails("Department", gi.getDepartment(), R.drawable.ic_account_circle_black_24dp));
                general.add(new StudentDetails("Designation", gi.getDesignation(), R.drawable.ic_account_circle_black_24dp));
                general.add(new StudentDetails("Date of Joining", gi.getDateOfJoining(), R.drawable.ic_account_circle_black_24dp));
                general.add(new StudentDetails("Type of Employement ", gi.getEmployeeType(), R.drawable.ic_account_circle_black_24dp));
                general.add(new StudentDetails("Total Year of Service ", gi.getTotalYearOfService(), R.drawable.ic_account_circle_black_24dp));


            }


            map.put("General Information", general);

            PersonalInformation pi = result.getPersonalInformation();


            List personal = new ArrayList();

            if (pi != null) {

                personal.add(new Header("Citizenship"));

                personal.add(new StudentDetails("Country", pi.getCountryId(), R.drawable.ic_account_circle_black_24dp));
                personal.add(new StudentDetails("Aadhaar No ", pi.getAadhaarNo(), R.drawable.ic_account_circle_black_24dp));
                personal.add(new StudentDetails("Driving License", pi.getDrivingLicense(), R.drawable.ic_account_circle_black_24dp));

                personal.add(new Header("Salary Details"));

                personal.add(new StudentDetails("GROSS SALARY", pi.getGrossSalary(), R.drawable.ic_account_circle_black_24dp));
                personal.add(new StudentDetails("PF ", pi.getPf(), R.drawable.ic_account_circle_black_24dp));
                personal.add(new StudentDetails("TOTAL SALARY ", pi.getTotalSalary(), R.drawable.ic_account_circle_black_24dp));


                personal.add(new Header("Other Information"));

                personal.add(new StudentDetails("Gender", pi.getGender(), R.drawable.ic_account_circle_black_24dp));
                personal.add(new StudentDetails("Marital Status  ", pi.getMarital(), R.drawable.ic_account_circle_black_24dp));
                personal.add(new StudentDetails("Date of Birth  ", pi.getBirthday(), R.drawable.ic_account_circle_black_24dp));
                personal.add(new StudentDetails("Age ", pi.getAge(), R.drawable.ic_account_circle_black_24dp));
                personal.add(new StudentDetails("Religion ", pi.getReligion(), R.drawable.ic_account_circle_black_24dp));
                personal.add(new StudentDetails("Caste ", pi.getCaste(), R.drawable.ic_account_circle_black_24dp));
                personal.add(new StudentDetails("User Name ", pi.getUserId(), R.drawable.ic_account_circle_black_24dp));

            }

            map.put("Personal Information", personal);


            List<Qualification> qi = result.getQualification();

            List qualification = new ArrayList();

            if (qi.size() > 0) {

                qualification.add(new Header("Qualification"));

                for (int i = 0; i < qi.size(); i++) {

                    Qualification q = qi.get(i);

                    Qualify qualify = new Qualify();

                    qualify.setDegree(q.getDegree());
                    qualify.setInstitute_of_Study(q.getInstituteOfStudy());
                    qualify.setUniversity(q.getUniversity());
                    qualify.setYear_of_Completion(q.getYearOfCompletion());

                    qualification.add(qualify);

                }
            }


            map.put("Qualification", qualification);


            List<Subject> si = result.getSubjects();

            List subjects = new ArrayList();

            if (si.size() > 0) {

                subjects.add(new Header("Subjects "));

                for (int i = 0; i < si.size(); i++) {

                    Subject q = si.get(i);

                    Sub sub = new Sub();

                    sub.setAcademic_Year(q.getAcademicYear());
                    sub.setSubject(q.getSubject());
                    sub.setGrade(q.getGrade());
                    sub.setSection(q.getSection());
                    sub.setCategory(q.getEduStructure());

                    subjects.add(sub);


                }
            }


            map.put("Subjects", subjects);

            List<Attendance> ai = result.getAttendance();

            List attends = new ArrayList();

            if (ai.size() > 0) {

//                attends.add(new Header("Staff Attendance "));

//                attends.add(new StudentDetails("RFID Number  ", result.getRfidcardNo(), R.drawable.ic_account_circle_black_24dp));

                for (int i = 0; i < ai.size(); i++) {

                    Attendance q = ai.get(i);


                    Attend attend = new Attend();

                    attend.setAttendance_Date(q.getAttendanceDate());
                    attend.setAttendance_Status(q.getAttendanceStatus());
                    attend.setIn_time(q.getInTime());
                    attend.setOut_time(q.getOutTime());
                    attend.setWorked_Hours(q.getTimeDiff());


                    attends.add(attend);


                }
            }

            map.put("Attendance", attends);


            onFinishedListener.onSuccss(map, personalInfo);
        } else {
            onFinishedListener.onFail(new Throwable("There is no data"));
        }


    }
}
