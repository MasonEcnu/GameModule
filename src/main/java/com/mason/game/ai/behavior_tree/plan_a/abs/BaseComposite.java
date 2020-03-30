package com.mason.game.ai.behavior_tree.plan_a.abs;

import com.mason.game.ai.behavior_tree.plan_a.ifs.IBehavior;
import com.mason.game.ai.behavior_tree.plan_a.ifs.IComposite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mwu on 2020/3/30
 */
public abstract class BaseComposite extends BaseBehavior implements IComposite {

    protected ArrayList<IBehavior> children = new ArrayList<>();

    @Override
    public void addChild(IBehavior child) {
        children.add(child);
    }

    @Override
    public void removeChild(IBehavior child) {
        children.remove(child);
    }

    @Override
    public void clearChild() {
        children.clear();
    }

    @Override
    public List<IBehavior> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<IBehavior> behaviors) {
        children.clear();
        children.addAll(behaviors);
    }
}
