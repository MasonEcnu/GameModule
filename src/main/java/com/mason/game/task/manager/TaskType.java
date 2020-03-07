package com.mason.game.task.manager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by mwu on 2019/12/16
 */
public enum TaskType {
    DEFAULT(0, TaskUpdateMode.CHECK),
    BUILDING_LEVEL(1, TaskUpdateMode.CHECK),
    PLAYER_LEVEL(2, TaskUpdateMode.CHECK),
    KILL_ENEMY(3, TaskUpdateMode.ADD);

    public final int type;
    public final TaskUpdateMode mode;

    TaskType(int type, TaskUpdateMode mode) {
        this.type = type;
        this.mode = mode;
    }

    public static TaskType valueOf(int type) {
        Optional<TaskType> result = Arrays.stream(values()).filter(it -> it.type == type).findFirst();
        return result.orElse(TaskType.DEFAULT);
    }

    public static List<TaskType> valuesExceptZero() {
        return Arrays.stream(values()).filter(it -> it != DEFAULT).collect(Collectors.toList());
    }
}