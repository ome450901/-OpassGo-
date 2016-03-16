package com.willy.smartcityhackathon.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.willy.smartcityhackathon.R;
import com.willy.smartcityhackathon.adapter.CandidateFoodsPageAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Willy on 2015/11/12.
 */
public class CandidateFoodsActivity extends AppCompatActivity {

    private CandidateFoodsPageAdapter mCandidateFoodsPageAdapter;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mCandidateFoodsPageAdapter = new CandidateFoodsPageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mCandidateFoodsPageAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setToolbar();
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
}