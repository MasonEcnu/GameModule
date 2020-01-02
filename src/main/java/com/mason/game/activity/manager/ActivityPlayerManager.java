package com.mason.game.activity.manager;

import com.google.common.collect.Lists;
import com.mason.game.activity.constants.ActivityConstants;
import com.mason.game.activity.player.ActivityPlayer;

import java.util.List;

/**
 * Created by mwu on 2020/1/2
 * 管理所有活动玩家
 */
public class ActivityPlayerManager {

  private List<ActivityPlayer> players = Lists.newArrayList();

  public ActivityPlayerManager() {
    for (int i = 1; i <= ActivityConstants.ACTIVITY_PLAYER_MAX_NUM; i++) {
      players.add(new ActivityPlayer(i));
    }
  }

  public List<ActivityPlayer> getPlayers() {
    return players;
  }
}
