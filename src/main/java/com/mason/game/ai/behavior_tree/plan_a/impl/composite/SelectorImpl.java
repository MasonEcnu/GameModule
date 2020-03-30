package com.mason.game.ai.behavior_tree.plan_a.impl.composite;

import com.mason.game.ai.behavior_tree.plan_a.abs.BaseComposite;
import com.mason.game.ai.behavior_tree.plan_a.common.NodeStatus;
import com.mason.game.ai.behavior_tree.plan_a.ifs.IBehavior;
import com.mason.game.ai.behavior_tree.plan_a.ifs.composite.ISequence;

import java.util.Iterator;

/**
 * Created by mwu on 2020/3/30
 * 选择器实现
 */
public class SelectorImpl extends BaseComposite implements ISequence {

    private IBehavior currChild;

    @Override
    public void onInitialize() {

    }

    @Override
    public NodeStatus update() {
        Iterator<IBehavior> iterator = getChildren().iterator();
        if (iterator.hasNext()) {
            while (true) {
                currChild = iterator.next();
                NodeStatus status = currChild.tick();
                // 如果执行成功，就继续
                if (status != NodeStatus.FAILURE) {
                    return status;
                }
                if (!iterator.hasNext()) {
                    return NodeStatus.FAILURE;
                }
            }
        }
        // todo 循环意外终止，为啥是初始状态？
        return NodeStatus.INITIAL;
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
