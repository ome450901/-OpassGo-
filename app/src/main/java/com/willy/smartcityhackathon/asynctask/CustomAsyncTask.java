package com.willy.smartcityhackathon.asynctask;

import android.os.AsyncTask;

/**
 * Created by Willy on 2015/12/3.
 */
public class CustomAsyncTask extends AsyncTask<Void, Integer, Void> {

    private final String TAG = getClass().getSimpleName();

    private AsyncTaskCallback mAsyncTaskCallback;

    @Override
    protected Void doInBackground(Void... params) {

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if (getAsyncTaskCallback() != null) {
            getAsyncTaskCallback().onFinish();
        }
    }

    public AsyncTaskCallback getAsyncTaskCallback() {
        return mAsyncTaskCallback;
    }

    public void setAsyncTaskCallback(AsyncTaskCallback asyncTaskCallback) {
        mAsyncTaskCallback = asyncTaskCallback;
    }
}
