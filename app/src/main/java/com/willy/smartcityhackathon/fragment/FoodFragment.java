package com.willy.smartcityhackathon.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.willy.smartcityhackathon.R;
import com.willy.smartcityhackathon.activity.CandidateFoodsActivity;
import com.willy.smartcityhackathon.object.api.FoodCondition;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Willy on 2015/12/13.
 */
public class FoodFragment extends Fragment {

    private FoodCondition mFoodCondition;

    @Bind(R.id.edt_budget)
    EditText mEdtBudget;
    @Bind(R.id.iv_bg)
    ImageView mIvBg;
    @Bind(R.id.btn_search)
    ImageButton mBtnSearch;
    @Bind(R.id.btn_setting)
    ImageButton mBtnSetting;

    public FoodFragment() {
    }

    public static Fragment newInstance() {
        FoodFragment foodFragment = new FoodFragment();
        return foodFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_food, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBtnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodConditionDialogFragment foodConditionDialogFragment = new FoodConditionDialogFragment();
                foodConditionDialogFragment.show(getFragmentManager(), null);
            }
        });

        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(getActivity(), CandidateFoodsActivity.class);
                startActivity(intent);
                //Disable enter transition for new Acitvity
                getActivity().overridePendingTransition(0, 0);
            }
        });

        Picasso.with(getContext())
                .load(R.mipmap.bg_food)
                .into(mIvBg);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(500);
        mIvBg.startAnimation(fadeIn);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}