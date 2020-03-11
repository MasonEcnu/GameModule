package com.mason.game.findpath.finders;

import com.mason.game.findpath.core.*;
import com.mason.game.findpath.core.heuristic.Heuristic;
import com.mason.game.findpath.core.heuristic.HeuristicFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by mwu on 2020/3/9
 * 双向A星算法
 */
public class BiAStarFinder {
    private boolean allowDiagonal;
    private boolean dontCrossCorners;
    private DiagonalMovement diagonalMovement;
    private HeuristicFunction heuristic;
    private int weight;

    private Queue<Node> startOpenList = new PriorityQueue<>(); // 优先队列(升序)
    private Queue<Node> endOpenList = new PriorityQueue<>(); // 优先队列(升序)

    /**
     * A* path-finder.
     * based upon https://github.com/bgrins/javascript-astar
     *
     * @param opt {Object}           opt
     *            opt.allowDiagonal Whether diagonal movement is allowed.
     *            Deprecated, use diagonalMovement instead.
     *            opt.dontCrossCorners Disallow diagonal movement touching
     *            block corners. Deprecated, use diagonalMovement instead.
     *            opt.diagonalMovement Allowed diagonal movement.
     *            opt.heuristic Heuristic function to estimate the distance
     *            (defaults to manhattan).
     *            opt.weight Weight to apply to the heuristic to allow for
     *            suboptimal paths, in order to speed up the search.
     * @constructor
     */
    public BiAStarFinder(FindPathOption opt) {
        this.allowDiagonal = opt.allowDiagonal;
        this.dontCrossCorners = opt.dontCrossCorners;
        this.heuristic = opt.heuristic;
        this.weight = opt.weight;
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
     * @return {Array<Array<number>>} The path, including both start and
     * end positions.
     */
    public List<List<Integer>> findPath(int startX, int startY, int endX, int endY, Grid grid) {
        Node startNode = grid.getNodeAt(startX, startY);
        Node endNode = grid.getNodeAt(endX, endY);

        HeuristicFunction heuristic = this.heuristic;
        DiagonalMovement diagonalMovement = this.diagonalMovement;
        int weight = this.weight;

        int x, y;
        // set the `g` and `f` value of the start node to be 0
        // and push it into the start open list
        startNode.g = 0;
        startNode.f = 0;
        startOpenList.add(startNode);
        startNode.opened = StartType.BY_START;

        // set the `g` and `f` value of the end node to be 0
        // and push it into the end open list
        endNode.g = 0;
        endNode.f = 0;
        endOpenList.add(endNode);
        endNode.opened = StartType.BY_END;

        // while both the open lists are not empty
        while (!startOpenList.isEmpty() && !endOpenList.isEmpty()) {
            // pop the position of start node which has the minimum `f` value.
            Node node = startOpenList.poll();
            node.closed = true;

            // get neigbours of the current node
            List<Node> neighbors = grid.getNeighbors(node, diagonalMovement);
            for (Node neighbor : neighbors) {
                if (TypeConversion.isBoolean(neighbor.closed) && (boolean) neighbor.closed) {
                    continue;
                }

                if (TypeConversion.isStartType(neighbor.opened) && neighbor.opened == StartType.BY_END) {
                    return Util.biBacktrace(node, neighbor);
                }

                x = neighbor.x;
                y = neighbor.y;

                // get the distance between current node and the neighbor
                // and calculate the next g score
                double ng = node.g + ((x - node.x == 0 || y - node.y == 0) ? 1 : Math.sqrt(2));

                // check if the neighbor has not been inspected yet, or
                // can be reached with smaller cost from the current node
                if ((TypeConversion.isBoolean(neighbor.opened) && !(boolean) neighbor.opened) || ng < neighbor.g) {
                    neighbor.g = ng;
                    if (neighbor.h <= 0) {
                        neighbor.h = weight * heuristic.calcDistance(Math.abs(x - endX), Math.abs(y - endY));
                    }
                    neighbor.f = neighbor.g + neighbor.h;
                    neighbor.parent = node;

                    if (TypeConversion.isBoolean(neighbor.opened) && !(boolean) neighbor.opened) {
                        startOpenList.add(neighbor);
                        neighbor.opened = StartType.BY_START;
                    } else {
                        // the neighbor can be reached with smaller cost.
                        // Since its f value has been updated, we have to
                        // update its position in the open list
                        startOpenList.removeIf(temp -> temp.equals(neighbor));
                        startOpenList.add(neighbor);
                    }
                }
            }// end for each neighbor

            // pop the position of end node which has the minimum `f` value.
            node = endOpenList.poll();
            if (node == null) {
                continue;
            }
            node.closed = true;

            // get neigbours of the current node
            neighbors = grid.getNeighbors(node, diagonalMovement);
            for (Node neighbor : neighbors) {
                if (TypeConversion.isBoolean(neighbor.closed) && (boolean) neighbor.closed) {
                    continue;
                }
                if (TypeConversion.isStartType(neighbor.opened) && neighbor.opened == StartType.BY_START) {
                    return Util.biBacktrace(neighbor, node);
                }

                x = neighbor.x;
                y = neighbor.y;

                // get the distance between current node and the neighbor
                // and calculate the next g score
                double ng = node.g + ((x - node.x == 0 || y - node.y == 0) ? 1 : Math.sqrt(2));
                // check if the neighbor has not been inspected yet, or
                // can be reached with smaller cost from the current node
                if (TypeConversion.isBoolean(neighbor.opened) && !(boolean) neighbor.opened || ng < neighbor.g) {
                    neighbor.g = ng;
                    if (neighbor.h <= 0) {
                        neighbor.h = weight * heuristic.calcDistance(Math.abs(x - endX), Math.abs(y - endY));
                    }
                    neighbor.f = neighbor.g + neighbor.h;
                    neighbor.parent = node;

                    if (TypeConversion.isBoolean(neighbor.opened) && !(boolean) neighbor.opened) {
                        endOpenList.add(neighbor);
                        neighbor.opened = StartType.BY_END;
                    } else {
                        // the neighbor can be reached with smaller cost.
                        // Since its f value has been updated, we have to
                        // update its position in the open list
                        endOpenList.removeIf(temp -> temp.equals(neighbor));
                        endOpenList.add(neighbor);
                    }
                }
            } // end for each neighbor
        } // end while not open list empty

        return new ArrayList<>();
    }
}
