package com.willy.smartcityhackathon.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.willy.smartcityhackathon.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Willy on 2015/12/13.
 */
public class ShareFragment extends Fragment {

    @Bind(R.id.iv_bg)
    ImageView mIvBg;

    public ShareFragment() {
    }

    public static Fragment newInstance() {
        ShareFragment shareFragment = new ShareFragment();
        return shareFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_share, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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