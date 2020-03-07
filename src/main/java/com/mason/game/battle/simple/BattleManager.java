package com.mason.game.battle.simple;

import com.mason.game.battle.simple.monster.Monster;

import java.util.*;

/**
 * Created by mwu on 2019/12/23
 * 规则
 * 多个monsters互相攻击
 * 每秒攻击一次
 * 攻击对象从所有的monsters中随机算则
 * 两个monster谁先攻击由速度决定
 * 每个monster有不同的职业
 * 不同职业的初始属性和技能不同
 * 技能有不同的效果
 * <p>
 * 结束条件
 * 直到剩最后一个monster结束
 */
public class BattleManager {

    private List<Monster> monsters;
    private int maxMonsterId = 0;
    private Timer timer = new Timer();
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            checkMonsterSkillCD();
            System.out.println("BattleManager--check monsters:" + monsters.size());
            checkAndRemoveMonsters();
            randomChoose2Battle();
            checkAndRemoveMonsters();
        }
    };

    BattleManager(List<Monster> monsters) {
        this.monsters = monsters;
        checkMaxMonsterId();
        monsters.forEach(System.out::println);
    }

    private void randomChoose2Battle() {
        Collections.shuffle(monsters);
        if (monsters.size() <= 1) {
            if (monsters.size() == 0) {
                System.out.println("Battle game finished: no one wins.");
                System.exit(0);
            } else {
                Monster finalMonster = monsters.get(0);
                System.out.println("Battle game finished:" + finalMonster.toString());
                System.exit(0);
            }
        }

        Monster attacker = monsters.get(0);
        Monster defender = monsters.get(1);
        attacker.attack(defender);
    }

    private void checkMaxMonsterId() {
        Optional<Monster> result = monsters.stream().max(Comparator.comparingInt(Monster::getMonsterId));
        result.ifPresent(monster -> maxMonsterId = monster.getMonsterId());
    }

    void startGame() {
        startTimer();
    }

    private void startTimer() {
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    private void checkAndRemoveMonsters() {
        List<Monster> needToRemove = new ArrayList<>();
        monsters.forEach(monster -> {
                    if (monster.isDead()) {
                        needToRemove.add(monster);
                    }
                }
        );
        needToRemove.forEach(this::removeMonster);
    }

    public void addMonster() {
        Monster monster = new Monster(nextMonsterId());
        addMonster(monster);
    }

    private void addMonster(Monster monster) {
        monsters.remove(monster);
        monsters.add(monster);
        checkMaxMonsterId();
        System.out.format("Add new monster:%s\n", monster);
    }

    private int nextMonsterId() {
        maxMonsterId += 1;
        return maxMonsterId;
    }

    private void checkMonsterSkillCD() {
        monsters.forEach(monster -> monster.calcSkillCD(1));
    }

    private void removeMonster(Monster monster) {
        monsters.remove(monster);
    }
}
