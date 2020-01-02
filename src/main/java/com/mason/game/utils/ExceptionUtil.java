package com.mason.game.utils;

import com.alibaba.fastjson.JSON;

/**
 * Created by mwu on 2019/12/25
 */
public class ExceptionUtil {

  public static void ensure(boolean expression, String... args) {
    if (!expression) {
      throw new RuntimeException(JSON.toJSONString(args));
    }
  }

  public static <T> T requireNotNull(T obj, String... args) {
    if (obj == null) {
      throw new RuntimeException(JSON.toJSONString(args));
    }
    return obj;
  }

  public static void main(String[] args) {
    System.out.println(ExceptionUtil.requireNotNull(1));
  }
}
