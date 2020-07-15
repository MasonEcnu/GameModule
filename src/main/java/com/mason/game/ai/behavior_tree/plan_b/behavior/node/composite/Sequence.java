package com.mason.game.ai.behavior_tree.plan_b.behavior.node.composite;

import com.mason.game.ai.behavior_tree.plan_b.behavior.node.BehaviorNode;
import com.mason.game.ai.behavior_tree.plan_b.common.NodeResult;
import com.mason.game.ai.behavior_tree.plan_b.entity.BattleUnit;

/**
 * Created by mwu on 2020/3/31
 * 顺序节点
 */
public class Sequence extends CompositeNode {

    @Override
    public NodeResult act(BattleUnit agent) {
        for (BehaviorNode behaviorNode : childrenNodeList) {
            NodeResult result = behaviorNode.act(agent);
            if (result == NodeResult.FAILED) {
                return NodeResult.FAILED;
            } else if (result == NodeResult.KEEP_RUNNING) {
                agent.behaviorTree.setRunningNode(this);
                return NodeResult.RUNNING;
            } else if (result == NodeResult.RUNNING) {
                return NodeResult.RUNNING;
            }
        }
        return NodeResult.SUCCEED;
    }
}
