package com.willy.smartcityhackathon.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.willy.smartcityhackathon.fragment.FoodFragment;
import com.willy.smartcityhackathon.fragment.ShareFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pisua on 2015/9/18.
 */
public class MainPageAdapter extends FragmentStatePagerAdapter {

    public List<String> typeList = new ArrayList<>();

    public MainPageAdapter(FragmentManager fm) {
        super(fm);
        typeList.add("找吃的");
        typeList.add("我要開桌");
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return FoodFragment.newInstance();
            case 1:
                return ShareFragment.newInstance();
        }
        return FoodFragment.newInstance();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return typeList.get(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}