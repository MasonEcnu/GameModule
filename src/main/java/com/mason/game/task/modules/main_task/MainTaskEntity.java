package com.mason.game.task.modules.main_task;

import com.mason.game.task.manager.TaskInfo;
import com.mason.game.task.manager.TaskModule;
import com.mason.game.task.manager.config.TaskConf;

/**
 * Created by mwu on 2019/12/16
 */
public class MainTaskEntity implements TaskInfo {

  private long process = 0L;
  private TaskModule module = TaskModule.MAIN_TASK;
  private TaskConf conf;

  MainTaskEntity(int taskId, int taskType, int firstParam, int secondParam, long condition) {
    conf = new TaskConf(taskId, taskType, firstParam, secondParam, condition);
  }

  @Override
  public TaskConf taskConf() {
    return conf;
  }

  @Override
  public long getProcess() {
    return process;
  }

  @Override
  public void setProcess(long process) {
    this.process = process;
  }

  @Override
  public TaskModule getTaskModule() {
    return module;
  }

  @Override
  public void setTaskModule(TaskModule module) {
    this.module = module;
  }

  @Override
  public int taskId() {
    return conf.taskId();
  }

  @Override
  public String toString() {
    return String.format("MainTaskEntity[taskId:%s, taskType:%s, firstParam:%s, secondParam:%s, condition:%s, progress:%s]",
        this.conf.taskId(), this.conf.taskType(), this.conf.firstParam(), this.conf.secondParam(), this.conf.condition(), this.process);
  }
}
