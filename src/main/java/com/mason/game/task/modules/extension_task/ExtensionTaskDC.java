package com.mason.game.task.modules.extension_task;

import com.mason.game.task.manager.TaskInfo;
import com.mason.game.task.manager.TaskType;

import java.util.*;

import static com.mason.game.task.TaskConstants.EXTENSION_TASK_START_INDEX;

/**
 * Created by mwu on 2019/12/16
 */
class ExtensionTaskDC {

    private Map<Integer, TaskInfo> mainTasks = new HashMap<>();

    ExtensionTaskDC() {
        int start = EXTENSION_TASK_START_INDEX;
        for (int i = start; i < start + 10; i++) {
            List<TaskType> types = TaskType.valuesExceptZero();
            Collections.shuffle(types);
            TaskType type = types.get(0);
            Random random = new Random();
            switch (type) {
                case PLAYER_LEVEL:
                    mainTasks.put(i, new ExtensionTaskEntity(i, type.type, 0, 0, random.nextInt(29) + 1));
                    break;
                case BUILDING_LEVEL:
                    mainTasks.put(i, new ExtensionTaskEntity(i, type.type, i, 0, random.nextInt(29) + 1));
                    break;
                case KILL_ENEMY:
                    mainTasks.put(i, new ExtensionTaskEntity(i, type.type, 0, 0, random.nextInt(29) + 1));
                    break;
                default:
                    System.out.println("some thing is wrong...");
            }
        }
    }

    Collection<TaskInfo> getAllTasks() {
        return mainTasks.values();
    }
}
