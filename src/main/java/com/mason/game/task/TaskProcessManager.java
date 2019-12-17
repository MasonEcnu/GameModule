package com.mason.game.task;

import com.alibaba.fastjson.JSON;
import com.mason.game.task.checker.DefaultTaskChecker;
import com.mason.game.task.checker.TaskChecker;
import com.mason.game.task.manager.TaskInfo;
import com.mason.game.task.manager.TaskManager;
import com.mason.game.task.manager.TaskModule;
import com.mason.game.task.manager.TaskType;
import com.mason.game.task.manager.config.TaskConf;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by mwu on 2019/12/17
 */
public class TaskProcessManager {
  private static TaskProcessManager instance;

  static {
    try {
      instance = new TaskProcessManager();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static TaskProcessManager getInstance() {
    return instance;
  }

  private TaskProcessManager() {
  }

  private GameTaskManager gameTaskManager = GameTaskManager.getInstance();

  void addTaskProgress(TaskType taskType) {
    addTaskProgress(taskType, 0, 0, 1);
  }

  public void addTaskProgress(TaskType taskType, int firstParam) {
    addTaskProgress(taskType, firstParam, 0, 1);
  }

  public void addTaskProgress(TaskType taskType, int firstParam, int secondParam) {
    addTaskProgress(taskType, firstParam, secondParam, 1);
  }

  void addTaskProgress(TaskType taskType, int firstParam, int secondParam, int amount) {
    gameTaskManager.TASK_MODULE_SERVICE_MAP.forEach((taskModule, taskManager) ->
        taskManager.loadAllTasks(allTasks ->
            notifyTaskProgressChanged(taskType, firstParam, secondParam, amount, allTasks, taskModule)
        )
    );
  }

  private void notifyTaskProgressChanged(
      TaskType taskType,
      int firstParam,
      int secondParam,
      int amount,
      Collection<TaskInfo> allTasks,
      TaskModule taskModule
  ) {
    if (allTasks.isEmpty()) return;

    Collection<TaskInfo> updatedTasks = new ArrayList<>();
    for (TaskInfo task : allTasks) {
      if (!match(task, firstParam, secondParam)) {
        continue;
      }

      TaskConf conf = task.taskConf();
      //是否匹配任务类型
      if (conf.taskType() != taskType) {
        continue;
      }

      switch (task.taskType().mode) {
        case ADD:
          long newProgress = task.getProcess() + amount;
          task.setProcess(newProgress);
          updatedTasks.add(task);
          break;
        case CHECK:
          checkTask(task);
          break;
        case COVER:
          task.setProcess(amount);
          updatedTasks.add(task);
          break;
        default:
          break;
      }
    }

    if (!updatedTasks.isEmpty()) {
      TaskManager taskService = gameTaskManager.TASK_MODULE_SERVICE_MAP.get(taskModule);
      if (taskService != null) {
        taskService.onTaskProcessUpdated(updatedTasks.toArray(new TaskInfo[]{}));
      }
    }
  }

  private void checkTask(TaskInfo task) {
    TaskChecker checker = gameTaskManager.TASK_TYPE_CHECKER_MAP.get(task.taskType());
    if (checker != null) {
      checker.check(task);
    }
  }

  private boolean match(TaskInfo task, int firstParam, int secondParam) {
    return gameTaskManager.TASK_TYPE_CHECKER_MAP
        .getOrDefault(task.taskType(), DefaultTaskChecker.getInstance()).match(task, firstParam, secondParam);
  }

  public void updateTaskProgress(TaskInfo task) {
    TaskManager taskService = gameTaskManager.TASK_MODULE_SERVICE_MAP.get(task.getTaskModule());
    if (taskService != null) {
      taskService.onTaskProcessUpdated(task);
    }
  }

  public static void main(String[] args) {
    Collection<Integer> updatedTasks = new ArrayList<>();
    updatedTasks.add(1);
    updatedTasks.add(2);
    updatedTasks.add(3);
    Integer[] temp = updatedTasks.toArray(new Integer[]{});
    System.out.println(JSON.toJSONString(temp));
  }
}
