package com.mason.game.findpath.finders;

import com.mason.game.findpath.core.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by mwu on 2020/3/11
 */
public class BreadthFirstFinder {

    private boolean allowDiagonal;
    private boolean dontCrossCorners;
    private DiagonalMovement diagonalMovement;

    /**
     * Breadth-First-Search path finder.
     *
     * @param opt {FindPathOption}           opt
     *            opt.allowDiagonal Whether diagonal movement is allowed.
     *            Deprecated, use diagonalMovement instead.
     *            opt.dontCrossCorners Disallow diagonal movement touching
     *            block corners. Deprecated, use diagonalMovement instead.
     *            opt.diagonalMovement Allowed diagonal movement.
     * @constructor
     */
    public BreadthFirstFinder(FindPathOption opt) {
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
        Queue<Node> openList = new LinkedList<>(); // 普通队列
        DiagonalMovement diagonalMovement = this.diagonalMovement;
        Node startNode = grid.getNodeAt(startX, startY);
        Node endNode = grid.getNodeAt(endX, endY);

        // push the start pos into the queue
        openList.offer(startNode);
        startNode.opened = true;

        while (!openList.isEmpty()) {
            // take the front node from the queue
            Node node = openList.poll();
            node.closed = true;

            // reached the end position
            if (node == endNode) {
                return Util.backtrace(endNode);
            }

            List<Node> neighbors = grid.getNeighbors(node, diagonalMovement);

            for (Node neighbor : neighbors) {
                // skip this neighbor if it has been inspected before
                if ((TypeConversion.isBoolean(neighbor.closed) && (boolean) neighbor.closed)
                        || (TypeConversion.isBoolean(neighbor.opened) && (boolean) neighbor.opened)) {
                    continue;
                }

                openList.offer(neighbor);
                neighbor.opened = true;
                neighbor.parent = node;
            }
        }
        return new ArrayList<>();
    }
}
