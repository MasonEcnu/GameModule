package com.mason.game.findpath.core.heuristic;

/**
 * Created by mwu on 2020/3/6
 */
@FunctionalInterface
public interface HeuristicFunction {
    double calcDistance(int dx, int dy);
}
