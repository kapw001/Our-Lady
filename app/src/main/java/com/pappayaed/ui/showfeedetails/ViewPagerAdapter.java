package com.pappayaed.ui.showfeedetails;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pappayaed.data.model.ResultResponse;
import com.pappayaed.ui.showfeedetails.paymenthistory.PaymentHistory;
import com.pappayaed.ui.showfeedetails.termfees.TermFeeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yasar on 27/3/18.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private ResultResponse myResponse;
    private boolean isError;

    public ViewPagerAdapter(FragmentManager manager, ResultResponse myResponse, boolean isError) {
        super(manager);
        this.myResponse = myResponse;
        this.isError = isError;
    }

    public void update(ResultResponse myResponse, boolean isError) {
        this.myResponse = myResponse;
        this.isError = isError;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public int getItemPosition(Object object) {

        if (object instanceof TermFeeFragment) {
            if (!isError) {
                ((TermFeeFragment) object).setList(myResponse);
            } else {
                ((TermFeeFragment) object).setError();
            }
        } else if (object instanceof PaymentHistory) {
            if (!isError) {
                ((PaymentHistory) object).setList(myResponse);
            } else {
                ((PaymentHistory) object).setError();
            }
        }

//            return POSITION_UNCHANGED;
        return super.getItemPosition(object);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}