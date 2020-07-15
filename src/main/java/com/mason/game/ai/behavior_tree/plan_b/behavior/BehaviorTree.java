package com.mason.game.ai.behavior_tree.plan_b.behavior;

import com.mason.game.ai.behavior_tree.plan_b.behavior.node.BehaviorNode;

/**
 * Created by mwu on 2020/3/30
 */
public class BehaviorTree {

    private BehaviorNode runningNode = null;

    public void setRunningNode(BehaviorNode runningNode) {
        this.runningNode = runningNode;
    }
}
