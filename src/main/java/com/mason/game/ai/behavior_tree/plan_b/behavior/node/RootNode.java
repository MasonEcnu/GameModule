package com.mason.game.ai.behavior_tree.plan_b.behavior.node;

import java.util.HashMap;

/**
 * Created by mwu on 2020/3/30
 * 根节点，一个AI对应一个根节点实例
 */
public class RootNode {

    private HashMap<Integer, BehaviorNode> nodeIdMapper = new HashMap<>();
    private BehaviorNode rootNode = null;
}
