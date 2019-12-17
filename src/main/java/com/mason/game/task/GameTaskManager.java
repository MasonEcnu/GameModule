package com.mason.game.task;

import com.mason.game.task.checker.BuildingLevelChecker;
import com.mason.game.task.checker.PlayerLevelChecker;
import com.mason.game.task.checker.TaskChecker;
import com.mason.game.task.manager.TaskManager;
import com.mason.game.task.manager.TaskModule;
import com.mason.game.task.manager.TaskType;
import com.mason.game.task.modules.extension_task.ExtensionTaskService;
import com.mason.game.task.modules.main_task.MainTaskService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mwu on 2019/12/17
 */
public class GameTaskManager {
  private static GameTaskManager instance;
  final Map<TaskModule, TaskManager> TASK_MODULE_SERVICE_MAP = new HashMap<>();
  final Map<TaskType, TaskChecker> TASK_TYPE_CHECKER_MAP = new HashMap<>();

  static {
    try {
      instance = new GameTaskManager();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static GameTaskManager getInstance() {
    return instance;
  }

  private GameTaskManager() {
    TASK_MODULE_SERVICE_MAP.put(TaskModule.MAIN_TASK, MainTaskService.getInstance());
    TASK_MODULE_SERVICE_MAP.put(TaskModule.EXTENSION_TASK, ExtensionTaskService.getInstance());

    TASK_TYPE_CHECKER_MAP.put(TaskType.BUILDING_LEVEL, BuildingLevelChecker.getInstance());
    TASK_TYPE_CHECKER_MAP.put(TaskType.PLAYER_LEVEL, PlayerLevelChecker.getInstance());
  }
}
