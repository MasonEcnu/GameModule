package com.mason.game.ai.behavior_tree.plan_b.behavior.node.leaf.action;

import com.mason.game.ai.behavior_tree.plan_b.behavior.node.leaf.LeafNode;
import com.mason.game.ai.behavior_tree.plan_b.common.NodeResult;
import com.mason.game.ai.behavior_tree.plan_b.entity.BattleUnit;

/**
 * Created by mwu on 2020/3/31
 * 使用技能
 */
public class UseSkill extends LeafNode {

    @Override
    public NodeResult act(BattleUnit agent) {
        System.out.println("使用技能木有实现。。。");
        return NodeResult.FAILED;
    }
}
