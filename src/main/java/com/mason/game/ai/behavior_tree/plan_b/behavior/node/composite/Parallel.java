package com.mason.game.ai.behavior_tree.plan_b.behavior.node.composite;

import com.mason.game.ai.behavior_tree.plan_b.behavior.node.BehaviorNode;
import com.mason.game.ai.behavior_tree.plan_b.common.NodeResult;
import com.mason.game.ai.behavior_tree.plan_b.entity.BattleUnit;

/**
 * Created by mwu on 2020/3/31
 * 并行节点
 */
public class Parallel extends CompositeNode {

    @Override
    public NodeResult act(BattleUnit agent) {
        NodeResult result = NodeResult.SUCCEED;
        for (BehaviorNode behaviorNode : childrenNodeList) {
            NodeResult r = behaviorNode.act(agent);
            if (r == NodeResult.FAILED) {
                result = NodeResult.FAILED;
            } else if (r == NodeResult.KEEP_RUNNING) {
                if (result != NodeResult.FAILED) {
                    agent.behaviorTree.setRunningNode(this);
                    result = NodeResult.SUCCEED;
                }
            } else if (r == NodeResult.RUNNING) {
                if (result != NodeResult.FAILED) {
                    result = NodeResult.SUCCEED;
                }
            }
        }
        return result;
    }
}
