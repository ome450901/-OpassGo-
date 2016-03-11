package com.willy.smartcityhackathon.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Pisua on 2015/9/18.
 */
public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {


    public CustomFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return new Fragment();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

}