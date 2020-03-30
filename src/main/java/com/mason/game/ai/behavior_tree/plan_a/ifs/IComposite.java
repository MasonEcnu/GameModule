package com.mason.game.ai.behavior_tree.plan_a.ifs;

import java.util.List;

/**
 * Created by mwu on 2020/3/30
 * 组合节点
 */
public interface IComposite extends IBehavior {

    void addChild(IBehavior child);

    void removeChild(IBehavior child);

    void clearChild();

    List<IBehavior> getChildren();

    void setChildren(List<IBehavior> behaviors);
}
