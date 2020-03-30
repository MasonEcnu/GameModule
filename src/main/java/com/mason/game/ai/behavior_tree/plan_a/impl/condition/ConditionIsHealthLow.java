package com.mason.game.ai.behavior_tree.plan_a.impl.condition;

import com.mason.game.ai.behavior_tree.plan_a.abs.BaseCondition;
import com.mason.game.ai.behavior_tree.plan_a.common.NodeStatus;
import com.mason.game.ai.behavior_tree.plan_a.ifs.IBehavior;

/**
 * Created by mwu on 2020/3/30
 */
public class ConditionIsHealthLow extends BaseCondition {
    @Override
    public void onInitialize() {

    }

    @Override
    public NodeStatus update() {
        int random = getRandom();
        if (random < 30) {
            System.out.println("Health is low");
            return !isNegation() ? NodeStatus.SUCCESS : NodeStatus.FAILURE;
        } else {
            System.out.println("Health is Not low");
            return !isNegation() ? NodeStatus.FAILURE : NodeStatus.SUCCESS;
        }
    }

    @Override
    public void onTerminate(NodeStatus Status) {

    }

    @Override
    public void release() {

    }

    @Override
    public void addChild(IBehavior child) {

    }

    @Override
    public void reset() {

    }
}
