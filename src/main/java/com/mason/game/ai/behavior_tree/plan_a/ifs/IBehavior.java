package com.mason.game.ai.behavior_tree.plan_a.ifs;

import com.mason.game.ai.behavior_tree.plan_a.common.NodeStatus;

/**
 * Created by mwu on 2020/3/30
 * 行为树节点行为基础类
 * 对整个行为树的行为作出规范
 * onInitialize：初始化节点
 * onTerminate：终止节点
 * release：销毁节点
 * update：具体行为执行
 */
public interface IBehavior {

    //设置调用顺序，onInitialize--update--onTerminate
    NodeStatus tick();

    void onInitialize();

    NodeStatus update();

    void onTerminate(NodeStatus Status); //节点调用后执行

    void release();//释放对象所占资源

    void addChild(IBehavior child);

    void setStatus(NodeStatus status);

    NodeStatus getStatus();

    void reset();

    void abort();
}
