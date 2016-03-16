package com.willy.smartcityhackathon.object.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Willy on 2016/3/12.
 */
public class CandidateFoods {

    @SerializedName("staple")
    private Food[] mStaples;
    @SerializedName("snack")
    private Food[] mSnacks;
    @SerializedName("drink")
    private Food[] mDrinks;

    public Food[] getStaples() {
        return mStaples;
    }

    public void setStaples(Food[] staples) {
        mStaples = staples;
    }

    public Food[] getSnacks() {
        return mSnacks;
    }

    public void setSnacks(Food[] snacks) {
        mSnacks = snacks;
    }

    public Food[] getDrinks() {
        return mDrinks;
    }

    public void setDrinks(Food[] drinks) {
        mDrinks = drinks;
    }
}
