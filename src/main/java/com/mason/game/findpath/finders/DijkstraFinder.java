package com.mason.game.findpath.finders;

import com.mason.game.findpath.core.FindPathOption;
import com.mason.game.findpath.core.Grid;

import java.util.List;

/**
 * Created by mwu on 2020/3/10
 */
public class DijkstraFinder {

    private AStarFinder aStarFinder;

    /**
     * Dijkstra path-finder.
     *
     * @param opt {FindPathOption}           opt
     *            opt.allowDiagonal Whether diagonal movement is allowed.
     *            Deprecated, use diagonalMovement instead.
     *            opt.dontCrossCorners Disallow diagonal movement touching
     *            block corners. Deprecated, use diagonalMovement instead.
     *            opt.diagonalMovement Allowed diagonal movement.
     * @constructor
     */
    public DijkstraFinder(FindPathOption opt) {
        opt.heuristic = (dx, dy) -> 0;
        aStarFinder = new AStarFinder(opt);
    }

    /**
     * Find and return the the path.
     *
     * @return {Array<Array<number>>} The path, including both start and
     * end positions.
     */
    public List<List<Integer>> findPath(int startX, int startY, int endX, int endY, Grid grid) {
        return aStarFinder.findPath(startX, startY, endX, endY, grid);
    }
}
