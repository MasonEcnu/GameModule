package com.mason.game.task.manager.config;


import com.mason.game.task.manager.TaskType;

/**
 * Created by mwu on 2019/12/16
 * 读取配置
 * game.task.txt
 */
public class TaskConf {
  private int taskId;
  private TaskType taskType;
  private int firstParam;
  private int secondParam;
  private long condition;

  public TaskConf(int taskId, int taskType, int firstParam, int secondParam, long condition) {
    this.taskId = taskId;
    this.taskType = TaskType.valueOf(taskType);
    this.firstParam = firstParam;
    this.secondParam = secondParam;
    this.condition = condition;
  }

  public int taskId() {
    return this.taskId;
  }

  public TaskType taskType() {
    return this.taskType;
  }

  public int firstParam() {
    return this.firstParam;
  }

  public int secondParam() {
    return this.secondParam;
  }

  public long condition() {
    return this.condition;
  }

  @Override
  public String toString() {
    return String.format("[taskId:%s, taskType:%s, firstParam:%s, secondParam:%s, condition:%s]", this.taskId, this.taskType, this.firstParam, this.secondParam, this.condition);
  }
}
