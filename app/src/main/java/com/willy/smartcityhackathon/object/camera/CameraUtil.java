package com.willy.smartcityhackathon.object.camera;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.hardware.Camera;
import android.view.Display;
import android.view.Surface;

import com.willy.smartcityhackathon.App;
import com.willy.smartcityhackathon.Utils.Utils;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Willy on 2015/12/24.
 */
public class CameraUtil {
    public static final String TAG = "CameraUtil";

    private static CameraUtil sCameraUtil = null;

    private Camera.CameraInfo cameraInfo;

    public static CameraUtil getInstance() {
        if (sCameraUtil == null) {
            sCameraUtil = new CameraUtil();
            return sCameraUtil;
        } else {
            return sCameraUtil;
        }
    }

    public Camera getCameraAndOpen() {
        Camera camera = null;

        int unUseID = 0;
        cameraInfo = new Camera.CameraInfo();

        for (int camIdx = 0; camIdx < Camera.getNumberOfCameras(); camIdx++) {
            Camera.getCameraInfo(camIdx, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {

                try {
                    camera = Camera.open(camIdx);
                } catch (RuntimeException e) {
                    camera = null;
                }

                if (camera != null) {
                    break;
                }
            } else {
                unUseID = camIdx;
            }
        }

        if (camera == null) {
            try {
                camera = Camera.open(unUseID);
                Camera.getCameraInfo(unUseID, cameraInfo);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        return camera;
    }

    public Camera.Size getOptimalPreviewSize(List<Camera.Size> sizeList) {
        Camera.Size optimalSize = null;

        Collections.sort(sizeList, new Comparator<Camera.Size>() {

            public int compare(final Camera.Size a, final Camera.Size b) {
                return b.width * b.height - a.width * a.height;
            }
        });

        for (Camera.Size size : sizeList) {
            if (size.width <= 600 && size.height <= 600) {
                optimalSize = size;
                break;
            }
        }

//        TCIT's Method
//        for (int i = 0; i < sizeList.size(); i++) {
//            Camera.Size listSize = sizeList.get(i);
//            if ((listSize.height > listSize.width) &&
//                    (optimalSize == null || (listSize.width * listSize.height) > (optimalSize.width * optimalSize.height))) {
//                optimalSize = sizeList.get(i);
//            }
//        }

        if (optimalSize == null) {
            optimalSize = sizeList.get(sizeList.size() - 1);
        }

        return optimalSize;
    }

    public byte[] bitmapToByteArray(Bitmap bitmap) {

        int bytes = bitmap.getByteCount();
        ByteBuffer buffer = ByteBuffer.allocate(bytes);
        bitmap.copyPixelsToBuffer(buffer);

        return buffer.array();
    }

    public Bitmap byteArrayToBitmap(Bitmap bmpOriginal, byte[] imageArray) {
        Bitmap.Config configBmp = Bitmap.Config.valueOf(bmpOriginal.getConfig().name());
        Bitmap bitmap_tmp = Bitmap.createBitmap(bmpOriginal.getWidth(), bmpOriginal.getHeight(), configBmp);
        ByteBuffer buffer = ByteBuffer.wrap(imageArray);
        bitmap_tmp.copyPixelsFromBuffer(buffer);

        return bitmap_tmp;
    }

    public Bitmap convertBitmapToGraySale(Bitmap bmpOriginal) {
        int height = bmpOriginal.getHeight();
        int width = bmpOriginal.getWidth();

        Bitmap grayBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(grayBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(colorMatrix);
        paint.setColorFilter(colorMatrixFilter);
        canvas.drawBitmap(bmpOriginal, 0, 0, paint);

        return grayBitmap;
    }

    public byte[] convertByteDataToGraySaleImage(byte[] data, int width, int height) {
        int length = width * height;

        byte[] image = new byte[length];
        int counter = 0;

        switch (Utils.getScreenOrientation()) {
            case ActivityInfo.SCREEN_ORIENTATION_PORTRAIT:
                for (int i = image.length - 1; i >= 0; i--) {
                    byte p = (byte) (data[i] & 0xff);
                    image[counter] = p;
                    counter = (counter + height);
                    if (counter >= length) {
                        counter -= (length - 1);
                    }
                }
                break;
            case ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE:
                for (int i = length - 1; i >= 0; i--) {
                    image[i] = (byte) (data[i] & 0xff);
                }
                break;
        }

        return image;
    }

    public int getCorrectCameraOrientation() {

        int degrees = 0;
        Display display = App.getInstance().getDisplay();

//        switch (display.getRotation()) {
//            case Surface.ROTATION_0:
//                degrees = 0;
//                break;
//
//            case Surface.ROTATION_90:
//                degrees = 90;
//                break;
//
//            case Surface.ROTATION_180:
//                degrees = 180;
//                break;
//
//            case Surface.ROTATION_270:
//                degrees = 270;
//                break;
//
//        }

        switch (display.getRotation()) {
            case Surface.ROTATION_0:
            case Surface.ROTATION_180:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
            case Surface.ROTATION_270:
                degrees = 90;
                break;
        }

        int result;
        if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (cameraInfo.orientation + degrees) % 360;
            result = (360 - result) % 360;
        } else {
            result = (cameraInfo.orientation - degrees + 360) % 360;
        }

        return result;
    }
}
