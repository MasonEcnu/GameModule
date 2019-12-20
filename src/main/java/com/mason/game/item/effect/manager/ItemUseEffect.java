package com.mason.game.item.effect.manager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by mwu on 2019/12/19
 * 道具使用效果
 */
public enum ItemUseEffect {
  DEFAULT(0),
  PACKAGE_DROP(1),  // 掉落包
  CASTLE_SHIELD(2), // 主堡开盾
  PLAYER_EXP(3),  // 玩家经验
  PORTRAIT_UNLOCK(4); // 头像解锁

  private int effect;

  ItemUseEffect(int effect) {
    this.effect = effect;
  }


  public static ItemUseEffect valueOf(int effect) {
    Optional<ItemUseEffect> result = Arrays.stream(values()).filter(it -> it.effect == effect).findFirst();
    return result.orElse(ItemUseEffect.DEFAULT);
  }

  public static List<ItemUseEffect> valuesExceptZero() {
    return Arrays.stream(values()).filter(it -> it != DEFAULT).collect(Collectors.toList());
  }

  public int getEffect() {
    return effect;
  }

  public static void main(String[] args) {
    System.out.println(ItemUseEffect.CASTLE_SHIELD.getEffect());
  }
}
