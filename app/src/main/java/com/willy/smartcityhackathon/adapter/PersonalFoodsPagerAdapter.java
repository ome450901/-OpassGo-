package com.willy.smartcityhackathon.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.willy.smartcityhackathon.R;
import com.willy.smartcityhackathon.object.api.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Willy on 2016/1/28.
 */
public class PersonalFoodsPagerAdapter extends PagerAdapter {

    private Context mContext;

    private List<Food> mFoodList = new ArrayList<>();

    private int type;

    public PersonalFoodsPagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        {
            LayoutInflater LayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rootView = LayoutInflater.inflate(R.layout.item_food, container, false);
            container.addView(rootView);

            TextView mTvFoodTitle = (TextView) rootView.findViewById(R.id.tv_food_title);
            TextView mTvCal = (TextView) rootView.findViewById(R.id.tv_cal);
            TextView mTvPrice = (TextView) rootView.findViewById(R.id.tv_price);
            ImageView imageView = (ImageView) rootView.findViewById(R.id.iv_img);

            mTvFoodTitle.setText(mFoodList.get(position).getName());

            mTvPrice.setText("價錢：" + String.valueOf(mFoodList.get(position).getPrice()) + " 元");

            Random random = new Random();
            int cal = 0;

            switch (type) {
                case 0:
                    cal = random.nextInt((999 - 500) + 1) + 500;

                    Picasso.with(getContext())
                            .load(R.mipmap.ic_staple)
                            .into(imageView);
                    break;
                case 1:
                    cal = random.nextInt((500 - 300) + 1) + 300;

                    Picasso.with(getContext())
                            .load(R.mipmap.ic_snack)
                            .into(imageView);
                    break;
                case 2:

                    cal = random.nextInt((500 - 300) + 1) + 300;

                    Picasso.with(getContext())
                            .load(R.mipmap.ic_drink)
                            .into(imageView);
                    break;
            }

            mTvCal.setText("卡路里：" + String.valueOf(cal) + " 大卡");

            return rootView;
        }
    }

    public void setFoodList(List<Food> foodList, int type) {
        mFoodList = foodList;
        this.type = type;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mFoodList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    public Context getContext() {
        return mContext;
    }
}