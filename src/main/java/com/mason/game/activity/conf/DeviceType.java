package com.mason.game.activity.conf;

import com.mason.game.utils.RandomUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mwu on 2020/1/2
 * 设备类型
 * IOS
 * 安卓
 * 全部
 */
public enum DeviceType {
    IOS,
    ANDROID,
    ALL;

    public static List<DeviceType> typesWithoutAll = Arrays.stream(values())
            .filter(type -> type != DeviceType.ALL)
            .collect(Collectors.toList());

    public static DeviceType random() {
        int len = values().length;
        return values()[RandomUtil.random(len)];
    }

    public static DeviceType randomWithoutAll() {
        int len = typesWithoutAll.size();
        return typesWithoutAll.get(RandomUtil.random(len));
    }
}
