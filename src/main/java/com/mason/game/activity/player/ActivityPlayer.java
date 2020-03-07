package com.mason.game.activity.player;

import com.mason.game.activity.conf.ActivityConfig;
import com.mason.game.activity.conf.DeviceType;
import com.mason.game.activity.conf.PlatType;
import com.mason.game.activity.constants.ActivityConstants;
import com.mason.game.utils.RandomUtil;

/**
 * Created by mwu on 2020/1/2
 * 活动玩家结构
 */
public class ActivityPlayer {
    private String name;
    private int id;
    private int level;
    private int castleLv;
    private DeviceType deviceType;
    private PlatType platType;

    public ActivityPlayer(int id) {
        this.name = "ActivityPlayer-" + id;
        this.id = id;
        this.level = RandomUtil.between(ActivityConstants.ACTIVITY_PLAYER_LEVEL_RANGE);
        this.castleLv = RandomUtil.between(ActivityConstants.ACTIVITY_PLAYER_CASTLE_LEVEL_RANGE);
        this.deviceType = DeviceType.randomWithoutAll();
        this.platType = PlatType.randomWithoutAll();
    }

    @Override
    public String toString() {
        return String.format("ActivityPlayer[name:%s. level:%s, castleLv:%s, deviceType:%s, platType:%s]",
                this.name, this.level, this.castleLv, this.deviceType, this.platType);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ActivityPlayer) {
            return this.id == ((ActivityPlayer) obj).id;
        } else {
            return false;
        }
    }

    public boolean satisfy(ActivityConfig config) {
        boolean levelFlag = level >= config.getLevel();
        boolean castleLevelFlag = castleLv >= config.getCastleLv();
        boolean deviceTypeFlag = config.getDeviceType() == DeviceType.ALL || deviceType == config.getDeviceType();
        boolean platTypeFlag = config.getPlatType() == PlatType.ALL || platType == config.getPlatType();
        return levelFlag && castleLevelFlag && deviceTypeFlag && platTypeFlag;
    }
}
