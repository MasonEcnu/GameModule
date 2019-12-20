package com.mason.game.task;

import com.mason.game.task.manager.TaskType;

/**
 * Created by mwu on 2019/12/17
 * 测试任务系统
 */
public class TestTask {
  public static void main(String[] args) {
    TaskProcessManager processManager = TaskProcessManager.getInstance();
    processManager.addTaskProgress(TaskType.BUILDING_LEVEL, 1, 1, 1);
    processManager.addTaskProgress(TaskType.PLAYER_LEVEL);
    processManager.addTaskProgress(TaskType.KILL_ENEMY);
    processManager.addTaskProgress(TaskType.KILL_ENEMY);
  }
}
