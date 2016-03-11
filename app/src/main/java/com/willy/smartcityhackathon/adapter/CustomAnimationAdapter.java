package com.willy.smartcityhackathon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.willy.smartcityhackathon.R;
import com.willy.smartcityhackathon.Utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Willy on 2016/3/11.
 */
public class CustomAnimationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //要以動畫方式進來的Item數量
    private static final int ANIMATED_ITEMS_COUNT = 3;

    private Context context;
    private int lastAnimatedPosition = -1;
    private int itemsCount = 0;

    public CustomAnimationAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false);
        return new CellFeedViewHolder(view);
    }

    private void runEnterAnimation(View view, int position) {
        if (position >= ANIMATED_ITEMS_COUNT - 1) {
            return;
        }

        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            view.setTranslationY(Utils.getScreenHeight(context));
            view.animate()
                    .translationY(0)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setDuration(3000)
                    .start();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        runEnterAnimation(viewHolder.itemView, position);
        CellFeedViewHolder holder = (CellFeedViewHolder) viewHolder;
        if (position % 2 == 0) {
            holder.ivFeedCenter.setImageResource(R.mipmap.img_feed_center_1);
        } else {
            holder.ivFeedCenter.setImageResource(R.mipmap.img_feed_center_2);
        }
    }

    @Override
    public int getItemCount() {
        return itemsCount;
    }

    public static class CellFeedViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ivFeedCenter)
        ImageView ivFeedCenter;

        public CellFeedViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void updateItems() {
        itemsCount = 3;
        notifyDataSetChanged();
    }
}
