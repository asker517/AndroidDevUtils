package com.pactera.mob.framework.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class AppUtil {

  private AppUtil() {
    throw new UnsupportedOperationException("AppUtil cannot instantiated");
  }

  /**
   * 获取包信息.
   *
   * @param context the context
   */
  public static PackageInfo getPackageInfo(Context context) {
    PackageInfo info = null;
    try {
      String packageName = context.getPackageName();
      info = context.getPackageManager().getPackageInfo(packageName,PackageManager.GET_ACTIVITIES);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return info;
  }

  /**
   * 获取app版本名
   */
  public static String getAppVersionName(Context context) {
    PackageManager pm = context.getPackageManager();
    PackageInfo pi;
    try {
      pi = pm.getPackageInfo(context.getPackageName(), 0);
      return pi.versionName;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * 获取app版本号
   */
  public static int getAppVersionCode(Context context) {
    PackageManager pm = context.getPackageManager();
    PackageInfo pi;
    try {
      pi = pm.getPackageInfo(context.getPackageName(), 0);
      return pi.versionCode;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return 0;
  }
}