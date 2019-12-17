package com.mason.game.task;

import com.mason.game.task.manager.TaskManager;
import com.mason.game.task.modules.extension_task.ExtensionTaskService;
import com.mason.game.task.modules.main_task.MainTaskService;

/**
 * Created by mwu on 2019/12/16
 */
public class TaskServiceManager {

  private static TaskServiceManager instance;

  static {
    try {
      instance = new TaskServiceManager();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static TaskServiceManager getInstance() {
    return instance;
  }

  TaskManager mainTaskService = new MainTaskService();

  TaskManager extensionTaskService = new ExtensionTaskService();
}
