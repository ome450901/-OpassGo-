package com.willy.smartcityhackathon.object;

import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.willy.smartcityhackathon.App;
import com.willy.smartcityhackathon.object.api.CandidateFoods;
import com.willy.smartcityhackathon.object.api.FoodCondition;

/**
 * Created by Willy on 2015/10/22.
 */
public class APIManager {

    public static final String TAG = "APIManager";

    private static final String Host = "https://lit-coast-30973.herokuapp.com";
//    private static final String Host = "http://private-34b3f6-hackbudget.apiary-mock.com";

    private OnResponseListener onResponseListener;

    public interface OnResponseListener<T> {
        void onResponse(T object);

        void onErrorResponse(VolleyError error);
    }

    private void sendRequest(Request request) {

        App.getInstance().getVolleyRequestQueue().add(request);
    }

    public void getCandidateFoods(FoodCondition foodCondition) {

        String url = Host + "/food/candidate?total_budget=%1$s&staple_enable=%2$s&staple_budget=%3$s&snack_enable=%4$s&snack_budget=%5$s&drink_enable=%6$s&drink_budget=%7$s";
        url = String.format(url,foodCondition.getTotalBudget(),foodCondition.isStapleEnable(),
                foodCondition.getStapleBudget(),foodCondition.isSnackEnable(),
                foodCondition.getSnackBudget(),foodCondition.isDrinkEnable(),foodCondition.getDrinkBudget());

        GenericRequest<CandidateFoods> request = new GenericRequest<>(
                Request.Method.GET,
                url,
                CandidateFoods.class,
                null,
                new Response.Listener<CandidateFoods>() {

                    @Override
                    public void onResponse(CandidateFoods response) {
                        if(getOnResponseListener()!=null){
                            getOnResponseListener().onResponse(response);
                        }
                        Log.d(TAG, "response : " + response);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(getOnResponseListener()!=null){
                            getOnResponseListener().onErrorResponse(error);
                        }
                        Log.d(TAG, error.toString());
                    }
                }
        );

        request.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        sendRequest(request);
    }

    public OnResponseListener getOnResponseListener() {
        return onResponseListener;
    }

    public void setOnResponseListener(OnResponseListener onResponseListener) {
        this.onResponseListener = onResponseListener;
    }
}
