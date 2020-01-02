package com.mason.game.activity;

import com.google.common.collect.Lists;
import com.mason.game.activity.conf.ActivityConfig;
import com.mason.game.activity.manager.ActivityConfManager;
import com.mason.game.activity.manager.ActivityPlayerManager;
import com.mason.game.activity.player.ActivityPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mwu on 2020/1/2
 * 活动管理器
 */
public class ActivityManager {

  private boolean runStatus = false;
  private List<ActivityPlayer> players;
  private List<ActivityConfig> configs;
  private Map<ActivityPlayer, List<ActivityConfig>> result = new HashMap<>();

  public ActivityManager() {
    players = new ActivityPlayerManager().getPlayers();
    configs = new ActivityConfManager().getConfigs();
  }

  public void start() {
    runStatus = true;
    for (ActivityPlayer player : players) {
      List<ActivityConfig> satisfiedConfigs = Lists.newArrayList();
      for (ActivityConfig config : configs) {
        if (player.satisfy(config)) {
          satisfiedConfigs.add(config);
        }
      }
      result.put(player, satisfiedConfigs);
    }
  }

  public void show() {
    if (!runStatus) {
      start();
    }
    result.forEach((player, satisfiedConfigs) -> {
      if (!satisfiedConfigs.isEmpty()) {
        System.out.println(player);
        satisfiedConfigs.forEach(System.out::println);
        System.out.println("----------------------------------------------------");
      }
    });
  }
}
