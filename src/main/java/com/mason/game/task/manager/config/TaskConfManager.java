package com.mason.game.task.manager.config;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by mwu on 2019/12/16
 * 配置管理类
 * 负责读取配置
 */
public class TaskConfManager {

    private static final String confFileName = "task.txt";
    private static TaskConfManager instance;

    static {
        try {
            instance = new TaskConfManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<Integer, TaskConf> taskConfMap = new HashMap<>();

    private TaskConfManager() throws Exception {
        InputStream is = Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(confFileName));
        InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains("#")) {
                String[] params = line.split("#");
                if (params.length > 0 && isNumeric(params[0])) {
                    taskConfMap.put(Integer.valueOf(params[0]), new TaskConf(
                            Integer.valueOf(params[0]),
                            Integer.valueOf(params[1]),
                            Integer.valueOf(params[2]),
                            Integer.valueOf(params[3]),
                            Long.valueOf(params[4])
                    ));
                }
            }
        }
    }

    public static TaskConfManager getInstance() {
        return instance;
    }

    private boolean isNumeric(String str) {
        try {
            new BigDecimal(str);
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }
        return true;
    }
}
