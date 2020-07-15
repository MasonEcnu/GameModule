package com.mason.game.ai.behavior_tree.plan_b.behavior.node.composite;

import com.mason.game.ai.behavior_tree.plan_b.behavior.node.BehaviorNode;
import com.mason.game.ai.behavior_tree.plan_b.common.NodeResult;
import com.mason.game.ai.behavior_tree.plan_b.entity.BattleUnit;

/**
 * Created by mwu on 2020/3/31
 * 选择器节点
 */
public class Selector extends CompositeNode {

    @Override
    public NodeResult act(BattleUnit agent) {
        for (BehaviorNode behaviorNode : childrenNodeList) {
            NodeResult result = behaviorNode.act(agent);
            if (result != NodeResult.FAILED) {
                if (result == NodeResult.RUNNING) {
                    agent.behaviorTree.setRunningNode(this);
                }
                return result;
            }
        }
        return NodeResult.FAILED;
    }
}
