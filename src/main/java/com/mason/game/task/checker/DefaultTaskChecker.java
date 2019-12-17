package com.mason.game.task.checker;

import com.mason.game.task.manager.TaskInfo;

/**
 * Created by mwu on 2019/12/17
 */
public class DefaultTaskChecker implements TaskChecker {

  private static DefaultTaskChecker instance;

  static {
    try {
      instance = new DefaultTaskChecker();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static DefaultTaskChecker getInstance() {
    return instance;
  }

  @Override
  public void check(TaskInfo task) {
    throw new RuntimeException("程序不应该走到DefaultTaskChecker");
  }
}
