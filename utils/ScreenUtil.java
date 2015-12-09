package com.pactera.mob.framework.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class ScreenUtil {

  private static int screenWidth = 0;
  private static int screenHeight = 0;

  private ScreenUtil() {
    throw new UnsupportedOperationException("ScreenUtil cannot be instantiated");
  }

  public static int getScreenWidth(Context context) {
    if (screenHeight == 0) {
      WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
      DisplayMetrics outMetrics = new DisplayMetrics();
      wm.getDefaultDisplay().getMetrics(outMetrics);
      screenHeight = outMetrics.widthPixels;
    }
    return screenHeight;
  }

  public static int getScreenHeight(Context context) {
    if (screenHeight == 0) {
      WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
      DisplayMetrics outMetrics = new DisplayMetrics();
      wm.getDefaultDisplay().getMetrics(outMetrics);
      screenHeight = outMetrics.heightPixels;
    }
    return screenHeight;
  }
}