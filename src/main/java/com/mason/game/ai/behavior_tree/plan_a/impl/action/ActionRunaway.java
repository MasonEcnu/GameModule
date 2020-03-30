package com.mason.game.ai.behavior_tree.plan_a.impl.action;

import com.mason.game.ai.behavior_tree.plan_a.abs.BaseAction;
import com.mason.game.ai.behavior_tree.plan_a.common.NodeStatus;
import com.mason.game.ai.behavior_tree.plan_a.ifs.IBehavior;

/**
 * Created by mwu on 2020/3/30
 * 逃跑行为
 */
public class ActionRunaway extends BaseAction {

    @Override
    public NodeStatus update() {
        System.out.println("ActionRunaway 跑路");
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
