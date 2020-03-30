package com.mason.game.ai.behavior_tree.plan_a.abs;

import com.mason.game.ai.behavior_tree.plan_a.ifs.ICondition;

/**
 * Created by mwu on 2020/3/30
 */
public abstract class BaseCondition extends BaseBehavior implements ICondition {

    protected boolean negation = false;

    @Override
    public boolean isNegation() {
        return negation;
    }

    @Override
    public void setNegation(boolean negation) {
        this.negation = negation;
    }

    protected int getRandom() {
        Double random = Math.random() * 100;
        return random.intValue();
    }
}
