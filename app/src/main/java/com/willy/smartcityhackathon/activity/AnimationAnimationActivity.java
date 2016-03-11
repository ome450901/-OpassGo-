package com.willy.smartcityhackathon.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import com.willy.smartcityhackathon.R;
import com.willy.smartcityhackathon.Utils.Utils;
import com.willy.smartcityhackathon.adapter.CustomAnimationAdapter;

import butterknife.Bind;

/**
 * Created by Willy on 2016/3/11.
 */
public class AnimationAnimationActivity extends BaseAnimationActivity {

    @Bind(R.id.rvFeed)
    RecyclerView rvFeed;

    private CustomAnimationAdapter feedAdapter;
    private boolean pendingIntroAnimation;

    private static final int ANIM_DURATION_TOOLBAR = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            pendingIntroAnimation = true;
        }

        setupFeed();
    }

    private void setupFeed() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvFeed.setLayoutManager(linearLayoutManager);
        feedAdapter = new CustomAnimationAdapter(this);
        rvFeed.setAdapter(feedAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (pendingIntroAnimation) {
            pendingIntroAnimation = false;
            startIntroAnimation();
        }
        return true;
    }

    private void startIntroAnimation() {
        int actionbarSize = Utils.dpToPx(56);
        getToolbar().setTranslationY(-actionbarSize);
        getIvLogo().setTranslationY(-actionbarSize);

        getToolbar().animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300);
        getIvLogo().animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        feedAdapter.updateItems();
                    }
                })
                .setStartDelay(400);
    }

}
