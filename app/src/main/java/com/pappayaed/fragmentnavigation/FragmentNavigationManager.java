package com.pappayaed.fragmentnavigation;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.pappayaed.BuildConfig;
import com.pappayaed.ui.more.MoreFragment;
import com.pappayaed.ui.studentfee.StudentFeeFragment;
import com.pappayaed.ui.circular.CircularFragment;
import com.pappayaed.R;

/**
 * Created by yasar on 20/7/17.
 */

public class FragmentNavigationManager implements NavigationManager {

    private static FragmentNavigationManager sInstance;

    private FragmentManager mFragmentManager;
    private AppCompatActivity mActivity;
    private Fragment fragment;

    @Override
    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public static FragmentNavigationManager obtain(AppCompatActivity activity) {
        if (sInstance == null) {
            sInstance = new FragmentNavigationManager();
        }
        sInstance.configure(activity);
        return sInstance;
    }

    private void configure(AppCompatActivity activity) {
        mActivity = activity;
        mFragmentManager = mActivity.getSupportFragmentManager();
    }

    @Override
    public void AttendanceFragment(String title) {
    }

    @Override
    public void StudentFeeFragment(String title) {
        showFragment1(StudentFeeFragment.getInstance(), false, title);
    }

    @Override
    public void LeaveTapFragment(String title) {
    }

    @Override
    public void MoreFragment(String title) {
        showFragment(new MoreFragment(), false, title);
    }

    @Override
    public void AssignmentFragmentBackup(String title) {
    }

    @Override
    public void StudentSubmissionFragment(String title) {
    }

    @Override
    public void Circular(String title) {
        showFragment(new CircularFragment(), false, title);
    }

    @Override
    public void StudentTableFragment(String title) {

    }

    @Override
    public void StudentExamFragment(String title) {
    }

    @Override
    public void StudentExamParentFragment(String title) {
    }


    private void showFragment(Fragment fragment, boolean allowStateLoss, String tag) {
        FragmentManager fm = mFragmentManager;

        @SuppressLint("CommitTransaction")
        FragmentTransaction ft = fm.beginTransaction();
//        ft.addToBackStack(null);
        setFragment(fragment);
        mActivity.getSupportActionBar().setTitle(tag);
        Fragment faFragment1 = fm.findFragmentByTag(tag);
//        if (faFragment1 != null && faFragment1.isAdded()) {
//            ft.show(fragment);
//        } else {
        ft.replace(R.id.content, fragment, tag);
//        }

        if (allowStateLoss || !BuildConfig.DEBUG) {
            ft.commitAllowingStateLoss();
        } else {
            ft.commit();
        }

        fm.executePendingTransactions();
    }

    private void showFragment1(Fragment fragment, boolean allowStateLoss, String tag) {
        FragmentManager fm = mFragmentManager;

        @SuppressLint("CommitTransaction")
        FragmentTransaction ft = fm.beginTransaction();
//        ft.addToBackStack(null);
        setFragment(fragment);
        mActivity.getSupportActionBar().setTitle(tag);
        Fragment faFragment1 = fm.findFragmentByTag(tag);
        if (faFragment1 != null && faFragment1.isAdded()) {
            ft.show(fragment);
        } else {
            ft.replace(R.id.content, fragment, tag);
        }

        if (allowStateLoss || !BuildConfig.DEBUG) {
            ft.commitAllowingStateLoss();
        } else {
            ft.commit();
        }

        fm.executePendingTransactions();
    }
}

