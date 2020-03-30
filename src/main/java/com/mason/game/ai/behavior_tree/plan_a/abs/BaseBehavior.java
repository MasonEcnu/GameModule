package com.mason.game.ai.behavior_tree.plan_a.abs;

import com.mason.game.ai.behavior_tree.plan_a.common.NodeStatus;
import com.mason.game.ai.behavior_tree.plan_a.ifs.IBehavior;

/**
 * Created by mwu on 2020/3/30
 * Ibehavior行为树基接口实现类
 */
public abstract class BaseBehavior implements IBehavior {

    protected NodeStatus status;

    protected BaseBehavior() {
        setStatus(NodeStatus.INITIAL);
    }

    /**
     * 方法封装
     * 防止直接调用update方法
     * 导致节点未初始化
     *
     * @return 节点更新后的状态
     */
    @Override
    public NodeStatus tick() {
        if (status != NodeStatus.RUNNING) {
            onInitialize();
        }

        status = update();

        // 如果更新后，节点不在运行
        // 则销毁
        if (status != NodeStatus.RUNNING) {
            onTerminate(status);
        }

        return status;
    }

    @Override
    public void abort() {
        onTerminate(NodeStatus.TERMINATED);
        setStatus(NodeStatus.TERMINATED);
    }

    @Override
    public NodeStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(NodeStatus status) {
        this.status = status;
    }
}
