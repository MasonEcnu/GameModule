package com.mason.game.utils;

import com.mason.game.constans.Pair;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by mwu on 2019/12/25
 * 随机数生成工具
 */
public class RandomUtil {

  public static int between(int min, int max) {
    if (min > max) {
      return min;
    }
    return ThreadLocalRandom.current().nextInt(max - min + 1) + min;
  }

  public static double between(double min, double max) {
    if (min > max) {
      return min;
    }
    return ThreadLocalRandom.current().nextDouble(max - min + 1) + min;
  }

  public static int between(Pair<Integer> range) {
    return between(range.getFirst(), range.getSecond());
  }

  public static int random(int max) {
    if (max <= 0) {
      return 0;
    } else {
      return ThreadLocalRandom.current().nextInt(max);
    }
  }
}
