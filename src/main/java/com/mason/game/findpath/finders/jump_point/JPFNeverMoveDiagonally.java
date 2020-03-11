package com.mason.game.findpath.finders.jump_point;

import com.mason.game.findpath.core.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mwu on 2020/3/11
 */
public class JPFNeverMoveDiagonally implements IJumpPointFinder {

    private FindPathOption opt;

    JPFNeverMoveDiagonally(FindPathOption opt) {
        this.opt = opt;
    }

    /**
     * Search recursively in the direction (parent -> child), stopping only when a
     * jump point is found.
     *
     * @return {Array<Array<number>>} The x, y coordinate of the jump point
     * found, or null if not found
     */
    @Override
    public List<Integer> jump(Grid grid, int x, int y, int px, int py, Node endNode) {
        int dx = x - px;
        int dy = y - py;
        if (!grid.isWalkableAt(x, y)) {
            return null;
        }

        if (opt.trackJumpRecursion) {
            grid.getNodeAt(x, y).tested = true;
        }

        if (grid.getNodeAt(x, y) == endNode) {
            return Util.packageList(x, y);
        }

        if (dx != 0) {
            if ((grid.isWalkableAt(x, y - 1) && !grid.isWalkableAt(x - dx, y - 1)) ||
                    (grid.isWalkableAt(x, y + 1) && !grid.isWalkableAt(x - dx, y + 1))) {
                return Util.packageList(x, y);
            }
        } else if (dy != 0) {
            if ((grid.isWalkableAt(x - 1, y) && !grid.isWalkableAt(x - 1, y - dy)) ||
                    (grid.isWalkableAt(x + 1, y) && !grid.isWalkableAt(x + 1, y - dy))) {
                return Util.packageList(x, y);
            }
            List<Integer> xp1 = jump(grid, x + 1, y, x, y, endNode);
            List<Integer> xm1 = jump(grid, x - 1, y, x, y, endNode);
            //When moving vertically, must check for horizontal jump points
            if ((xp1 != null && !xp1.isEmpty()) || (xm1 != null && !xm1.isEmpty())) {
                return Util.packageList(x, y);
            }
        } else {
            throw new IllegalArgumentException("Only horizontal and vertical movements are allowed");
        }

        return jump(grid, x + dx, y + dy, x, y, endNode);
    }

    /**
     * Find the neighbors for the given node. If the node has a parent,
     * prune the neighbors based on the jump point search algorithm, otherwise
     * return all available neighbors.
     *
     * @return {Array<Array<number>>} The neighbors found.
     */
    @Override
    public List<List<Integer>> findNeighbors(Grid grid, Node node) {
        Node parent = node.parent;
        int x = node.x;
        int y = node.y;
        List<List<Integer>> neighbors = new ArrayList<>();
        if (parent != null) {
            int px = parent.x;
            int py = parent.y;
            // get the normalized direction of travel
            int dx = (x - px) / Math.max(Math.abs(x - px), 1);
            int dy = (y - py) / Math.max(Math.abs(y - py), 1);

            if (dx != 0) {
                if (grid.isWalkableAt(x, y - 1)) {
                    neighbors.add(Util.packageList(x, y - 1));
                }
                if (grid.isWalkableAt(x, y + 1)) {
                    neighbors.add(Util.packageList(x, y + 1));
                }
                if (grid.isWalkableAt(x + dx, y)) {
                    neighbors.add(Util.packageList(x + dx, y));
                }
            } else if (dy != 0) {
                if (grid.isWalkableAt(x - 1, y)) {
                    neighbors.add(Util.packageList(x - 1, y));
                }
                if (grid.isWalkableAt(x + 1, y)) {
                    neighbors.add(Util.packageList(x + 1, y));
                }
                if (grid.isWalkableAt(x, y + dy)) {
                    neighbors.add(Util.packageList(x, y + dy));
                }
            }
        } else {
            // return all neighbors
            List<Node> neighborNodes = grid.getNeighbors(node, DiagonalMovement.Never);
            for (Node neighborNode : neighborNodes) {
                neighbors.add(Util.packageList(neighborNode));
            }
        }
        return neighbors;
    }
}
