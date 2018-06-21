package com.pappayaed.ui.parentprofile;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.pappayaed.App.App;
import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewAdapter;
import com.pappayaed.RecyclerViewCommon.RecyclerViewAdapterMultiView;
import com.pappayaed.base.BaseActivity;
import com.pappayaed.common.ActivityUtils;
import com.pappayaed.common.CameraAndGallary;
import com.pappayaed.common.Utils;
import com.pappayaed.ui.showprofile.IProfileIntractor;
import com.pappayaed.ui.showprofile.IProfilePresenter;
import com.pappayaed.ui.showprofile.IProfileView;
import com.pappayaed.ui.showprofile.ProfileIntractorImpl;
import com.pappayaed.ui.showprofile.ProfilePresenterImpl;
import com.pappayaed.ui.showprofile.StudentList;
import com.pappayaed.ui.studentprofile.StudentProfileActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class ParentActivity extends BaseActivity implements IProfileView, RecyclerViewAdapter.OnRecyclerViewItemClickListener<StudentList>, CameraAndGallary.CameraAndGallaryCallBack {

    private static final String TAG = "ParentActivity";

    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.profile_name)
    TextView profileName;

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.edit)
    ImageView edit;
    @BindView(R.id.sliding_tabs)
    TabLayout slidingTabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.rf_id)
    TextView rfId;
    @BindView(R.id.staff_id)
    TextView staffId;
    @BindView(R.id.mobile)
    TextView mobile;
    @BindView(R.id.dept)
    TextView dept;

    private RecyclerViewAdapterMultiView adapter;

    private IProfilePresenter iProfilePresenter;

    private static final String[] CAMERA_AND_READWRITE =
            {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private static final int RC_CAMERA_AND_READWRITE_PERM = 124;

    private CameraAndGallary cameraAndGallary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);

//
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
//            window.navigationBarColor = ContextCompat.getColor(this, android.R.color.transparent);
            window.setBackgroundDrawableResource(R.drawable.ab_gradient);
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

//        setActionBarTitle("Profile", true);

        init();

        cameraAndGallary = new CameraAndGallary(this, this);

        Map<String, String> profileCached = App.getApp().getProfileCached();

        IProfileIntractor iProfileIntractor = new ProfileIntractorImpl(dataSource, profileCached);

        iProfilePresenter = new ProfilePresenterImpl(this, iProfileIntractor);

        callServices();


    }


    @OnClick(R.id.edit)
    public void onImageEdit(View view) {

        askCameraAndWriteData();

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//
//                super.onBackPressed();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    @OnClick(R.id.back)
    public void onBack() {

        super.onBackPressed();

    }

    private void callServices() {

        if (iProfilePresenter != null) {
            showLoading();

            iProfilePresenter.displayProfile();
            iProfilePresenter.getAllProfile();
        } else Log.e(TAG, "Presenter not initialized........................................ ");
    }

    private void init() {


    }

    @Override
    public void displayProfile(String name, String userType, String image) {

        profileName.setText(name);
        Bitmap imagebmp = Utils.decodeBitmap(this, image);

        profileImage.setImageBitmap(imagebmp);
        Utils.setBorderColor(profileImage);

    }

    @Override
    public void gotoStudentProfileActivity() {

    }

    @Override
    public void setData(List list) {

        hideLoading();


    }


    @Override
    public void setData(Map<Object, List> map, PersonalInfo personalInfo) {
        hideLoading();

        Log.e(TAG, "setData: size " + map.size());

        rfId.setText("RFID Number : " + personalInfo.getRfId());
        staffId.setText("Staff ID : " + personalInfo.getStaffid());
        mobile.setText("Mobile : " + personalInfo.getMobile());
        dept.setText("Department : " + personalInfo.getDepartment());


        SimpleFragmentPagerAdapter pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());

        List<Fragment> fragmentList = new ArrayList<>();
        List<String> title = new ArrayList<>();


        for (Map.Entry<Object, List> entry : map.entrySet()) {

            Log.e(TAG, "setData: " + entry.getKey());


            if (entry.getKey().toString().toLowerCase().equalsIgnoreCase("attendance")){

                AttendanceCalendarFragment profileViewFragment = AttendanceCalendarFragment.getProfileViewFragment(entry.getValue());

                fragmentList.add(profileViewFragment);
                title.add(entry.getKey().toString());

            }else {
                ProfileViewFragment profileViewFragment = ProfileViewFragment.getProfileViewFragment(entry.getValue());

                fragmentList.add(profileViewFragment);
                title.add(entry.getKey().toString());
            }



        }


        pagerAdapter.addFragmentAndTitle(fragmentList, title);

        viewpager.setAdapter(pagerAdapter);

        slidingTabs.setupWithViewPager(viewpager);


    }

    @Override
    public void showLoading() {

        Utils.showProgress(this, "Loading");

    }

    @Override
    public void hideLoading() {

        Utils.hideProgress();
    }

    @Override
    public void setError(String msg) {
        hideLoading();

    }

    @Override
    public void setEmptyData() {
        hideLoading();

    }

    @Override
    public void onItemClick(View view, StudentList studentList, int position) {


        Bundle bundle = new Bundle();
        bundle.putSerializable("studentlist", studentList);

        ActivityUtils.startActivity(this, StudentProfileActivity.class, bundle);

    }

    @AfterPermissionGranted(RC_CAMERA_AND_READWRITE_PERM)
    public void askCameraAndWriteData() {

        if (EasyPermissions.hasPermissions(this, CAMERA_AND_READWRITE)) {

            cameraAndGallary.selectImage();
            // Have permissions, do the thing!
//            Toast.makeText(this, "TODO: Camera and Read and Write things", Toast.LENGTH_LONG).show();

        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(
                    this,
                    "Need this permission to update the picture",
                    RC_CAMERA_AND_READWRITE_PERM,
                    CAMERA_AND_READWRITE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        cameraAndGallary.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    public void onSelectFromGalleryResult(Bitmap bitmap) {

        profileImage.setImageBitmap(bitmap);

        Utils.saveImage(this, bitmap);


    }
}
