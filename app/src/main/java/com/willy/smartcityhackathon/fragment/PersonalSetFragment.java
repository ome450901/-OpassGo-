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
import com.willy.smartcityhackathon.App;
import com.willy.smartcityhackathon.R;
import com.willy.smartcityhackathon.adapter.PersonalSetAdapter;
import com.willy.smartcityhackathon.object.APIManager;
import com.willy.smartcityhackathon.object.api.CandidateFoods;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Willy on 2015/12/13.
 */
public class PersonalSetFragment extends Fragment {


    @Bind(R.id.rv_list)
    RecyclerView mRvList;

    private ProgressDialog progressDialog;

    public PersonalSetFragment() {
    }

    public static Fragment newInstance() {
        PersonalSetFragment foodFragment = new PersonalSetFragment();
        return foodFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_personal_set, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupFeed();
    }

    private void setupFeed() {
        showProgress("阿發哥尋找食物中 ...");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRvList.setLayoutManager(linearLayoutManager);
        final PersonalSetAdapter personalSetAdapter = new PersonalSetAdapter(getContext());
        mRvList.setAdapter(personalSetAdapter);

        APIManager apiManager = new APIManager();
        apiManager.setOnResponseListener(new APIManager.OnResponseListener() {
            @Override
            public void onResponse(Object object) {
                personalSetAdapter.updateItem((CandidateFoods) object);
                mRvList.setAdapter(personalSetAdapter);
                progressDialog.dismiss();
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
//        apiManager.getCandidateFoods(new FoodCondition());
        apiManager.getCandidateFoods(App.getInstance().sFoodCondition);
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
}