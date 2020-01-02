package com.mason.game.activity.manager;

import com.google.common.collect.Lists;
import com.mason.game.activity.conf.ActivityConfig;
import com.mason.game.activity.constants.ActivityConstants;

import java.util.List;

/**
 * Created by mwu on 2020/1/2
 * 管理所有活动配置
 */
public class ActivityConfManager {

  private List<ActivityConfig> configs = Lists.newArrayList();

  public ActivityConfManager() {
    for (int i = 1; i <= ActivityConstants.ACTIVITY_CONFIG_MAX_NUM; i++) {
      configs.add(new ActivityConfig(i));
    }
  }

  public List<ActivityConfig> getConfigs() {
    return configs;
  }
}
