package com.mason.game.task.modules.extension_task;

import com.mason.game.task.manager.TaskInfo;
import com.mason.game.task.manager.TaskManager;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * Created by mwu on 2019/12/16
 */
public class ExtensionTaskService implements TaskManager {

  private static ExtensionTaskService instance;
  static {
    try {
      instance = new ExtensionTaskService();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static ExtensionTaskService getInstance() {
    return instance;
  }

  private ExtensionTaskDC extensionTaskDC = new ExtensionTaskDC();

  @Override
  public void loadAllTasks(Consumer<Collection<TaskInfo>> callback) {
    callback.accept(extensionTaskDC.getAllTasks());
  }

  @Override
  public void onTaskProcessUpdated(TaskInfo... tasks) {
    for (TaskInfo task : tasks) {
      System.out.println("ExtensionTaskService--" + task);
    }
  }
}
