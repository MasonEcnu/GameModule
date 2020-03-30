package com.mason.game.ai.behavior_tree.plan_a.impl.condition;

import com.mason.game.ai.behavior_tree.plan_a.abs.BaseCondition;
import com.mason.game.ai.behavior_tree.plan_a.common.NodeStatus;
import com.mason.game.ai.behavior_tree.plan_a.ifs.IBehavior;

/**
 * Created by mwu on 2020/3/30
 * 条件
 * 敌人死了
 */
public class ConditionIsEnemyDead extends BaseCondition {

    public ConditionIsEnemyDead(boolean negation) {
        setNegation(negation);
    }

    @Override
    public void onInitialize() {

    }

    @Override
    public NodeStatus update() {
        int random = getRandom();
        if (random < 60) {
            System.out.println("Enemy is dead");
            return !isNegation() ? NodeStatus.SUCCESS : NodeStatus.FAILURE;
        } else {
            System.out.println("Enemy is not dead");
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
