package com.mason.game.ai.behavior_tree.plan_a.impl.composite;

import com.mason.game.ai.behavior_tree.plan_a.abs.BaseComposite;
import com.mason.game.ai.behavior_tree.plan_a.common.NodeStatus;
import com.mason.game.ai.behavior_tree.plan_a.common.ParallelPolicy;
import com.mason.game.ai.behavior_tree.plan_a.ifs.IBehavior;
import com.mason.game.ai.behavior_tree.plan_a.ifs.composite.IParallel;

/**
 * Created by mwu on 2020/3/30
 * 并行节点实现
 */
public class ParallelImpl extends BaseComposite implements IParallel {

    private ParallelPolicy successPolicy;
    private ParallelPolicy failurePolicy;

    public ParallelImpl(ParallelPolicy successPolicy, ParallelPolicy failurePolicy) {
        this.successPolicy = successPolicy;
        this.failurePolicy = failurePolicy;
    }

    @Override
    public void onInitialize() {

    }

    @Override
    public NodeStatus update() {
        int successCount = 0;
        int failureCount = 0;
        int childrenSize = getChildren().size();
        for (IBehavior behavior : getChildren()) {
            // 行为终止则不再执行
            if (!(behavior.getStatus() == NodeStatus.SUCCESS || behavior.getStatus() == NodeStatus.FAILURE)) {
                behavior.tick();
            }

            if (behavior.getStatus() == NodeStatus.SUCCESS) {
                ++successCount;
                if (successPolicy == ParallelPolicy.REQUIRE_ONE) {
                    behavior.reset();
                    return NodeStatus.SUCCESS;
                }
            }

            if (behavior.getStatus() == NodeStatus.FAILURE) {
                ++failureCount;
                if (failurePolicy == ParallelPolicy.REQUIRE_ONE) {
                    behavior.reset();
                    return NodeStatus.FAILURE;
                }
            }
        }

        if (failurePolicy == ParallelPolicy.REQUIRE_ALL && failureCount == childrenSize) {
            for (IBehavior behavior : getChildren()) {
                behavior.reset();
            }
            return NodeStatus.FAILURE;
        }

        if (successPolicy == ParallelPolicy.REQUIRE_ALL && successCount == childrenSize) {
            for (IBehavior behavior : getChildren()) {
                behavior.reset();
            }
            return NodeStatus.SUCCESS;
        }

        return NodeStatus.RUNNING;
    }

    @Override
    public void onTerminate(NodeStatus Status) {
        for (IBehavior behavior : getChildren()) {
            if (behavior.getStatus() == NodeStatus.RUNNING) {
                behavior.abort();
            }
            behavior.reset();
        }
    }

    @Override
    public void release() {

    }

    @Override
    public void reset() {

    }
}
