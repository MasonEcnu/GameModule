package com.mason.game.ai.behavior_tree.plan_a.impl.action;

import com.mason.game.ai.behavior_tree.plan_a.abs.BaseAction;
import com.mason.game.ai.behavior_tree.plan_a.common.NodeStatus;
import com.mason.game.ai.behavior_tree.plan_a.ifs.IBehavior;

/**
 * Created by mwu on 2020/3/30
 * 攻击行为
 */
public class ActionAttack extends BaseAction {

    @Override
    public NodeStatus update() {
        System.out.println("ActionAttack 攻击");
        return NodeStatus.SUCCESS;
    }

    @Override
    public void onInitialize() {

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
