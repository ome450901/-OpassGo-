package com.willy.smartcityhackathon.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.willy.smartcityhackathon.R;
import com.willy.smartcityhackathon.adapter.ShareSetAdapter;
import com.willy.smartcityhackathon.object.APIManager;
import com.willy.smartcityhackathon.object.api.FoodCondition;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Willy on 2015/12/13.
 */
public class ShareSetFragment extends Fragment {

    @Bind(R.id.rv_list)
    RecyclerView mRvList;

    private ProgressDialog progressDialog;

    public ShareSetFragment() {
    }

    public static Fragment newInstance() {
        ShareSetFragment foodFragment = new ShareSetFragment();
        return foodFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_share_set, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupFeed();
    }

    private void setupFeed() {
//        showProgress("Loading ...");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRvList.setLayoutManager(linearLayoutManager);
        final ShareSetAdapter shareSetAdapter = new ShareSetAdapter(getContext());
        mRvList.setAdapter(shareSetAdapter);
    }

    private void showProgress(String message) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading");
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void getShareFood() {
        showProgress("Loading ...");

        APIManager apiManager = new APIManager();
        apiManager.setOnResponseListener(new APIManager.OnResponseListener() {
            @Override
            public void onResponse(Object object) {
                final ShareSetAdapter shareSetAdapter = new ShareSetAdapter(getContext());
                mRvList.setAdapter(shareSetAdapter);
                progressDialog.dismiss();
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        apiManager.getCandidateFoods(new FoodCondition());
    }
}