package com.pappayaed.ui.studentprofile;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.DividerItemDecoration;
import com.pappayaed.RecyclerViewCommon.RecyclerViewAdapter;
import com.pappayaed.RecyclerViewCommon.RecyclerViewAdapterMultiView;
import com.pappayaed.base.BaseActivity;
import com.pappayaed.common.Utils;
import com.pappayaed.ui.showprofile.StudentList;
import com.pappayaed.ui.showprofile.UserDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class StudentProfileActivity extends BaseActivity implements IStudentView {

    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.profile_name)
    TextView profileName;
    @BindView(R.id.profile_id)
    TextView profileId;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.back)
    ImageView back;

    private IPresenter<IStudentView> iPresenter;

    private RecyclerViewAdapterMultiView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        ButterKnife.bind(this);


        init();


    }


    private void init() {

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setHasFixedSize(true);

        StudentList studentList = (StudentList) getIntent().getSerializableExtra("studentlist");

        adapter = new RecyclerViewAdapterMultiView(new ArrayList());

        recyclerview.setAdapter(adapter);

        iPresenter = new PresenterImpl<>(dataSource);

        iPresenter.onAttach(this);

        iPresenter.getStudentList(studentList);


    }


    @OnClick(R.id.back)
    public void onBack() {

        super.onBackPressed();

    }


    @Override
    public void displayNameAndID(String name, String id) {

        profileName.setText(name);

        profileId.setText(id);
    }

    @Override
    public void showStudentDetails(List studentList) {

        adapter.updateData(studentList);

        Utils.setBorderColor(profileImage);
    }
}
