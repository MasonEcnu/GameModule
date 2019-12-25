package com.mason.game.utils;

import java.math.BigDecimal;

/**
 * Created by mwu on 2019/12/25
 * 字符串处理工具
 */
public class StringUtil {

  public static int stringToInt(String string) {
    try {
      return new BigDecimal(string).intValue();
    } catch (Exception e) {
      return -1;
    }
  }
}
