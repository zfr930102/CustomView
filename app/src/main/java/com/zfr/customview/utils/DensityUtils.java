package com.zfr.customview.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.annotation.NonNull;



public class DensityUtils {
    public static void setCustomDensity(@NonNull Activity activity, Application application) {
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        float targetDensity = (float) (displayMetrics.widthPixels * 1.0 / 384);
        int targetDpi = (int) (160 * targetDensity);
        Log.e(DensityUtils.class.getSimpleName(), "targetDensity" + targetDensity);
        displayMetrics.density = targetDensity;
        displayMetrics.densityDpi = targetDpi;
        DisplayMetrics activityMetrics = activity.getResources().getDisplayMetrics();
        activityMetrics.density = targetDensity;
        activityMetrics.densityDpi = targetDpi;
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
