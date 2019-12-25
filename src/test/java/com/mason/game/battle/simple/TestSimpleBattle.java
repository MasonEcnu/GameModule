package com.mason.game.battle.simple;

import com.mason.game.battle.simple.monster.Monster;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mwu on 2019/12/25
 */
public class TestSimpleBattle {
  public static void main(String[] args) {
    List<Monster> monsters = new ArrayList<>();
    for (int i = 1; i <= 2; i++) {
      monsters.add(new Monster(i));
    }
    new BattleManager(monsters).startGame();
  }
}
