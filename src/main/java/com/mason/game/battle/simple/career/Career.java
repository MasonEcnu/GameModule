package com.mason.game.battle.simple.career;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mwu on 2019/12/23
 * 职业
 */
public enum Career {
  WARRIOR,
  MAGICIAN,
  PRIEST;

  public static List<Career> getCareers() {
    return Arrays.stream(values()).collect(Collectors.toList());
  }
}
