package com.willy.smartcityhackathon.activity;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.willy.smartcityhackathon.R;
import com.willy.smartcityhackathon.Utils.Utils;
import com.willy.smartcityhackathon.adapter.MainPageAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Willy on 2015/11/12.
 */
public class MainActivity extends AppCompatActivity {

    private static final int ANIM_DURATION_TOOLBAR = 300;
    private static final int ANIM_DURATION_VIEW_PAGER = 800;

    private MainPageAdapter mMainPageAdapter;

    @Bind(R.id.ivLogo)
    ImageView mIvLogo;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMainPageAdapter = new MainPageAdapter(getSupportFragmentManager());

        setToolbar();
        startIntroAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setToolbar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

    private void startIntroAnimation() {
        int actionbarSize = Utils.dpToPx(56);
        mToolbar.setTranslationY(-actionbarSize);
        mIvLogo.setTranslationY(-actionbarSize);
        mViewPager.setTranslationX(Utils.getScreenWidth(this));

        mToolbar.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300);
        mIvLogo.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(400);
        mViewPager.animate()
                .translationX(0)
                .setDuration(ANIM_DURATION_VIEW_PAGER)
                .setStartDelay(600)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        mViewPager.setAdapter(mMainPageAdapter);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

    }
}