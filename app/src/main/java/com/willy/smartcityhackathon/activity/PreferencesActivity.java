package com.willy.smartcityhackathon.activity;

import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.appcompat.BuildConfig;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;

import com.willy.smartcityhackathon.R;

/**
 * Created by Willy on 2015/10/29.
 */
public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getFragmentManager().beginTransaction().replace(
                R.id.container,
                new PreferencesFragment()).commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    public static class PreferencesFragment extends PreferenceFragment {

        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            getPreferenceManager().findPreference("mobile_info").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    getFragmentManager().beginTransaction().replace(
                            R.id.container,
                            new MobileInfoFragment()).addToBackStack(null).commit();
                    return true;
                }
            });

            SwitchPreference promoZoneSwitch = (SwitchPreference) getPreferenceManager().findPreference("string");

            getPreferenceManager().findPreference("appVersion").setSummary(BuildConfig.VERSION_NAME);
        }
    }

    public static class MobileInfoFragment extends PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.mobile_info);

            getPreferenceManager().findPreference("brand").setSummary(Build.BRAND);

            getPreferenceManager().findPreference("model").setSummary(Build.MODEL);

            getPreferenceManager().findPreference("version").setSummary(Build.VERSION.RELEASE);

            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            int vWidth = dm.widthPixels;
            int vHeight = dm.heightPixels;
            getPreferenceManager().findPreference("screen_size").setSummary(vHeight + "*" + vWidth);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
