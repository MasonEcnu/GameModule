package com.mason.game.task.manager;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * Created by mwu on 2019/12/16
 * 任务管理器
 * 用于加载所有模块的任务
 * 以及更新任务推送
 */
public interface TaskManager {

  void loadAllTasks(Consumer<Collection<TaskInfo>> callback);

  void onTaskProcessUpdated(TaskInfo... tasks);
}
