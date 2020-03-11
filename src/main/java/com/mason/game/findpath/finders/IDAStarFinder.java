package com.mason.game.findpath.finders;

import com.mason.game.findpath.Constants;
import com.mason.game.findpath.core.*;
import com.mason.game.findpath.core.heuristic.Heuristic;
import com.mason.game.findpath.core.heuristic.HeuristicFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mwu on 2020/3/10
 */
public class IDAStarFinder {
    private boolean allowDiagonal;
    private boolean dontCrossCorners;
    private DiagonalMovement diagonalMovement;
    private HeuristicFunction heuristic;
    private int weight;
    private boolean trackRecursion;
    private int timeLimit;  // 单位：秒

    /**
     * Iterative Deeping A Star (IDA*) path-finder.
     * <p>
     * Recursion based on:
     * http://www.apl.jhu.edu/~hall/AI-Programming/IDA-Star.html
     * <p>
     * Path retracing based on:
     * V. Nageshwara Rao, Vipin Kumar and K. Ramesh
     * "A Parallel Implementation of Iterative-Deeping-A*", January 1987.
     * ftp://ftp.cs.utexas.edu/.snapshot/hourly.1/pub/AI-Lab/tech-reports/UT-AI-TR-87-46.pdf
     *
     * @param opt                {FindPathOption}           opt
     * @param {boolean}          opt.allowDiagonal Whether diagonal movement is allowed.
     *                           Deprecated, use diagonalMovement instead.
     * @param {boolean}          opt.dontCrossCorners Disallow diagonal movement touching
     *                           block corners. Deprecated, use diagonalMovement instead.
     * @param {DiagonalMovement} opt.diagonalMovement Allowed diagonal movement.
     * @param {function}         opt.heuristic Heuristic function to estimate the distance
     *                           (defaults to manhattan).
     * @param {number}           opt.weight Weight to apply to the heuristic to allow for
     *                           suboptimal paths, in order to speed up the search.
     * @param {boolean}          opt.trackRecursion Whether to track recursion for
     *                           statistical purposes.
     * @param {number}           opt.timeLimit Maximum execution time. Use <= 0 for infinite.
     * @author Gerard Meier (www.gerardmeier.com)
     * @constructor
     */
    public IDAStarFinder(FindPathOption opt) {
        this.allowDiagonal = opt.allowDiagonal;
        this.dontCrossCorners = opt.dontCrossCorners;
        this.heuristic = opt.heuristic;
        this.weight = opt.weight;
        this.diagonalMovement = opt.diagonalMovement;
        this.trackRecursion = opt.trackRecursion;
        this.timeLimit = opt.timeLimit;

        if (this.diagonalMovement != null) {
            if (!this.allowDiagonal) {
                this.diagonalMovement = DiagonalMovement.Never;
            } else {
                if (this.dontCrossCorners) {
                    this.diagonalMovement = DiagonalMovement.OnlyWhenNoObstacles;
                } else {
                    this.diagonalMovement = DiagonalMovement.IfAtMostOneObstacle;
                }
            }
        }

        // When diagonal movement is allowed the manhattan heuristic is not
        //admissible. It should be octile instead
        if (this.diagonalMovement == DiagonalMovement.Never) {
            if (opt.heuristic == null) {
                this.heuristic = Heuristic.manhattan;
            }
        } else {
            if (opt.heuristic == null) {
                this.heuristic = Heuristic.octile;
            }
        }
    }

    // Used for statistics:
    // 好像并没有用到
    private int nodesVisited = 0;

    /**
     * Find and return the the path. When an empty array is returned, either
     * no path is possible, or the maximum execution time is reached.
     *
     * @return {Array<Array<number>>} The path, including both start and
     * end positions.
     */
    public List<List<Integer>> findPath(int startX, int startY, int endX, int endY, Grid grid) {
        // Execution time limitation:
        long startTime = System.currentTimeMillis();
        Node startNode = grid.getNodeAt(startX, startY);
        Node endNode = grid.getNodeAt(endX, endY);

        // Initial search depth, given the typical heuristic contraints,
        // there should be no cheaper route possible.
        double cutOff = heuristic.calcDistance(Math.abs(endNode.x - startNode.x), Math.abs(endNode.y - startNode.y));
        List<List<Integer>> route;
        while (true) {
            route = new ArrayList<>();
            // Search till cut-off depth:
            Object t = search(grid, startNode, endNode, 0, cutOff, route, 0, startTime);

            // Route not possible, or not found in time limit.
            if (TypeConversion.isInt(t) && (int) t == Constants.INFINITY) {
                return new ArrayList<>();
            }

            // If t is a node, it's also the end node. Route is now
            // populated with a valid path to the end node.
            if (TypeConversion.isNode(t)) {
                return route;
            }

            // Try again, this time with a deeper cut-off. The t score
            // is the closest we got to the end node.
            if (TypeConversion.isDouble(t)) {
                cutOff = (double) t;
            }
        }
    }

    private Object search(
            Grid grid,
            Node node,
            Node endNode,
            double g,
            double cutoff,
            List<List<Integer>> route,
            int depth,
            long startTime
    ) {
        nodesVisited++;

        // Enforce timeLimit:
        long currTime = System.currentTimeMillis();
        if (this.timeLimit > 0 && currTime - startTime > this.timeLimit * 1000) {
            // Enforced as "path-not-found".
            // 超时了，未找到路径
            return Constants.INFINITY;
        }

        double h = heuristic.calcDistance(Math.abs(endNode.x - node.x), Math.abs(endNode.y - node.y));
        double f = g + h * this.weight;

        // We've searched too deep for this iteration.
        if (f > cutoff) {
            return f;
        }

        if (node == endNode) {
            route.add(Util.packageList(node));
            return node;
        }

        List<Node> neighbours = grid.getNeighbors(node, this.diagonalMovement);
        // Sort the neighbours, gives nicer paths. But, this deviates
        // from the original algorithm - so I left it out.
        //neighbours.sort(function(a, b){
        //    return h(a, end) - h(b, end);
        //});

        /*jshint -W084 *///Disable warning: Expected a conditional expression and instead saw an assignment
        double min = Constants.INFINITY;
        for (int k = 0; k < neighbours.size(); ++k) {
            Node neighbour = neighbours.get(k);

            /*jshint +W084 *///Enable warning: Expected a conditional expression and instead saw an assignment
            if (this.trackRecursion) {
                // Retain a copy for visualisation. Due to recursion, this
                // node may be part of other paths too.
                neighbour.retainCount = neighbour.retainCount + 1 | 1;

                if (!neighbour.tested) {
                    neighbour.tested = true;
                }
            }

            double cost = (node.x == neighbour.x || node.y == neighbour.y) ? 1 : Math.sqrt(2);
            Object t = search(grid, neighbour, endNode, g + cost, cutoff, route, depth + 1, startTime);
            if (TypeConversion.isNode(t)) {
                route.add(Util.packageList(node));
                // For a typical A* linked list, this would work:
                // neighbour.parent = node;
                return t;
            }

            // Decrement count, then determine whether it's actually closed.
            if (this.trackRecursion && (--neighbour.retainCount) == 0) {
                neighbour.tested = false;
            }

            if (TypeConversion.isDouble(t) && (double) t < min) {
                min = (double) t;
            }
        }
        return min;
    }
}
