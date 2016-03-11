package com.willy.smartcityhackathon.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Pisua on 2015/9/18.
 */
public class CustomFragmentStatePagerAdapter extends FragmentStatePagerAdapter {


    public CustomFragmentStatePagerAdapter(FragmentManager fm) {
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
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return 0;
    }
}