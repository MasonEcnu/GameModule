package com.mason.game.queue.manager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by mwu on 2019/12/17
 */
public enum QueueType {
    DEFAULT(0),
    BUILDING_LEVEL_UP(1), // 建筑升级
    SCIENCE_LEVEL(2), // 科技升级
    TRAIN_SOLIDER(3), // 训练士兵
    MARCH(4); // 行军

    public final int type;

    QueueType(int type) {
        this.type = type;
    }

    public static QueueType valueOf(int type) {
        Optional<QueueType> result = Arrays.stream(values()).filter(it -> it.type == type).findFirst();
        return result.orElse(QueueType.DEFAULT);
    }

    public static List<QueueType> valuesExceptZero() {
        return Arrays.stream(values()).filter(it -> it != DEFAULT).collect(Collectors.toList());
    }
}
