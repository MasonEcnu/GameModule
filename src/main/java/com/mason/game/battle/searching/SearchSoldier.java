package com.mason.game.battle.searching;

import javafx.util.Pair;

/**
 * Created by mwu on 2020/3/30
 * 士兵
 */
class SearchSoldier {
    // 第几排，同兵种
    int lineNum;
    // 第几列
    int row;
    // 位置
    Pair<Integer, Integer> position;
    // 是否已死
    boolean isDead;
}
