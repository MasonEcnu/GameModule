package com.mason.game.ai.behavior_tree.plan_b.entity;

import com.mason.game.ai.behavior_tree.plan_b.behavior.BehaviorTree;
import com.mason.game.ai.behavior_tree.plan_b.common.TeamType;
import com.mason.game.ai.behavior_tree.plan_b.common.UnitState;
import com.mason.game.ai.behavior_tree.plan_b.common.UnitType;

/**
 * Created by mwu on 2020/3/30
 */
public abstract class BattleUnit {
    public int unitId;
    public UnitType unitType;
    public TeamType teamType;

    public BattleUnit(int unitId, UnitType unitType, TeamType teamType) {
        this.unitId = unitId;
        this.unitType = unitType;
        this.teamType = teamType;
    }

    // 索敌目标
    BattleUnit lockTarget = null;

    // 移动目标
    BattleUnit moveTarget = null;

    // 单位状态
    UnitState state = UnitState.IDLE;

    // 行为树
    public BehaviorTree behaviorTree = null;

    public void initBehaviorTree() {
        behaviorTree = new BehaviorTree();
    }

    abstract public void update();
}
