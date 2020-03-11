package com.mason.game.findpath.finders.jump_point;

import com.mason.game.findpath.core.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mwu on 2020/3/11
 */
public class JPFMoveDiagonallyIfNoObstacles implements IJumpPointFinder {

    private FindPathOption opt;

    JPFMoveDiagonallyIfNoObstacles(FindPathOption opt) {
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

        // check for forced neighbors
        // along the diagonal
        if (dx != 0 && dy != 0) {
            // if ((grid.isWalkableAt(x - dx, y + dy) && !grid.isWalkableAt(x - dx, y)) ||
            // (grid.isWalkableAt(x + dx, y - dy) && !grid.isWalkableAt(x, y - dy))) {
            // return [x, y];
            // }
            // when moving diagonally, must check for vertical/horizontal jump points
            List<Integer> xpdx = jump(grid, x + dx, y, x, y, endNode);
            List<Integer> ypdy = jump(grid, x, y + dy, x, y, endNode);
            if ((xpdx != null && !xpdx.isEmpty()) || (ypdy != null && !ypdy.isEmpty())) {
                return Util.packageList(x, y);
            }
        } else {
            // horizontally/vertically
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
                // When moving vertically, must check for horizontal jump points
                // if (this._jump(x + 1, y, x, y) || this._jump(x - 1, y, x, y)) {
                // return [x, y];
                // }
            }
        }
        // moving diagonally, must make sure one of the vertical/horizontal
        // neighbors is open to allow the path
        if (grid.isWalkableAt(x + dx, y) && grid.isWalkableAt(x, y + dy)) {
            return jump(grid, x + dx, y + dy, x, y, endNode);
        } else {
            return null;
        }
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

            // search diagonally
            if (dx != 0 && dy != 0) {
                if (grid.isWalkableAt(x, y + dy)) {
                    neighbors.add(Util.packageList(x, y + dy));
                }
                if (grid.isWalkableAt(x + dx, y)) {
                    neighbors.add(Util.packageList(x + dx, y));
                }
                if (grid.isWalkableAt(x, y + dy) && grid.isWalkableAt(x + dx, y)) {
                    neighbors.add(Util.packageList(x + dx, y + dy));
                }
            } else {
                // search horizontally/vertically
                boolean isNextWalkable;
                if (dx != 0) {
                    isNextWalkable = grid.isWalkableAt(x + dx, y);
                    boolean isTopWalkable = grid.isWalkableAt(x, y + 1);
                    boolean isBottomWalkable = grid.isWalkableAt(x, y - 1);

                    if (isNextWalkable) {
                        neighbors.add(Util.packageList(x + dx, y));
                        if (isTopWalkable) {
                            neighbors.add(Util.packageList(x + dx, y + 1));
                        }
                        if (isBottomWalkable) {
                            neighbors.add(Util.packageList(x + dx, y - 1));
                        }
                    }
                    if (isTopWalkable) {
                        neighbors.add(Util.packageList(x, y + 1));
                    }
                    if (isBottomWalkable) {
                        neighbors.add(Util.packageList(x, y - 1));
                    }
                } else if (dy != 0) {
                    isNextWalkable = grid.isWalkableAt(x, y + dy);
                    boolean isRightWalkable = grid.isWalkableAt(x + 1, y);
                    boolean isLeftWalkable = grid.isWalkableAt(x - 1, y);

                    if (isNextWalkable) {
                        neighbors.add(Util.packageList(x, y + dy));
                        if (isRightWalkable) {
                            neighbors.add(Util.packageList(x + 1, y + dy));
                        }
                        if (isLeftWalkable) {
                            neighbors.add(Util.packageList(x - 1, y + dy));
                        }
                    }
                    if (isRightWalkable) {
                        neighbors.add(Util.packageList(x + 1, y));
                    }
                    if (isLeftWalkable) {
                        neighbors.add(Util.packageList(x - 1, y));
                    }
                }
            }
        } else {
            // return all neighbors
            List<Node> neighborNodes = grid.getNeighbors(node, DiagonalMovement.OnlyWhenNoObstacles);
            for (Node neighborNode : neighborNodes) {
                neighbors.add(Util.packageList(neighborNode));
            }
        }
        return neighbors;
    }
}
