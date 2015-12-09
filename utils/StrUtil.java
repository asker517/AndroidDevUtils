package com.pactera.mob.framework.utils;

import android.text.TextUtils;

/**
 * Created by Vincent on 15/12/9.
 */
public class StrUtil {

  private StrUtil() {
    throw new UnsupportedOperationException("StrUtil cannot be instantiated");
  }

  /**
   * 判断一个字符串是否为null或空值.
   *
   * @param str 指定的字符串
   * @return true or false
   */
  public static boolean isEmpty(String str) {
    return TextUtils.isEmpty(str) || str.trim().length() == 0;
  }

  /**
   * 将null转化为“”.
   *
   * @param str 指定的字符串
   * @return 字符串的String类型
   */
  public static String parseEmpty(String str) {
    if (str == null || "null".equals(str.trim())) {
      str = "";
    }
    return str.trim();
  }
}
