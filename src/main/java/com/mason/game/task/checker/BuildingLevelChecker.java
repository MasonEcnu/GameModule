package com.mason.game.task.checker;

import com.mason.game.task.TaskProcessManager;
import com.mason.game.task.manager.TaskInfo;

/**
 * Created by mwu on 2019/12/17
 */
public class BuildingLevelChecker implements TaskChecker {

    private static BuildingLevelChecker instance;

    static {
        try {
            instance = new BuildingLevelChecker();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BuildingLevelChecker getInstance() {
        return instance;
    }

    private BuildingLevelChecker() {
    }

    @Override
    public void check(TaskInfo task) {
        long oldProcess = task.getProcess();
        task.setProcess(oldProcess + 1);
        TaskProcessManager.getInstance().updateTaskProgress(task);
    }
}
