package com.mason.game.ai.behavior_tree.plan_a.impl.decorator;

import com.mason.game.ai.behavior_tree.plan_a.abs.BaseDecorator;
import com.mason.game.ai.behavior_tree.plan_a.common.NodeStatus;
import com.mason.game.ai.behavior_tree.plan_a.ifs.IBehavior;

/**
 * Created by mwu on 2020/3/30
 */
public class Repeat extends BaseDecorator {

    private int limited = 3;
    private volatile int count = 0;

    @Override
    public void onInitialize() {
        count = 0;
    }

    @Override
    public NodeStatus update() {
        while (true) {
            child.tick();
            switch (child.getStatus()) {
                case RUNNING:
                    return NodeStatus.SUCCESS;
                case FAILURE:
                    return NodeStatus.FAILURE;
                default:
                    break;
            }
            if (++count > limited) {
                return NodeStatus.SUCCESS;
            }
            child.reset();
        }
    }

    @Override
    public void addChild(IBehavior child) {
        super.addChild(child);
    }

    @Override
    public void onTerminate(NodeStatus Status) {

    }

    @Override
    public void release() {

    }

    @Override
    public void reset() {

    }
}
