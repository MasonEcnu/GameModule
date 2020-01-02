package com.mason.game.activity.conf;

import com.mason.game.utils.RandomUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mwu on 2020/1/2
 * 平台枚举
 * QQ
 * 微信
 * 游客
 * 全部
 */
public enum PlatType {
  QQ,
  WEIXIN,
  GUEST,
  ALL;

  public static List<PlatType> typesWithoutAll = Arrays.stream(values())
      .filter(type -> type != PlatType.ALL)
      .collect(Collectors.toList());

  public static PlatType random() {
    int len = values().length;
    return values()[RandomUtil.random(len)];
  }

  public static PlatType randomWithoutAll() {
    int len = typesWithoutAll.size();
    return typesWithoutAll.get(RandomUtil.random(len));
  }
}
