package com.willy.smartcityhackathon.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.widget.ImageView;

import com.willy.smartcityhackathon.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Willy on 2015/11/12.
 */
public class SplashActivity extends Activity {

    private final int SYSTEM_OVERLAY_REQUEST = 10;
    private final int PERMISSION_REQUEST = 20;

    private final String[] Permissions = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Bind(R.id.imgView_bg)
    ImageView mImgViewBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= 23) {
            checkSystemOverlayPermissions();
        } else {
            PermissionsChecked();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mImgViewBg.setImageBitmap(null);
        System.gc();
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SYSTEM_OVERLAY_REQUEST:
                checkSystemOverlayPermissions();
                break;
            case PERMISSION_REQUEST:
                checkOtherPermissions();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_REQUEST:
                checkOtherPermissions();
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkSystemOverlayPermissions() {
        if (!Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, SYSTEM_OVERLAY_REQUEST);
        } else {
            checkOtherPermissions();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkOtherPermissions() {
        List<String> permissionList = new ArrayList<>();

        for (int i = 0; i < Permissions.length; i++) {
            if (checkSelfPermission(Permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Permissions[i]);
            }
        }

        if (permissionList.isEmpty()) {
            PermissionsChecked();
        } else {
            ActivityCompat.requestPermissions(this,
                    permissionList.toArray(new String[permissionList.size()]), PERMISSION_REQUEST);
        }

    }

    private void PermissionsChecked() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1500);
    }
}
