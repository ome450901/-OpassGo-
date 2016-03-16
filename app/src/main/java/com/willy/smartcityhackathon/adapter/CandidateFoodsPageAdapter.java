package com.willy.smartcityhackathon.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.willy.smartcityhackathon.fragment.FoodFragment;
import com.willy.smartcityhackathon.fragment.PersonalSetFragment;
import com.willy.smartcityhackathon.fragment.ShareSetFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pisua on 2015/9/18.
 */
public class CandidateFoodsPageAdapter extends FragmentStatePagerAdapter {

    public List<String> typeList = new ArrayList<>();

    public CandidateFoodsPageAdapter(FragmentManager fm) {
        super(fm);
        typeList.add("自己吃");
        typeList.add("找共食");
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return PersonalSetFragment.newInstance();
            case 1:
                return ShareSetFragment.newInstance();
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