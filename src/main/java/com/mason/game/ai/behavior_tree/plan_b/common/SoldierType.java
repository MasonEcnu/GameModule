package com.mason.game.ai.behavior_tree.plan_b.common;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by mwu on 2020/3/30
 */
public enum SoldierType {
    UNKNOWN(1),
    // 步兵
    INFANTRYMAN(1),
    // 骑兵
    CAVALARYMAN(2),
    // 枪兵
    PIKEMAN(3),
    // 弓兵
    ARCHER(4),
    ;

    private int type;

    SoldierType(int type) {
        this.type = type;
    }

    public SoldierType from(int type) {
        Optional<SoldierType> optional = Arrays.stream(values())
                .filter(soldierType -> soldierType.type == type).findFirst();
        return optional.orElse(SoldierType.UNKNOWN);
    }
}
