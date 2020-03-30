package com.mason.game.ai.behavior_tree.plan_a.ifs;

/**
 * Created by mwu on 2020/3/30
 * 条件行为节点
 */
public interface ICondition {

    boolean isNegation();

    void setNegation(boolean negation);
}
