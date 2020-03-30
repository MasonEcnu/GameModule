package com.mason.game.battle.searching;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by mwu on 2020/3/30
 * 索敌
 * 优先同一排的，相同兵种
 * 优先排数比自己小的，即靠近自己的
 * 优先同列的，即正对自己，然后往两边延伸
 */
public class SearchDemo {

    private static Random random = new Random();

    public static void main(String[] args) {
        int MAX_LINE = 4;
        int MAX_ROW = 10;
        List<SearchSoldier> soldierList = new ArrayList<>();
        // 一排10个，共4排
        for (int i = 0; i < MAX_LINE; i++) {
            for (int j = 0; j < MAX_ROW; j++) {
                SearchSoldier soldier = new SearchSoldier();
                soldier.lineNum = i;
                soldier.row = j;
                soldier.position = new Pair<>(i, j);
                soldier.isDead = random.nextBoolean();
                soldierList.add(soldier);
                if (j == MAX_ROW - 1) {
                    System.out.format("(%s, %s, %5s)", i, j, soldier.isDead);
                } else {
                    System.out.format("(%s, %s, %5s) |", i, j, soldier.isDead);
                }
            }
            System.out.println();
        }
        SearchSoldier finder = new SearchSoldier();
        finder.lineNum = random.nextInt(MAX_LINE);
        finder.row = random.nextInt(MAX_ROW);
        finder.position = new Pair<>(finder.lineNum, finder.row);
        System.out.format("我自己的位置：(%s, %s)\n", finder.lineNum, finder.row);

        SearchSoldier target = null;
        int lastDist = Integer.MAX_VALUE;
        for (SearchSoldier searchSoldier : soldierList) {
            if (searchSoldier.isDead) continue;
            if (target == null || searchSoldier.lineNum < target.lineNum) {
                target = searchSoldier;
                lastDist = SearchTool.calcDistance(finder.position, searchSoldier.position);
            } else if (searchSoldier.lineNum == target.lineNum && target.row != finder.row) {
                if (finder.row == searchSoldier.row) {
                    target = searchSoldier;
                } else {
                    int currDist = SearchTool.calcDistance(finder.position, searchSoldier.position);
                    if (currDist < lastDist) {
                        target = searchSoldier;
                        lastDist = currDist;
                    }
                }
            }
            System.out.format("目标寻找中：(%s, %s)\n", target.lineNum, target.row);
        }

        if (target != null) {
            System.out.format("找到目标：(%s, %s)\n", target.lineNum, target.row);
        } else {
            System.out.println("未找到目标");
        }
    }
}
