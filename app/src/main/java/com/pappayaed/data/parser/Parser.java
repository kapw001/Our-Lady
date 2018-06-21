package com.pappayaed.data.parser;

import android.util.Log;

import com.github.lzyzsd.randomcolor.RandomColor;
import com.pappayaed.R;
import com.pappayaed.common.Utils;
import com.pappayaed.data.helper.L;
import com.pappayaed.data.listener.DataListener;
import com.pappayaed.data.model.AssignmentSubLine;
import com.pappayaed.data.model.AttachmentFileId;
import com.pappayaed.data.model.Circular;
import com.pappayaed.data.model.CircularAndHomeWork;
import com.pappayaed.data.pref.Pref;
import com.pappayaed.ui.circular.ICircularIntractor;
import com.pappayaed.ui.login.ILoginIntractor;
import com.pappayaed.ui.showprofile.IProfileIntractor;
import com.pappayaed.ui.showprofile.Profile;
import com.pappayaed.ui.showprofile.ProfileAndUserDetails;
import com.pappayaed.ui.showprofile.StudentList;
import com.pappayaed.ui.showprofile.UserDetails;
import com.pappayaed.ui.studentfee.IStudentIntractor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yasar on 6/3/18.
 */

public class Parser {

    private static final String TAG = "Parser";

    public static synchronized void loginParse(String response, DataListener dataListener, Pref preferences) {

        try {
            JSONObject jsonObject1 = new JSONObject(response);
            JSONObject result = jsonObject1.optJSONObject("result");
            Log.e(TAG, "onResponse: " + jsonObject1.toString());
            if (result.optString("status").equalsIgnoreCase("true")) {

                preferences.saveSession(result.getString("login"), result.getString("password"), result.getString("user_name"), result.getString("user_id"), result.getString("user_type"), Boolean.parseBoolean(result.getString("status")), result.getString("user_image"));

                if (preferences.isLoggedIn()) {


                    dataListener.onSuccess(result.getString("user_type"));

                } else {
                    dataListener.onFail(new Throwable("Something went wrong while parsing json data"));
                    L.loge(" Save preference errors ");
                }


            } else {

                dataListener.onFail(new Throwable("Invalid Email ID and Password"));

            }

        } catch (JSONException | NullPointerException e) {
//            dataListener.onFail(e);
            dataListener.onFail(new Throwable("Invalid Email ID and Password"));
        }

    }

    public static synchronized void loginParse(String response, ILoginIntractor.OnFinishedListener dataListener, Pref preferences) {

        try {
            JSONObject jsonObject1 = new JSONObject(response);
            JSONObject result = jsonObject1.optJSONObject("result");
            Log.e(TAG, "onResponse: " + jsonObject1.toString());
            if (result.optString("status").equalsIgnoreCase("true")) {

                preferences.saveSession(result.getString("login"), result.getString("password"), result.getString("user_name"), result.getString("user_id"), result.getString("user_type"), Boolean.parseBoolean(result.getString("status")), result.getString("user_image"));

                if (preferences.isLoggedIn()) {


                    dataListener.onSuccssLogin(result.getString("user_type"));

                } else {
                    dataListener.onFail(new Throwable("Something went wrong while parsing json data"));
                    L.loge(" Save preference errors ");
                }


            } else {

                dataListener.onFail(new Throwable("Invalid Email ID and Password"));

            }

        } catch (JSONException | NullPointerException e) {
//            dataListener.onFail(e);
            dataListener.onFail(new Throwable("Invalid Email ID and Password"));
        }

    }


