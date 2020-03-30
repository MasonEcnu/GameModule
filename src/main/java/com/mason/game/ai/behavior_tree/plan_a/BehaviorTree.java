package com.mason.game.ai.behavior_tree.plan_a;

import com.mason.game.ai.behavior_tree.plan_a.ifs.IBehavior;

/**
 * Created by mwu on 2020/3/30
 * 行为树基类
 */
public class BehaviorTree {

    private IBehavior root;

    public BehaviorTree(IBehavior root) {
        this.root = root;
    }

    public void tick() {
        root.tick();
    }

    public boolean haveRoot() {
        return root != null;
    }

    public void setRoot(IBehavior inNode) {
        this.root = inNode;
    }

    public void release() {
        root.release();
    }
}
