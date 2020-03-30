package com.mason.game.ai.behavior_tree.plan_a;

import com.mason.game.ai.behavior_tree.plan_a.ifs.IBehavior;

import java.util.Stack;

/**
 * Created by mwu on 2020/3/30
 * 行为树构造器
 */
public class BehaviorTreeBuilder {

    private Stack<IBehavior> behaviors = new Stack<>();
    private IBehavior treeRoot = null;

    public BehaviorTreeBuilder addBehavior(IBehavior behavior) {
        // 如果没有根节点，则设置根节点
        // 否则设置新节点为栈顶节点的子节点
        if (treeRoot == null) {
            treeRoot = behavior;
        } else {
            behaviors.peek().addChild(behavior);
        }

        // 将节点压入栈
        behaviors.push(behavior);
        return this;
    }

    public BehaviorTreeBuilder back() {
        behaviors.pop();
        return this;
    }

    public BehaviorTree end() {
        while (!behaviors.empty()) {
            behaviors.pop();
        }
        return new BehaviorTree(treeRoot);
    }
}
