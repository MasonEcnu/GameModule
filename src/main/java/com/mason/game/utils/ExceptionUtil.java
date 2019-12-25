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
}
