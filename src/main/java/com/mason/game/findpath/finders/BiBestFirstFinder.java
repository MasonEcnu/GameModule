package com.mason.game.findpath.finders;

import com.mason.game.findpath.core.FindPathOption;
import com.mason.game.findpath.core.Grid;
import com.mason.game.findpath.core.heuristic.HeuristicFunction;

import java.util.List;

/**
 * Created by mwu on 2020/3/10
 */
public class BiBestFirstFinder {

    private BiAStarFinder biAStarFinder;

    /**
     * Bi-direcitional Best-First-Search path-finder.
     *
     * @param opt {Object}           opt
     *            opt.allowDiagonal Whether diagonal movement is allowed.
     *            Deprecated, use diagonalMovement instead.
     *            opt.dontCrossCorners Disallow diagonal movement touching
     *            block corners. Deprecated, use diagonalMovement instead.
     *            opt.diagonalMovement Allowed diagonal movement.
     *            opt.heuristic Heuristic function to estimate the distance
     *            (defaults to manhattan).
     * @constructor
     */
    public BiBestFirstFinder(FindPathOption opt) {
        HeuristicFunction heuristic = opt.heuristic;
        opt.heuristic = (dx, dy) -> heuristic.calcDistance(dx, dy) * 1000000;
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
