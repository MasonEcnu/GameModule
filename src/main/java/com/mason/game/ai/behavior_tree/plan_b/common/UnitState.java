package com.mason.game.ai.behavior_tree.plan_b.common;

/**
 * Created by mwu on 2020/3/30
 */
public enum UnitState {
    IDLE, //待机
    MOVING, //移动
    ATTACKING, //攻击
    SHOOTING,  //射击
    CAST_SKILL,//释放技能
    DEAD, //死亡
    SEARCHING //搜索
}
