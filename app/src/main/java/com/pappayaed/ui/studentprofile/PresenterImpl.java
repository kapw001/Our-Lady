package com.pappayaed.ui.studentprofile;

import com.pappayaed.R;
import com.pappayaed.base.BasePresenter;
import com.pappayaed.base.BaseView;
import com.pappayaed.base.MvpPresenter;
import com.pappayaed.data.DataSource;
import com.pappayaed.ui.showprofile.StudentList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yasar on 21/5/18.
 */

public class PresenterImpl<V extends IStudentView> extends BasePresenter<V> implements IPresenter<V> {


    public PresenterImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void getStudentList(StudentList st) {

        String s_name = st.getName();

        String id = "Student ID " + st.getId();

        getMvpView().displayNameAndID(s_name, id);

        List list = new ArrayList();

        list.add(new Header("Profile"));

        StudentDetails name = new StudentDetails("Name", st.getName(), R.drawable.ic_account_circle_black_24dp, true);
        list.add(name);
        StudentDetails dob = new StudentDetails("DOB", st.getBirthDate(), R.drawable.ic_cake_black_24dp);
        list.add(dob);
        StudentDetails gender = new StudentDetails("Gender", st.getGender(), R.drawable.ic_gender);
        list.add(gender);

        StudentDetails bloodgroup = new StudentDetails("Bood Group", st.getBloodGroup(), R.drawable.ic_blood_type);
        list.add(bloodgroup);

//        StudentDetails email = new StudentDetails("Email", st.getEmail(), R.drawable.ic_email_black_24dp);
//        list.add(email);

        StringBuilder builder = new StringBuilder();
        builder.append(st.getStreet1());
        String s2 = st.getStreet2() != null && st.getStreet2().length() > 0 ? "," + st.getStreet2() : "";
        builder.append(s2);
        String city = st.getCity() != null && st.getCity().length() > 0 ? "," + st.getCity() : "";
        builder.append(city);
        String state = st.getState() != null && st.getState().length() > 0 ? "," + st.getState() : "";
        builder.append(state);
        String country = st.getCountry() != null && st.getCountry().length() > 0 ? "," + st.getCountry() : "";
        builder.append(country);
        String zipcode = st.getZip() != null && st.getZip().length() > 0 ? "," + st.getZip() : "";
        builder.append(zipcode);

        StudentDetails address = new StudentDetails("Address", builder.toString(), R.drawable.ic_home_black_24dp);
        list.add(address);

        StudentDetails contact = new StudentDetails("Emergency Contact", st.getMobile(), R.drawable.ic_phone_black_24dp);
        list.add(contact);


        list.add(new Header("Class Credendtials"));

        StudentDetails grade = new StudentDetails("Grade", st.getGrade(), R.drawable.ic_group_black_24dp, true);
        list.add(grade);

        StudentDetails section = new StudentDetails("Section", st.getSection(), R.drawable.ic_group_black_24dp);
        list.add(section);

        StudentDetails rollnumber = new StudentDetails("Roll Number", st.getCurrentRollNumber(), R.drawable.ic_person_black_24dp);
        list.add(rollnumber);

        getMvpView().showStudentDetails(list);

    }
}
