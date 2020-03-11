package com.mason.game.findpath.finders;

import com.mason.game.findpath.core.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by mwu on 2020/3/11
 */
public class BiBreadthFirstFinder {

    private boolean allowDiagonal;
    private boolean dontCrossCorners;
    private DiagonalMovement diagonalMovement;

    /**
     * Bi-directional Breadth-First-Search path finder.
     *
     * @param opt {FindPathOption}           opt
     *            opt.allowDiagonal Whether diagonal movement is allowed.
     *            Deprecated, use diagonalMovement instead.
     *            opt.dontCrossCorners Disallow diagonal movement touching
     *            block corners. Deprecated, use diagonalMovement instead.
     *            opt.diagonalMovement Allowed diagonal movement.
     * @constructor
     */
    public BiBreadthFirstFinder(FindPathOption opt) {
        this.allowDiagonal = opt.allowDiagonal;
        this.dontCrossCorners = opt.dontCrossCorners;
        this.diagonalMovement = opt.diagonalMovement;

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

    /**
     * Find and return the the path.
     *
     * @return {Array<Array<number>>} The path, including both start and
     * end positions.
     */
    public List<List<Integer>> findPath(int startX, int startY, int endX, int endY, Grid grid) {
        Node startNode = grid.getNodeAt(startX, startY);
        Node endNode = grid.getNodeAt(endX, endY);

        Queue<Node> startOpenList = new LinkedList<>(); // 普通队列
        Queue<Node> endOpenList = new LinkedList<>(); // 普通队列
        DiagonalMovement diagonalMovement = this.diagonalMovement;

        startOpenList.add(startNode);
        startNode.opened = true;
        startNode.startType = StartType.BY_START;

        endOpenList.add(endNode);
        endNode.opened = true;
        endNode.startType = StartType.BY_END;

        while (!startOpenList.isEmpty() && !endOpenList.isEmpty()) {
            Node node = startOpenList.poll();
            node.closed = true;

            // get neigbours of the current node
            List<Node> neighbors = grid.getNeighbors(node, diagonalMovement);
            for (Node neighbor : neighbors) {
                if (TypeConversion.isBoolean(neighbor.closed) && (boolean) neighbor.closed) {
                    continue;
                }

                if (TypeConversion.isBoolean(neighbor.opened) && (boolean) neighbor.opened) {
                    // if this node has been inspected by the reversed search,
                    // then a path is found.
                    if (neighbor.startType == StartType.BY_END) {
                        return Util.biBacktrace(node, neighbor);
                    }
                    continue;
                }
                startOpenList.offer(neighbor);
                neighbor.parent = node;
                neighbor.opened = true;
                neighbor.startType = StartType.BY_START;
            }

            // expand end open list
            node = endOpenList.poll();
            if (node == null) {
                continue;
            }
            node.closed = true;
            neighbors = grid.getNeighbors(node, diagonalMovement);
            for (Node neighbor : neighbors) {
                if (TypeConversion.isBoolean(neighbor.closed) && (boolean) neighbor.closed) {
                    continue;
                }

                if (TypeConversion.isBoolean(neighbor.opened) && (boolean) neighbor.opened) {
                    if (neighbor.startType == StartType.BY_START) {
                        return Util.biBacktrace(neighbor, node);
                    }
                    continue;
                }
                endOpenList.offer(neighbor);
                neighbor.parent = node;
                neighbor.opened = true;
                neighbor.startType = StartType.BY_END;
            }
        }
        return new ArrayList<>();
    }
}
