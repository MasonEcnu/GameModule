package com.mason.game.item.manager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by mwu on 2019/12/19
 * 道具类型
 */
public enum ItemType {
    DEFAULT(0), // 默认
    NORMAL(1),  // 普通道具
    TIME_LIMIT(2),  // 限时道具
    EXPERIENCE(3);  // 经验道具

    private int type;

    ItemType(int type) {
        this.type = type;
    }

    public static ItemType valueOf(int type) {
        Optional<ItemType> result = Arrays.stream(values()).filter(it -> it.type == type).findFirst();
        return result.orElse(ItemType.DEFAULT);
    }

    public static List<ItemType> valuesExceptZero() {
        return Arrays.stream(values()).filter(it -> it != DEFAULT).collect(Collectors.toList());
    }

    public int getType() {
        return type;
    }}
