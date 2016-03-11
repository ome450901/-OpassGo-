package com.willy.smartcityhackathon.Utils;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

import com.willy.smartcityhackathon.App;

/**
 * Created by Willy on 2016/2/19.
 */
public class Utils {

    public static final String TAG = "Utils";

    private static int screenWidth = 0;
    private static int screenHeight = 0;

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getScreenHeight(Context c) {
        if (screenHeight == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
        }

        return screenHeight;
    }

    public static int getScreenWidth(Context c) {
        if (screenWidth == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
        }

        return screenWidth;
    }

    public static boolean isAndroid5() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }

        return false;
    }

    public static int getScreenOrientation() {

        Display display = App.getInstance().getDisplay();
        int rotation = display.getRotation();

        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        int orientation;
        // if the device's natural orientation is portrait:
        if ((rotation == Surface.ROTATION_0
                || rotation == Surface.ROTATION_180) && height > width ||
                (rotation == Surface.ROTATION_90
                        || rotation == Surface.ROTATION_270) && width > height) {
//            switch (rotation) {
//                case Surface.ROTATION_0:
//                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
//                    break;
//                case Surface.ROTATION_90:
//                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
//                    break;
//                case Surface.ROTATION_180:
//                    orientation =
//                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
//                    break;
//                case Surface.ROTATION_270:
//                    orientation =
//                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
//                    break;
//                default:
//                    Log.e(TAG, "Unknown screen orientation. Defaulting to " +
//                            "portrait.");
//                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
//                    break;
//            }

            switch (rotation) {
                case Surface.ROTATION_0:
                case Surface.ROTATION_180:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
                case Surface.ROTATION_90:
                case Surface.ROTATION_270:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
                default:
                    Log.e(TAG, "Unknown screen orientation. Defaulting to " +
                            "portrait.");
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
            }

        }
        // if the device's natural orientation is landscape or if the device
        // is square:
        else {
            switch (rotation) {
                case Surface.ROTATION_0:
                case Surface.ROTATION_180:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
                case Surface.ROTATION_90:
                case Surface.ROTATION_270:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
                default:
                    Log.e(TAG, "Unknown screen orientation. Defaulting to " +
                            "landscape.");
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
            }
        }

        return orientation;
    }
}
