package com.mason.game.ai.behavior_tree.plan_a.impl.condition;

import com.mason.game.ai.behavior_tree.plan_a.abs.BaseCondition;
import com.mason.game.ai.behavior_tree.plan_a.common.NodeStatus;
import com.mason.game.ai.behavior_tree.plan_a.ifs.IBehavior;

/**
 * Created by mwu on 2020/3/30
 */
public class ConditionIsSeeEnemy extends BaseCondition {
    @Override
    public void onInitialize() {

    }

    @Override
    public NodeStatus update() {
        int random = getRandom();
        if (random < 50) {
            System.out.println("SeeEnemy");
            return !isNegation() ? NodeStatus.SUCCESS : NodeStatus.FAILURE;
        } else {
            System.out.println("Not SeeEnemy");
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
