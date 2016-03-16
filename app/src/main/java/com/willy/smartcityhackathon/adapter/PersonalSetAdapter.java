package com.willy.smartcityhackathon.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.willy.smartcityhackathon.R;
import com.willy.smartcityhackathon.Utils.Utils;
import com.willy.smartcityhackathon.object.api.CandidateFoods;
import com.willy.smartcityhackathon.object.api.Food;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Willy on 2016/3/11.
 */
public class PersonalSetAdapter extends RecyclerView.Adapter<PersonalSetAdapter.MyViewHolder> {
    //要以動畫方式進來的Item數量
    private static final int ANIMATED_ITEMS_COUNT = 4;

    private Context context;
    private int lastAnimatedPosition = -1;

    private List<Food> mStapleList = new ArrayList<>();
    private List<Food> mSnackList = new ArrayList<>();
    private List<Food> mDrinkList = new ArrayList<>();


    public PersonalSetAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_personal_set, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        runEnterAnimation(holder.itemView, position);

        PersonalFoodsPagerAdapter personalFoodAdapter = new PersonalFoodsPagerAdapter(context);

        switch (position) {
            case 0:
                holder.mTvTitle.setText("主食");
                personalFoodAdapter.setFoodList(mStapleList,0);
                break;
            case 1:
                holder.mTvTitle.setText("點心");
                personalFoodAdapter.setFoodList(mSnackList,1);
                break;
            case 2:
                holder.mTvTitle.setText("飲料");
                personalFoodAdapter.setFoodList(mDrinkList,2);
                break;
        }

        holder.mViewPager.setAdapter(personalFoodAdapter);
    }

    private void runEnterAnimation(View view, int position) {
        if (position >= ANIMATED_ITEMS_COUNT - 1) {
            return;
        }

        int duration = 1500 + position * 1000;

        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            view.setTranslationX(Utils.getScreenWidth(context));
            view.animate()
                    .translationX(0)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setDuration(duration)
                    .start();
        }
    }

    public void updateItem(CandidateFoods candidateFoods){
        mStapleList = Arrays.asList(candidateFoods.getStaples());
        mSnackList = Arrays.asList(candidateFoods.getSnacks());
        mDrinkList = Arrays.asList(candidateFoods.getDrinks());
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_title)
        TextView mTvTitle;
        @Bind(R.id.view_pager)
        ViewPager mViewPager;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
