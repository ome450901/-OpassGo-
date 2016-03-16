package com.willy.smartcityhackathon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.willy.smartcityhackathon.R;
import com.willy.smartcityhackathon.Utils.Utils;
import com.willy.smartcityhackathon.object.api.ShareFoods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Willy on 2016/3/11.
 */
public class ShareSetAdapter extends RecyclerView.Adapter<ShareSetAdapter.ShareViewHolder> {
    //要以動畫方式進來的Item數量
    private static final int ANIMATED_ITEMS_COUNT = 4;

    private Context context;
    private int lastAnimatedPosition = -1;

    private List<ShareFoods> mFoodList = new ArrayList<>();

    public ShareSetAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ShareViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_share, parent, false);
        return new ShareViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShareViewHolder holder, int position) {
        runEnterAnimation(holder.itemView, position);
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

    public void updateItem(ShareFoods shareFoods){
        mFoodList = Arrays.asList(shareFoods);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class ShareViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_user)
        TextView mTvUser;

        public ShareViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
