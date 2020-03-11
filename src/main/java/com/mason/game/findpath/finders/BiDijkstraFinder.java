package com.mason.game.findpath.finders;

import com.mason.game.findpath.core.FindPathOption;
import com.mason.game.findpath.core.Grid;

import java.util.List;

/**
 * Created by mwu on 2020/3/11
 */
public class BiDijkstraFinder {

    private BiAStarFinder biAStarFinder;

    public BiDijkstraFinder(FindPathOption opt) {
        opt.heuristic = (dx, dy) -> 0;
        biAStarFinder = new BiAStarFinder(opt);
    }

    /**
     * Find and return the the path.
     *
     * @return {Array<Array<number>>} The path, including both start and
     * end positions.
     */
    public List<List<Integer>> findPath(int startX, int startY, int endX, int endY, Grid grid) {
        return biAStarFinder.findPath(startX, startY, endX, endY, grid);
    }
}
