package com.mason.game.findpath.finders;

import com.mason.game.findpath.core.*;
import com.mason.game.findpath.core.heuristic.Heuristic;
import com.mason.game.findpath.core.heuristic.HeuristicFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by mwu on 2020/3/10
 * 感觉和A星没啥区别啊
 */
public class TraceFinder {
    private boolean allowDiagonal;
    private boolean dontCrossCorners;
    private DiagonalMovement diagonalMovement;
    private HeuristicFunction heuristic;

    private Queue<Node> openList = new PriorityQueue<>(); // 优先队列(升序)

    /**
     * A* path-finder.
     * based upon https://github.com/bgrins/javascript-astar
     *
     * @param opt {FindPathOption}   opt
     *            opt.allowDiagonal Whether diagonal movement is allowed.
     *            opt.dontCrossCorners Disallow diagonal movement touching block corners.
     *            opt.heuristic Heuristic function to estimate the distance
     *            (defaults to manhattan).
     *            opt.weight Weight to apply to the heuristic to allow for suboptimal paths,
     *            in order to speed up the search.
     * @constructor
     */
    public TraceFinder(FindPathOption opt) {
        this.allowDiagonal = opt.allowDiagonal;
        this.dontCrossCorners = opt.dontCrossCorners;
        this.heuristic = opt.heuristic;
        this.diagonalMovement = opt.diagonalMovement;

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

    /**
     * Find and return the the path.
     *
     * @return {Array.<[number, number]>} The path, including both start and
     * end positions.
     */
    public List<List<Integer>> findPath(int startX, int startY, int endX, int endY, Grid grid) {
        Node startNode = grid.getNodeAt(startX, startY);
        Node endNode = grid.getNodeAt(endX, endY);

        HeuristicFunction heuristic = this.heuristic;
        int x, y;
        // set the `g` and `f` value of the start node to be 0
        startNode.g = 0;
        startNode.f = 0;

        // push the start node into the open list
        openList.add(startNode);
        startNode.opened = true;

        // while the open list is not empty
        while (!openList.isEmpty()) {
            // pop the position of node which has the minimum `f` value.
            Node node = openList.poll();
            node.closed = true;

            // if reached the end position, construct the path and return it
            if (node == endNode) {
                return Util.backtrace(endNode);
            }

            // get neigbours of the current node
            List<Node> neighbors = grid.getNeighbors(node, diagonalMovement);
            int ar = neighbors.size();

            for (Node neighbor : neighbors) {
                if (TypeConversion.isBoolean(neighbor.closed) && (boolean) neighbor.closed) {
                    continue;
                }

                x = neighbor.x;
                y = neighbor.y;

                // get the distance between current node and the neighbor
                // and calculate the next g score
                double ng = node.g + ((x - node.x == 0 || y - node.y == 0) ? 1 : Math.sqrt(2));

                // check if the neighbor has not been inspected yet, or
                // can be reached with smaller cost from the current node
                if (TypeConversion.isBoolean(neighbor.opened)) {
                    if (!(boolean) neighbor.opened || ng < neighbor.g) {
                        neighbor.g = ng * ar / 9; //the trace magic
                        if (neighbor.h <= 0) {
                            neighbor.h = heuristic.calcDistance(Math.abs(x - endX), Math.abs(y - endY));
                        }
                        neighbor.f = neighbor.g + neighbor.h;
                        neighbor.parent = node;
                    }

                    if (!(boolean) neighbor.opened) {
                        openList.add(neighbor);
                        neighbor.opened = true;
                    } else {
                        // the neighbor can be reached with smaller cost.
                        // Since its f value has been updated, we have to
                        // update its position in the open list
                        openList.removeIf(temp -> temp.equals(neighbor));
                        openList.add(neighbor);
                    }
                }
            }// end for each neighbor
        }// end while not open list empty

        return new ArrayList<>();
    }
}
