package com.mason.game.activity.conf;

import com.mason.game.activity.constants.ActivityConstants;

/**
 * Created by mwu on 2020/1/2
 * 活动配置结构
 */
public class ActivityConfig {
    private int id;
    private int level;
    private int castleLv;
    private DeviceType deviceType;
    private PlatType platType;

    public ActivityConfig(int id) {
        this.id = id;
        this.level = ActivityConstants.randomConfigLevel();
        this.castleLv = ActivityConstants.randomConfigCastleLevel();
        this.deviceType = DeviceType.random();
        this.platType = PlatType.random();
    }

    @Override
    public String toString() {
        return String.format("ActivityConfig[id:%s. level:%s, castleLv:%s, deviceType:%s, platType:%s]",
                this.id, this.level, this.castleLv, this.deviceType, this.platType);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ActivityConfig) {
            return this.id == ((ActivityConfig) obj).id;
        } else {
            return false;
        }
    }

    public int getLevel() {
        return level;
    }

    public int getCastleLv() {
        return castleLv;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public PlatType getPlatType() {
        return platType;
    }
}
