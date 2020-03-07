package com.mason.game.battle.simple.skill;

/**
 * Created by mwu on 2019/12/23
 * 技能类型
 * rate:基础攻击倍率
 * 防御类技能则是抵消
 */
public enum SkillType {
    NORMAL(1f, false), // 普通攻击

    // 战士技能
    THUMP(1.2f, false),  // 重击
    CRITICAL(2f, false), // 暴击
    BLOCK(1.5f, true),  // 格挡

    // 法师技能
    MANA_SHIELD(1.5f, true),  // 魔法盾
    FIRE_BALL(1.8f, false), // 火球
    SNOWSTORM(2.2f, false),  // 暴风雪

    // 牧师技能
    TURNUNDEPOST(1.5f, false),  // 圣言术
    HOLY_SHOCK(2f, false), // 圣击
    MANTRA_SHIELD(1.5f, true);  // 真言盾

    private float rate;
    private boolean isDefense;

    SkillType(float rate, boolean isDefense) {
        this.rate = rate;
        this.isDefense = isDefense;
    }

    public float getRate() {
        return rate;
    }

    public boolean isDefense() {
        return isDefense;
    }


    @Override
    public String toString() {
        return String.format("[%s, %s, %s]", this.name(), this.rate, this.isDefense);
    }}