    public static synchronized void circularParse(String response, ICircularIntractor.OnFinishedListener dataListener, Pref preferences) {

//        Log.e("  ", "onSuccess: " + response);

        try {
            JSONObject jsonObject1 = new JSONObject(response);
            JSONObject result = jsonObject1.optJSONObject("result");
            Log.e(TAG, "onResponse: " + jsonObject1.toString());


            JSONArray circular_list = result.optJSONArray("circulars");

            preferences.saveHomeWork(result.toString());

//            Log.e(TAG, "circularParse: " + circular_list.toString());

//            JSONObject circular = circular_list.getJSONObject(1);


//            Log.e(TAG, "circularParse: " + circular.toString());

//            JSONArray array = circular.getJSONArray("circular_ids");


//            Log.e(TAG, "circularParse: " + array.toString());


            List<Circular> list = new ArrayList<>();

            for (int i = 0; i < circular_list.length(); i++) {

                Circular circular1 = new Circular();

                JSONObject jsonObject = circular_list.getJSONObject(i);

                circular1.setDescription(jsonObject.optString("description"));

                list.add(circular1);
            }


//            JSONObject homework = circular_list.getJSONObject(0);

            JSONArray arrayHome = result.getJSONArray("home_works");


//            Log.e(TAG, "circularParse: " + array.toString());


            List<AssignmentSubLine> listHome = new ArrayList<>();

            for (int i = 0; i < arrayHome.length(); i++) {

                AssignmentSubLine homeWork1 = new AssignmentSubLine();

                JSONObject jsonObject = arrayHome.getJSONObject(i);

                JSONArray attachment = jsonObject.getJSONArray("attachment_file_ids");

                List<AttachmentFileId> attachmentFileIdList = new ArrayList<>();

                for (int j = 0; j < attachment.length(); j++) {

                    JSONObject jsonObject2 = attachment.getJSONObject(j);

                    AttachmentFileId attachmentFileId = new AttachmentFileId();

                    attachmentFileId.setName(jsonObject2.optString("name"));
                    attachmentFileId.setAttachId(jsonObject2.optInt("attach_id"));
                    attachmentFileId.setMimetype(jsonObject2.optString("mimetype"));

                    attachmentFileIdList.add(attachmentFileId);
                }


                homeWork1.setAttachmentFileIds(attachmentFileIdList);

                homeWork1.setSubject(jsonObject.optString("subject_name"));
                homeWork1.setDescription(jsonObject.optString("description"));

                String d = Utils.convertDateToString(jsonObject.optString("date"), "yyyy-MM-dd", "dd/MM/yyyy");

                homeWork1.setSubmissiondate(d);

                listHome.add(homeWork1);


            }

            CircularAndHomeWork circularAndHomeWork = new CircularAndHomeWork();

            circularAndHomeWork.setCircularList(list);
            circularAndHomeWork.setHomeCircularList(listHome);


//            dataListener.onSuccss(circularAndHomeWork);


        } catch (JSONException | NullPointerException e) {
            dataListener.onFail(e);
//            dataListener.onFail(new Throwable("There is no data"));
        }

    }


    public static synchronized void homerParse(String response, ICircularIntractor.OnFinishedListener dataListener, Pref preferences) {

//        Log.e("  ", "onSuccess: " + response);

        try {
            JSONObject jsonObject1 = new JSONObject(response);
            JSONObject result = jsonObject1.optJSONObject("result");
//            Log.e(TAG, "onResponse: " + jsonObject1.toString());


            JSONArray circular_list = result.optJSONArray("circular_list");

            preferences.saveHomeWork(circular_list.toString());

//            Log.e(TAG, "circularParse: " + circular_list.toString());

            JSONObject circular = circular_list.getJSONObject(0);


//            Log.e(TAG, "circularParse: " + circular.toString());

            JSONArray array = circular.getJSONArray("home_ids");


//            Log.e(TAG, "circularParse: " + array.toString());


            List<Circular> list = new ArrayList<>();

            for (int i = 0; i < array.length(); i++) {

                Circular circular1 = new Circular();

                JSONObject jsonObject = array.getJSONObject(i);

                circular1.setDescription(jsonObject.optString("description"));

                list.add(circular1);

                list.add(circular1);
                list.add(circular1);


            }

//            dataListener.onSuccss(list);


        } catch (JSONException | NullPointerException e) {
            dataListener.onFail(e);
//            dataListener.onFail(new Throwable("There is no data"));
        }

    }


