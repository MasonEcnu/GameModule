package com.mason.game.task.checker;

import com.mason.game.task.TaskProcessManager;
import com.mason.game.task.manager.TaskInfo;

/**
 * Created by mwu on 2019/12/17
 */
public class PlayerLevelChecker implements TaskChecker {

  private static PlayerLevelChecker instance;

  static {
    try {
      instance = new PlayerLevelChecker();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static PlayerLevelChecker getInstance() {
    return instance;
  }

  @Override
  public void check(TaskInfo task) {
    long oldProcess = task.getProcess();
    task.setProcess(oldProcess + 1);
    TaskProcessManager.getInstance().updateTaskProgress(task);
  }
}
