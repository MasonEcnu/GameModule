package com.mason.game.task.modules.main_task;

import com.mason.game.task.GameTaskManager;
import com.mason.game.task.TaskServiceManager;
import com.mason.game.task.checker.TaskChecker;
import com.mason.game.task.manager.TaskInfo;
import com.mason.game.task.manager.TaskManager;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * Created by mwu on 2019/12/16
 */
public class MainTaskService implements TaskManager {

  private static MainTaskService instance;
  static {
    try {
      instance = new MainTaskService();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static MainTaskService getInstance() {
    return instance;
  }

  private MainTaskDC mainTaskDC = new MainTaskDC();

  @Override
  public void loadAllTasks(Consumer<Collection<TaskInfo>> callback) {
    callback.accept(mainTaskDC.getAllTasks());
  }

  @Override
  public void onTaskProcessUpdated(TaskInfo... tasks) {
    for (TaskInfo task : tasks) {
      System.out.println("MainTaskService--" + task);
    }
  }
}
