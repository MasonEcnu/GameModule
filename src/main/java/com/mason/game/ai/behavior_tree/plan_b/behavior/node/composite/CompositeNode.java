package com.mason.game.ai.behavior_tree.plan_b.behavior.node.composite;

import com.mason.game.ai.behavior_tree.plan_b.behavior.node.BehaviorNode;

import java.util.ArrayList;

/**
 * Created by mwu on 2020/3/30
 */
public abstract class CompositeNode extends BehaviorNode {

    protected ArrayList<BehaviorNode> childrenNodeList = new ArrayList<>();

    public void addNode(BehaviorNode node) {
        childrenNodeList.add(node);
        node.parent = this;
    }
}
