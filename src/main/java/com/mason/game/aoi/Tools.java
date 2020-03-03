package com.mason.game.aoi;

import java.util.Random;

/**
 * Created by mwu on 2020/3/3
 */
class Tools {

  private static Random random = new Random(System.currentTimeMillis());

  static int random(int bound) {
    return random.nextInt(bound);
  }

  static int random(int start, int end) {
    return start + random.nextInt(end);
  }

  static String generateCreateName(int i) {
    return String.format("Creature(%s)", i);
  }
}
