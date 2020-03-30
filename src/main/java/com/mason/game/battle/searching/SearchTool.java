package com.mason.game.battle.searching;

import javafx.util.Pair;

/**
 * Created by mwu on 2020/3/30
 */
public class SearchTool {

    public static int calcDistance(Pair<Integer, Integer> from, Pair<Integer, Integer> to) {
        int xDiff = Math.abs(from.getKey() - to.getKey());
        int yDiff = Math.abs(from.getValue() - to.getValue());
        return xDiff * xDiff + yDiff * yDiff;
    }
}
