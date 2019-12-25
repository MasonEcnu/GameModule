package com.mason.game.battle.simple.constants;

import com.mason.game.constans.Pair;
import com.mason.game.utils.ExceptionUtil;

/**
 * Created by mwu on 2019/12/23
 * 常量
 */
public class BattleConstants {

  // 技能CD，单位：秒
  public static final int WARRIOR_SKILL_CD = 10;
  public static final int MAGICIAN_SKILL_CD = 8;
  public static final int PRIEST_SKILL_CD = 8;

  // 初始攻击范围
  public static final Pair<Integer> WARRIOR_ATTACK_RANGE = new Pair<>(30, 35);
  public static final Pair<Integer> MAGICIAN_ATTACK_RANGE = new Pair<>(28, 32);
  public static final Pair<Integer> PRIEST_ATTACK_RANGE = new Pair<>(26, 30);

  // 初始最大血量范围
  public static final Pair<Integer> WARRIOR_MAX_HP_RANGE = new Pair<>(280, 300);
  public static final Pair<Integer> MAGICIAN_MAX_HP_RANGE = new Pair<>(240, 280);
  public static final Pair<Integer> PRIEST_MAX_HP_RANGE = new Pair<>(240, 280);

  // 初始速度区间
  public static final Pair<Integer> WARRIOR_SPEED_RANGE = new Pair<>(10, 15);
  public static final Pair<Integer> MAGICIAN_SPEED_RANGE = new Pair<>(8, 12);
  public static final Pair<Integer> PRIEST_SPEED_RANGE = new Pair<>(9, 14);

  static {
    ExceptionUtil.ensure(
        WARRIOR_ATTACK_RANGE.getFirst() <= WARRIOR_ATTACK_RANGE.getSecond(),
        "WARRIOR_ATTACK_RANGE", WARRIOR_ATTACK_RANGE.toString()
    );
    ExceptionUtil.ensure(
        MAGICIAN_ATTACK_RANGE.getFirst() <= MAGICIAN_ATTACK_RANGE.getSecond(),
        "MAGICIAN_ATTACK_RANGE", MAGICIAN_ATTACK_RANGE.toString()
    );
    ExceptionUtil.ensure(
        PRIEST_ATTACK_RANGE.getFirst() <= PRIEST_ATTACK_RANGE.getSecond(),
        "PRIEST_ATTACK_RANGE", PRIEST_ATTACK_RANGE.toString()
    );
    // 血量检查
    ExceptionUtil.ensure(
        WARRIOR_MAX_HP_RANGE.getFirst() <= WARRIOR_MAX_HP_RANGE.getSecond(),
        "WARRIOR_MAX_HP_RANGE", WARRIOR_MAX_HP_RANGE.toString()
    );
    ExceptionUtil.ensure(
        MAGICIAN_MAX_HP_RANGE.getFirst() <= MAGICIAN_MAX_HP_RANGE.getSecond(),
        "MAGICIAN_MAX_HP_RANGE", MAGICIAN_MAX_HP_RANGE.toString()
    );
    ExceptionUtil.ensure(
        PRIEST_MAX_HP_RANGE.getFirst() <= PRIEST_MAX_HP_RANGE.getSecond(),
        "PRIEST_MAX_HP_RANGE", PRIEST_MAX_HP_RANGE.toString()
    );
    // 速度检查
    ExceptionUtil.ensure(
        WARRIOR_SPEED_RANGE.getFirst() <= WARRIOR_SPEED_RANGE.getSecond(),
        "WARRIOR_SPEED_RANGE", WARRIOR_SPEED_RANGE.toString()
    );
    ExceptionUtil.ensure(
        MAGICIAN_SPEED_RANGE.getFirst() <= MAGICIAN_SPEED_RANGE.getSecond(),
        "MAGICIAN_SPEED_RANGE", MAGICIAN_SPEED_RANGE.toString()
    );
    ExceptionUtil.ensure(
        PRIEST_SPEED_RANGE.getFirst() <= PRIEST_SPEED_RANGE.getSecond(),
        "PRIEST_SPEED_RANGE", PRIEST_SPEED_RANGE.toString()
    );
  }

  public static void main(String[] args) {
    System.out.println("Test BattleConstants Over...");
  }
}
