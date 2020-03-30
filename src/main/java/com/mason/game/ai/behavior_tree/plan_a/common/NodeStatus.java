package com.mason.game.ai.behavior_tree.plan_a.common;

/**
 * Created by mwu on 2020/3/30
 * 行为树节点状态
 */
public enum NodeStatus {
    INITIAL,    // 初始状态
    SUCCESS,    // 成功
    FAILURE,    // 失败
    RUNNING,    // 运行中
    TERMINATED     // 终止
}
