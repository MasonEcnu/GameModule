package com.mason.game.ai.behavior_tree.plan_b.common;

/**
 * Created by mwu on 2020/3/30
 */
public enum UnitType {
    // 指挥官
    COMMANDER(0),
    // 士兵
    SOLDIER(1),
    // 塔
    TURRET(2),
    // 怪物
    MONSTER(3),
    // 器械
    APPARATUS(4),
    // 城墙
    WALL(5),
    // 城墙受击点
    WALL_POINT(6),
    // 范围技能
    AREA_UNIT(7),
    //上帝
    GOD(999);

    UnitType(int type) {

    }
}
