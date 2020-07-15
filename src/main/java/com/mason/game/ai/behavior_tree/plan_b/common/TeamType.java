package com.mason.game.ai.behavior_tree.plan_b.common;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by mwu on 2020/3/30
 */
public enum TeamType {
    ATTACKER(0),
    DEFENDER(1),

    GOD(-1);

    private int type;

    TeamType(int type) {
        this.type = type;
    }

    public TeamType from(int type) {
        Optional<TeamType> optional = Arrays.stream(values())
                .filter(soldierType -> soldierType.type == type).findFirst();
        return optional.orElse(TeamType.GOD);
    }

    /**
     * 获取敌对
     *
     * @return 敌对阵营
     */
    public TeamType hostile() {
        if (this == TeamType.ATTACKER) {
            return TeamType.DEFENDER;
        }
        if (this == TeamType.DEFENDER) {
            return TeamType.ATTACKER;
        }
        return TeamType.GOD;
    }
}
