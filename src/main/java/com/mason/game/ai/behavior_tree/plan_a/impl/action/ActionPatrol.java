package com.mason.game.ai.behavior_tree.plan_a.impl.action;

import com.mason.game.ai.behavior_tree.plan_a.abs.BaseAction;
import com.mason.game.ai.behavior_tree.plan_a.common.NodeStatus;
import com.mason.game.ai.behavior_tree.plan_a.ifs.IBehavior;

/**
 * Created by mwu on 2020/3/30
 * 巡逻行为
 */
public class ActionPatrol extends BaseAction {

    @Override
    public NodeStatus update() {
        System.out.println("ActionPatrol 巡逻");
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
