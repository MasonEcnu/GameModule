package com.mason.game.task.modules.main_task;

import com.mason.game.task.manager.TaskInfo;
import com.mason.game.task.manager.TaskType;
import com.mason.game.task.modules.extension_task.ExtensionTaskEntity;

import java.util.*;

import static com.mason.game.task.TaskConstants.MAIN_TASK_START_INDEX;

/**
 * Created by mwu on 2019/12/16
 */
class MainTaskDC {

  private Map<Integer, TaskInfo> mainTasks = new HashMap<>();

  MainTaskDC() {
    int start = MAIN_TASK_START_INDEX;
    for (int i = start; i < start + 10; i++) {
      List<TaskType> types = TaskType.valuesExceptZero();
      Collections.shuffle(types);
      TaskType type = types.get(0);
      Random random = new Random();
      switch (type) {
        case PLAYER_LEVEL:
          mainTasks.put(i, new MainTaskEntity(i, type.type, 0, 0, random.nextInt(29) + 1));
          break;
        case BUILDING_LEVEL:
          mainTasks.put(i, new MainTaskEntity(i, type.type, i, 0, random.nextInt(29) + 1));
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
