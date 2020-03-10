package com.mason.game.findpath.finders;

import com.mason.game.findpath.core.FindPathOption;
import com.mason.game.findpath.core.Grid;
import com.mason.game.findpath.core.heuristic.HeuristicFunction;

import java.util.List;

/**
 * Created by mwu on 2020/3/10
 */
public class BestFirstFinder {

    private AStarFinder aStarFinder;

    /**
     * Best-First-Search path-finder.
     *
     * @param opt                {FindPathOption}           opt
     * @param {boolean}          opt.allowDiagonal Whether diagonal movement is allowed.
     *                           Deprecated, use diagonalMovement instead.
     * @param {boolean}          opt.dontCrossCorners Disallow diagonal movement touching
     *                           block corners. Deprecated, use diagonalMovement instead.
     * @param {DiagonalMovement} opt.diagonalMovement Allowed diagonal movement.
     * @param {function}         opt.heuristic Heuristic function to estimate the distance
     *                           (defaults to manhattan).
     * @constructor
     */
    public BestFirstFinder(FindPathOption opt) {
        HeuristicFunction heuristic = opt.heuristic;
        opt.heuristic = (dx, dy) -> heuristic.calcDistance(dx, dy) * 1000000;
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
