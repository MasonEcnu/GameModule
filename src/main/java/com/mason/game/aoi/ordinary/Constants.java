package com.mason.game.aoi.ordinary;

/**
 * Created by mwu on 2020/3/3
 */
class Constants {
    static final int NOTHING = Integer.MIN_VALUE;
    static final int MAX_CREATURE_COUNT = 100000;

    // 地图宽度最小值
    static final int MAP_MIN_WITH = 0;
    // 地图宽度最大值
    static final int MAP_MAX_WITH = 4000;
    static final int MAP_MIN_HEIGHT = 0;
    static final int MAP_MAX_HEIGHT = 4000;
    static final int GRID_WITH = 20;
    static final int GRID_HEIGHT = 20;

    static final int MAX_CREATURE_COUNT_EACH_GRID = GRID_WITH * GRID_HEIGHT / 20;

    static final int MINUTE_TO_SECONDS = 60;
    static final int HOUR_TO_MINUTE = 60;
    static final int HOUR_TO_SECONDS = 60 * 60;
    static final int DAY_TO_HOURS = 24;
    static final int DAY_TO_MINUTES = 24 * 60;
    static final int DAY_TO_SECONDS = 24 * 60 * 60;
    static final int SECOND_TO_MILLIS = 1000;
}
