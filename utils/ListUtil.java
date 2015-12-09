package com.pactera.mob.framework.utils;

import java.util.List;

/**
 * Created by Vincent on 15/12/9.
 */
public class ListUtil {

  private ListUtil() {
    throw new AssertionError();
  }

  public static <V> int getSize(List<V> sourceList) {
    return sourceList == null ? 0 : sourceList.size();
  }

  public static <V> boolean isEmpty(List<V> sourceList) {
    return (sourceList == null || sourceList.size() == 0);
  }
}
