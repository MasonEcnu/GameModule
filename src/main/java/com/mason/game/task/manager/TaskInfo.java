package com.mason.game.task.manager;

import com.mason.game.task.manager.config.TaskConf;

/**
 * Created by mwu on 2019/12/16
 * 任务信息接口
 */
public interface TaskInfo {
  /**
   * 获取任务配置
   *
   * @return TaskConf
   */
  TaskConf taskConf();

  /**
   * 任务进度
   */
  long getProcess();

  void setProcess(long process);

  /**
   * 任务所属模块
   */
  TaskModule getTaskModule();

  void setTaskModule(TaskModule module);

  /**
   * 获取任务Id
   *
   * @return int
   */
  default int taskId() {
    return taskConf().taskId();
  }

  /**
   * 获取任务类型
   *
   * @return TaskType
   */
  default TaskType taskType() {
    return taskConf().taskType();
  }

  /**
   * 任务是否完成
   *
   * @return boolean
   */
  default boolean isComplete() {
    return getProcess() >= taskConf().condition();
  }
}
