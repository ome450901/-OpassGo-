package com.willy.smartcityhackathon.object;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.RequestFuture;
import com.willy.smartcityhackathon.App;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by Willy on 2015/10/22.
 */
public class APIManager {

    public static final String TAG = "APIManager";

    private static final String Host = "http://60.251.3.38/device/api";

    private OnResponseListener onResponseListener;

    public interface OnResponseListener<T> {
        void onResponse(T object);

        void onErrorResponse(VolleyError error);
    }

    private void sendRequest(Request request) {
        App.getInstance().getVolleyRequestQueue().add(request);
    }

    public void sendPutRequest() {
        Map<String, Object> params = new HashMap<>();
        params.put("data", 1);

        String url = Host;

        GenericRequest<Object> request = new GenericRequest<>(
                Request.Method.PUT,
                url,
                Object.class,
                params,
                new Response.Listener<Object>() {

                    @Override
                    public void onResponse(Object response) {
                        Log.d(TAG, "response : " + response);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.toString());
                    }
                }
        );

        sendRequest(request);
    }


    public void sendPostRequest() {

        Map<String, Object> params = new HashMap<>();
        params.put("data", 1);

        String url = Host;

        GenericRequest<Object> request = new GenericRequest<>(
                Request.Method.POST,
                url,
                Object.class,
                params,
                new Response.Listener<Object>() {

                    @Override
                    public void onResponse(Object response) {
                        Log.d(TAG, "response : " + response);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.toString());
                    }
                }
        );

        sendRequest(request);
    }

    public void sendJsonArrayRequest() {

        String url = Host ;

        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.toString());
                    }
                });

        sendRequest(request);
    }

    public void sendRequestBySync() {

        String url = Host;

        RequestFuture<JSONArray> future = RequestFuture.newFuture();
        JsonArrayRequest request = new JsonArrayRequest(url, future, future);
        sendRequest(request);

        try {
            JSONArray responseJsonArray = future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.e(TAG, "getRemoteRecipesBySync error:" + e.toString());
        }
    }

    public OnResponseListener getOnResponseListener() {
        return onResponseListener;
    }

    public void setOnResponseListener(OnResponseListener onResponseListener) {
        this.onResponseListener = onResponseListener;
    }
}
