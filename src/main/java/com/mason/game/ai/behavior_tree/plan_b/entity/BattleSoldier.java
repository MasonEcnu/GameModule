package com.mason.game.ai.behavior_tree.plan_b.entity;

import com.mason.game.ai.behavior_tree.plan_b.common.TeamType;
import com.mason.game.ai.behavior_tree.plan_b.common.UnitType;
import com.mason.game.ai.behavior_tree.plan_b.entity.BattleUnit;

/**
 * Created by mwu on 2020/3/30
 */
public abstract class BattleSoldier extends BattleUnit {

    // 第几排--从左往右看
    // column相同表示兵种相同
    public int column = 0;
    // 第几列
    // 方阵从上往下看--第几行
    // row相同表示正对面
    public int row = 0;

    public BattleSoldier(int unitId, UnitType unitType, TeamType teamType) {
        super(unitId, unitType, teamType);
    }
}
