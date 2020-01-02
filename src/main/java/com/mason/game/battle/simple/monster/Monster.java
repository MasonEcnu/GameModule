package com.mason.game.battle.simple.monster;

import com.alibaba.fastjson.JSON;
import com.mason.game.battle.simple.career.Career;
import com.mason.game.battle.simple.constants.BattleConstants;
import com.mason.game.battle.simple.skill.SkillManager;
import com.mason.game.battle.simple.skill.SkillType;
import com.mason.game.constans.Pair;
import com.mason.game.utils.CalcUtil;
import com.mason.game.utils.RandomUtil;

import java.util.Collections;
import java.util.List;

/**
 * Created by mwu on 2019/12/23
 */
public class Monster {

  private int monsterId;  // 怪物编号
  private String name;  // 怪物名称
  private int hp;
  private int speed;
  private Pair<Integer> attackRange;
  private int skillCD;
  private Career career;
  private List<SkillType> skills;

  private SkillManager skillManager = SkillManager.getInstance();

  public Monster(int monsterId) {
    this.monsterId = monsterId;
    this.name = "Monster" + monsterId;
    initMonsterInfo();
  }

  private void initMonsterInfo() {
    List<Career> careers = Career.getCareers();
    Collections.shuffle(careers);
    career = careers.get(0);
    skills = skillManager.CAREER_SKILL_MAP.get(career);
    switch (career) {
      case WARRIOR:
        skillCD = BattleConstants.WARRIOR_SKILL_CD;
        hp = RandomUtil.between(BattleConstants.WARRIOR_MAX_HP_RANGE);
        speed = RandomUtil.between(BattleConstants.WARRIOR_SPEED_RANGE);
        attackRange = BattleConstants.WARRIOR_ATTACK_RANGE;
        break;
      case MAGICIAN:
        skillCD = BattleConstants.MAGICIAN_SKILL_CD;
        hp = RandomUtil.between(BattleConstants.MAGICIAN_MAX_HP_RANGE);
        speed = RandomUtil.between(BattleConstants.MAGICIAN_SPEED_RANGE);
        attackRange = BattleConstants.MAGICIAN_ATTACK_RANGE;
        break;
      case PRIEST:
        skillCD = BattleConstants.PRIEST_SKILL_CD;
        hp = RandomUtil.between(BattleConstants.PRIEST_MAX_HP_RANGE);
        speed = RandomUtil.between(BattleConstants.PRIEST_SPEED_RANGE);
        attackRange = BattleConstants.PRIEST_ATTACK_RANGE;
        break;
    }
  }

  /**
   * this attack defender
   *
   * @param defender 另一个敌人
   */
  public void attack(Monster defender) {
    Monster attacker = this;
    System.out.println("Battle start:");
    System.out.println("Attacker:" + attacker);
    System.out.println("Defender:" + defender);

    if (attacker.isDead() || defender.isDead()) return;
    // 计算攻击力
    int attackerAttackPot = RandomUtil.between(attacker.getAttackRange());
    int defenderAttackPot = RandomUtil.between(defender.getAttackRange());
    // 随机技能
    SkillType attackerSkill = randomChooseSkill(attacker);
    SkillType defenderSkill = randomChooseSkill(defender);
    // 谁先手
    int attackerSpeed = attacker.getSpeed();
    int defenderSpeed = defender.getSpeed();
    boolean attackFirst = attackerSpeed >= defenderSpeed;
    System.out.println("Battle info:");
    System.out.println(attackFirst);
    System.out.format("attackerSpeed:%s, attackerAttackPot:%s, attackerSkill:%s\n", attackerSpeed, attackerAttackPot, attackerSkill);
    System.out.format("defenderSpeed:%s, defenderAttackPot:%s, defenderSkill:%s\n", defenderSpeed, defenderAttackPot, defenderSkill);
    if (attackFirst) {
      // 进攻者先手
      attack(defender, attackerAttackPot, defenderAttackPot, attackerSkill, defenderSkill);
    } else {
      // 防守者先手
      attack(attacker, defenderAttackPot, attackerAttackPot, defenderSkill, attackerSkill);
    }

    System.out.println("Battle finished:");
    System.out.println("Attacker:" + attacker);
    System.out.println("Defender:" + defender);
  }

  private void attack(Monster defender, int attackerAttackPot, int defenderAttackPot, SkillType attackerSkill, SkillType defenderSkill) {
    // 如果进攻者随机出的是防守技能，则啥也不干
    // 如果不是，则计算最终攻击力
    if (!attackerSkill.isDefense()) {
      int attackerFinalAttackPot = CalcUtil.multiplyFloor(attackerAttackPot, defenderSkill.getRate());
      if (defenderSkill.isDefense()) {
        attackerFinalAttackPot -= CalcUtil.multiplyFloor(defenderAttackPot, attackerSkill.getRate());
      }
      if (attackerFinalAttackPot > 0) {
        defender.attacked(attackerFinalAttackPot);
      }
    }
  }

  private void attacked(int attackPot) {
    this.hp -= attackPot;
  }

  private SkillType randomChooseSkill(Monster attacker) {
    if (attacker.couldEnchant()) {
      List<SkillType> temp = attacker.getSkills();
      Collections.shuffle(temp);
      return temp.get(0);
    } else {
      return SkillType.NORMAL;
    }
  }

  public boolean isDead() {
    return hp <= 0;
  }

  public void calcSkillCD(int diff) {
    skillCD -= diff;
    if (skillCD < 0) {
      switch (career) {
        case WARRIOR:
          skillCD = BattleConstants.WARRIOR_SKILL_CD;
          break;
        case MAGICIAN:
          skillCD = BattleConstants.MAGICIAN_SKILL_CD;
          break;
        case PRIEST:
          skillCD = BattleConstants.PRIEST_SKILL_CD;
          break;
      }
    }
  }

  private int getSpeed() {
    return RandomUtil.random(speed);
  }

  private Pair<Integer> getAttackRange() {
    return attackRange;
  }

  public int getSkillCD() {
    return skillCD;
  }

  public Career getCareer() {
    return career;
  }

  private List<SkillType> getSkills() {
    return skills;
  }

  public int getMonsterId() {
    return monsterId;
  }

  private boolean couldEnchant() {
    return skillCD == 0;
  }

  @Override
  public String toString() {
    return String.format("Monster[name:%s, hp:%s, career:%s, speed:%s, cd:%s, skills:%s]",
        this.name, this.hp, this.career, this.speed, this.skillCD, JSON.toJSONString(this.skills)
    );
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