    public static synchronized void profileParse(String response, DataListener dataListener, Pref preferences) {

        try {
            Log.e(TAG, "onResponse: student profile " + response);
            if (response != null) {

                JSONArray result = new JSONObject(response).getJSONObject("result").getJSONArray("student_list");

                List<StudentList> list = new ArrayList<StudentList>();

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jsonObject = result.getJSONObject(i);
                    list.add(parseStudentList(jsonObject));
                }

                dataListener.onSuccess(list);


            } else {

                dataListener.onFail(new Throwable("Somthing went wrong, try again later "));

            }

        } catch (JSONException | NullPointerException e) {

            dataListener.onFail(new Throwable("Somthing went wrong "));

        }

    }


    public static synchronized void profileParse(String response, IStudentIntractor.OnFinishedListener dataListener, Pref preferences) {

        try {
            Log.e(TAG, "onResponse: student profile " + response);
            if (response != null) {

                JSONArray result = new JSONObject(response).getJSONObject("result").getJSONArray("student_list");

                List<StudentList> list = new ArrayList<StudentList>();

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jsonObject = result.getJSONObject(i);

                    list.add(parseStudentList(jsonObject));
                }

                if (list.size() > 0) list.get(0).setSelected(true);

                dataListener.onSuccss(list);


            } else {

                dataListener.onFail(new Throwable("Somthing went wrong, try again later "));

            }

        } catch (JSONException | NullPointerException e) {

            dataListener.onFail(new Throwable("Somthing went wrong "));

        }

    }

    public static synchronized void allProfileParse(String response, DataListener dataListener, Pref preferences) {

        try {
            Log.e(TAG, "onResponse: student profile " + response);
            if (response != null) {


                JSONObject result = new JSONObject(response).getJSONObject("result");

                Profile profile1 = parseProfile(result);

                ProfileAndUserDetails profileAndUserDetails = updateList(profile1, preferences);

                dataListener.onSuccess(profileAndUserDetails);


            } else {

                dataListener.onFail(new Throwable("Somthing went wrong, try again later "));

            }

        } catch (JSONException | NullPointerException e) {

            dataListener.onFail(new Throwable("Somthing went wrong "));

        }

    }


    public static List<UserDetails> updateList(StudentList profile) {
        List<UserDetails> list = new ArrayList<>();

        list.add(new UserDetails("Personal Detail", null, null, UserDetails.HEADER));
        list.add(new UserDetails("Email", profile.getEmail(), null, UserDetails.ROW));
        list.add(new UserDetails("Mobile", profile.getMobile(), null, UserDetails.ROW));
//        list.add(new UserDetails("Phone", profile.getPhone(), null, UserDetails.ROW));
        list.add(new UserDetails("Gender", profile.getGender(), null, UserDetails.ROW));
        list.add(new UserDetails("Date of birth", profile.getBirthDate(), null, UserDetails.ROW));
        list.add(new UserDetails("Blood Group", profile.getBloodGroup(), null, UserDetails.ROW));
        list.add(new UserDetails("Nationality", profile.getNationality(), null, UserDetails.ROW));

        list.add(new UserDetails("Contact Detail", null, null, UserDetails.HEADER));
        list.add(new UserDetails("Street 1", profile.getStreet1(), null, UserDetails.ROW));
        list.add(new UserDetails("Street 2", profile.getStreet2(), null, UserDetails.ROW));
        list.add(new UserDetails("City", profile.getCity(), null, UserDetails.ROW));
        list.add(new UserDetails("State", profile.getState(), null, UserDetails.ROW));
        list.add(new UserDetails("Zip", profile.getZip(), null, UserDetails.ROW));
        list.add(new UserDetails("Country", profile.getCountry(), null, UserDetails.ROW));


        list.add(new UserDetails("Educational Detail", null, null, UserDetails.HEADER));
        list.add(new UserDetails("School", profile.getSchool(), null, UserDetails.ROW));
        list.add(new UserDetails("Academic year", profile.getAcademicYear(), null, UserDetails.ROW));
        list.add(new UserDetails("Grade", profile.getGrade(), null, UserDetails.ROW));
        list.add(new UserDetails("Date of joining", profile.getDateOfJoining(), null, UserDetails.ROW));
        list.add(new UserDetails("Current Roll Number", profile.getCurrentRollNumber(), null, UserDetails.ROW));

        return list;

    }


    public static synchronized void allProfileParse(String response, IProfileIntractor.OnFinishedListener dataListener, Pref preferences) {

        try {
            Log.e(TAG, "onResponse: student profile " + response);
            if (response != null) {


                JSONObject result = new JSONObject(response).getJSONObject("result");

                Profile profile1 = parseProfile(result);

                ProfileAndUserDetails profileAndUserDetails = updateList(profile1, preferences);

                dataListener.onSuccss(profileAndUserDetails);


            } else {

                dataListener.onFail(new Throwable("Somthing went wrong, try again later "));

            }

        } catch (JSONException | NullPointerException e) {

            dataListener.onFail(new Throwable("Somthing went wrong "));

        }

    }


    private static ProfileAndUserDetails updateList(Profile profile, Pref pref) {

        ProfileAndUserDetails profileAndUserDetails = new ProfileAndUserDetails();

        profileAndUserDetails.setProfile(profile);

        List<UserDetails> list = new ArrayList<>();

        if (!pref.getUserType().equalsIgnoreCase("Parent")) {
//            list.add(new UserDetails("Personal Detail", null, null, UserDetails.HEADER));
            list.add(new UserDetails("Mobile", profile.getMobile(), null, UserDetails.ROW, R.drawable.ic_phone_black_24dp));
            list.add(new UserDetails("Email", profile.getEmail(), null, UserDetails.ROW, R.drawable.ic_email_black_24dp));

//            list.add(new UserDetails("Mobile", profile.getMobile(), null, UserDetails.ROW,R.drawable.ic));
//            list.add(new UserDetails("Contact Detail", null, null, UserDetails.HEADER));
//            list.add(new UserDetails("Street 1", profile.getStreet1(), null, UserDetails.ROW));
//            list.add(new UserDetails("Street 2", profile.getStreet2(), null, UserDetails.ROW));
//            list.add(new UserDetails("City", profile.getCity(), null, UserDetails.ROW));
//            list.add(new UserDetails("State", profile.getState(), null, UserDetails.ROW));
//            list.add(new UserDetails("Zip", profile.getZip(), null, UserDetails.ROW));
//            list.add(new UserDetails("Country", profile.getCountry(), null, UserDetails.ROW));

//            if (!pref.getUserType().equalsIgnoreCase("Faculty")) {
//                list.add(new UserDetails("Educational Detail", null, null, UserDetails.HEADER));
//            }
        } else {

            list.add(new UserDetails("Name", pref.getProfileName(), null, UserDetails.ROW, R.drawable.ic_account_circle_black_24dp));
            list.add(new UserDetails("Mobile", profile.getMobile(), null, UserDetails.ROW, R.drawable.ic_phone_black_24dp));
            list.add(new UserDetails("Email", profile.getEmail(), null, UserDetails.ROW, R.drawable.ic_email_black_24dp));

//            list.add(new UserDetails("Personal Detail", null, null, UserDetails.HEADER));
//            list.add(new UserDetails("Email", profile.getEmail(), null, UserDetails.ROW));
//            list.add(new UserDetails("Mobile", profile.getMobile(), null, UserDetails.ROW));
////            list.add(new UserDetails("Phone", profile.getPhone(), null, UserDetails.ROW));
//
//            list.add(new UserDetails("Address Detail", null, null, UserDetails.HEADER));
//            list.add(new UserDetails("Street 1", profile.getStreet1(), null, UserDetails.ROW));
//            list.add(new UserDetails("Street 2", profile.getStreet2(), null, UserDetails.ROW));
//            list.add(new UserDetails("City", profile.getCity(), null, UserDetails.ROW));
//            list.add(new UserDetails("State", profile.getState(), null, UserDetails.ROW));
//            list.add(new UserDetails("Zip", profile.getZip(), null, UserDetails.ROW));
//            list.add(new UserDetails("Country", profile.getCountry(), null, UserDetails.ROW));
//
//            List<StudentList> studentList = profile.getStudentList();
//            list.add(new UserDetails("Children Detail", null, null, UserDetails.HEADER));
//            for (int i = 0; i < studentList.size(); i++) {
//                StudentList studentList1 = studentList.get(i);
//                list.add(new UserDetails("Child " + (i + 1), studentList1.getName(), studentList1.getPhoto(), studentList1, UserDetails.ROW));
//            }


        }

        Map<Object, Object> map = new HashMap<>();
        map.put("profile", list);
        map.put("child", profile.getStudentList());

        profileAndUserDetails.setMap(map);

        profileAndUserDetails.setList(list);

        return profileAndUserDetails;

    }


    private static Profile parseProfile(JSONObject jsonObject) throws JSONException {

        Profile profile = new Profile();

        profile.setCity(jsonObject.optString("city"));
        profile.setUserId(jsonObject.optLong("user_id"));
        profile.setZip(jsonObject.optString("zip"));
        String mobile = jsonObject.optString("mobile");

        if (mobile != null && !mobile.contains("+91")) {
            mobile = "+91" + mobile;
        }
        profile.setMobile(mobile);
        profile.setStreet1(jsonObject.optString("street1"));
        profile.setStreet2(jsonObject.optString("street2"));
        profile.setEmail(jsonObject.optString("email"));
        profile.setPhone(jsonObject.optString("phone"));
        profile.setState(jsonObject.optString("state"));
        profile.setCountry(jsonObject.optString("country"));
        profile.setId(jsonObject.optLong("id"));

        JSONArray result = jsonObject.getJSONArray("student_list");

        List<StudentList> list = new ArrayList<StudentList>();

        for (int i = 0; i < result.length(); i++) {
            JSONObject jsonObject1 = result.getJSONObject(i);
            list.add(parseStudentList(jsonObject1));
        }

        profile.setStudentList(list);


        return profile;

    }


    private static StudentList parseStudentList(JSONObject jsonObject) {

        RandomColor randomColor = new RandomColor();
        int color = randomColor.randomColor();

        StudentList studentList = new StudentList();
        studentList.setColor(color);
        studentList.setAcademicYear(jsonObject.optString("academic_year"));

        String bloodgroup = jsonObject.optString("blood_group");
        if (bloodgroup != null && bloodgroup.length() > 0 && !bloodgroup.contains("ve")) {
            bloodgroup = bloodgroup + "ve";
        }

        studentList.setBloodGroup(bloodgroup);
        studentList.setGrade(jsonObject.optString("grade"));
        studentList.setPhoto(jsonObject.optString("photo"));
        studentList.setGradeId(jsonObject.optLong("grade_id"));

        Log.e(TAG, "parseStudentList: " + jsonObject.optLong("student_id"));

        String d = Utils.convertDateToString(jsonObject.optString("dob"), "yyyy-MM-dd", "dd/MM/yyyy");
        studentList.setBirthDate(d);
        studentList.setId(jsonObject.optLong("student_id"));
        studentList.setCity(jsonObject.optString("city"));
        studentList.setZip(jsonObject.optString("zip"));
        studentList.setSection(jsonObject.optString("section"));
        studentList.setState(jsonObject.optString("state"));
        studentList.setAcademicYearId(jsonObject.optLong("academic_year_id"));
        studentList.setEmail(jsonObject.optString("email"));
        studentList.setStreet1(jsonObject.optString("street1"));
        studentList.setStreet2(jsonObject.optString("street2"));
        studentList.setSectionId(jsonObject.optLong("section_id"));
        studentList.setPhone(jsonObject.optString("phone"));
        studentList.setDateOfJoining(jsonObject.optString("date_of_joining"));
        studentList.setNationality(jsonObject.optString("nationality"));
        studentList.setCurrentRollNumber(jsonObject.optString("current_roll_number"));
        studentList.setSchool(jsonObject.optString("school"));
        studentList.setName(jsonObject.optString("name"));

        String mobile = jsonObject.optString("mobile");

        if (mobile != null && !mobile.contains("+91")) {
            mobile = "+91" + mobile;
        }

        studentList.setMobile(mobile);
        studentList.setGender(Utils.capitalizeFirstLetter(jsonObject.optString("gender")));
        studentList.setSchoolId(jsonObject.optLong("school_id"));
        studentList.setCountry(jsonObject.optString("country"));

        return studentList;
    }

}
