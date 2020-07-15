package com.mason.game.ai.behavior_tree.plan_b.behavior.node;

import com.mason.game.ai.behavior_tree.plan_b.common.NodeResult;
import com.mason.game.ai.behavior_tree.plan_b.entity.BattleUnit;

import java.util.HashMap;

/**
 * Created by mwu on 2020/3/30
 */
public abstract class BehaviorNode {

    public BehaviorNode parent = null;

    public void init() {

    }


    public void parseParams(HashMap<String, String> params) {

    }

    public abstract NodeResult act(BattleUnit agent);
}
