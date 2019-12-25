package com.mason.game.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by mwu on 2019/12/25
 * 随机数生成工具
 */
public class RandomUtil {

  public static int between(int min, int max) {
    return ThreadLocalRandom.current().nextInt(max - min + 1) + min;
  }

  public static int random(int max) {
    return ThreadLocalRandom.current().nextInt(max);
  }
}
