package com.mason.game.ai.behavior_tree.plan_a.abs;

import com.mason.game.ai.behavior_tree.plan_a.ifs.IBehavior;
import com.mason.game.ai.behavior_tree.plan_a.ifs.IDecorator;

/**
 * Created by mwu on 2020/3/30
 */
public abstract class BaseDecorator extends BaseBehavior implements IDecorator {

    protected IBehavior child;

    @Override
    public void addChild(IBehavior child) {
        this.child = child;
    }
}
