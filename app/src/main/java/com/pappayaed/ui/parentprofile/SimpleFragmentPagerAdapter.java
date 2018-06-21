package com.pappayaed.ui.parentprofile;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yasar on 14/6/18.
 */

public class SimpleFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> title = new ArrayList<>();


    public void addFragmentAndTitle(List<Fragment> fragmentList, List<String> title) {

        this.fragmentList = fragmentList;
        this.title = title;

        notifyDataSetChanged();

    }

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
