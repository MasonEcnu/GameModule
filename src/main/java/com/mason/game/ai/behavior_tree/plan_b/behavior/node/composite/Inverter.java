package com.mason.game.ai.behavior_tree.plan_b.behavior.node.composite;

import com.mason.game.ai.behavior_tree.plan_b.behavior.node.BehaviorNode;
import com.mason.game.ai.behavior_tree.plan_b.common.NodeResult;
import com.mason.game.ai.behavior_tree.plan_b.entity.BattleUnit;

/**
 * Created by mwu on 2020/3/30
 * 取反节点，对子节点的结果取非，（只执行一次子节点！）
 */
class Inverter extends CompositeNode {

    @Override
    public NodeResult act(BattleUnit agent) {
        for (BehaviorNode behaviorNode : childrenNodeList) {
            if (behaviorNode.act(agent) == NodeResult.SUCCEED) {
                return NodeResult.FAILED;
            }
        }
        return NodeResult.SUCCEED;
    }
}
