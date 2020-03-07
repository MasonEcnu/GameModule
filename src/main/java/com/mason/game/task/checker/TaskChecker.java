package com.mason.game.task.checker;

import com.mason.game.task.manager.TaskInfo;

/**
 * Created by mwu on 2019/12/17
 * 任务检查器接口
 */
public interface TaskChecker {
    /**
     * 检查[game.task]并更新其进度
     *
     * @param task 实现TaskInfo接口的类
     */
    void check(TaskInfo task);

    /**
     * 检查任务参数是否匹配
     *
     * @param task        实现TaskInfo接口的类
     * @param firstParam  第一条件
     * @param secondParam 第二条件
     * @return boolean
     */
    default boolean match(TaskInfo task, int firstParam, int secondParam) {
        if (task.taskConf().firstParam() != 0 && firstParam != task.taskConf().firstParam())
            return false;
        return task.taskConf().secondParam() == 0 || secondParam == task.taskConf().secondParam();
    }
}
