package com.mason.game.findpath.core;

import java.util.ArrayList;
import java.util.List;

import static com.mason.game.findpath.Constants.BAR;

/**
 * Created by mwu on 2020/3/5
 */

public class Grid {

    public int width;
    public int height;
    public int[][] matrix = new int[][]{};
    public List<List<Node>> nodes = new ArrayList<>();

    /**
     * The Grid class, which serves as the encapsulation of the layout of the nodes.
     *
     * @param width  {number} width_or_matrix Number of columns of the grid
     * @param height {number}                                height Number of rows of the grid.
     * @param matrix {Array<Array<(number|boolean)>>}        [matrix] - A 0-1 matrix
     *               representing the walkable status of the nodes(0 for walkable).
     *               If the matrix is not supplied, all the nodes will be walkable.
     * @constructor
     */
    public Grid(int width, int height, int[][] matrix) {
        this.width = width;
        this.height = height;
        this.matrix = matrix;
        this.nodes = buildNodes(width, height, matrix);
    }

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Build and return the nodes.
     *
     * @param width  {number} width
     * @param height {number} height
     * @param matrix {Array<Array<number|boolean>>} [matrix] - A 0-1 matrix representing
     *               the walkable status of the nodes.
     * @private
     * @see Grid
     */
    private List<List<Node>> buildNodes(int width, int height, int[][] matrix) {
        List<List<Node>> nodes = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            List<Node> temps = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                // 默认路径都可以走
                temps.add(new Node(j, i, true));
            }
            nodes.add(temps);
        }

        if (matrix == null) {
            return nodes;
        }


        if (matrix.length != height || matrix[0].length != width) {
            String str = String.format("Matrix size does not fit:(height:%s, width:%s), matrix(%s, %s)",
                    height, width, matrix.length, matrix[0].length);
            throw new IllegalArgumentException(str);
        }

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (matrix[i][j] == BAR) {
                    // 0, false, null will be walkable
                    // while others will be un-walkable
                    nodes.get(i).get(j).walkable = false;
                }
            }
        }

        return nodes;
    }

    public Node getNodeAt(int x, int y) {
        return this.nodes.get(y).get(x);
    }

    /**
     * Determine whether the position is inside the grid.
     * XXX: `grid.isInside(x, y)` is wierd to read.
     * It should be `(x, y) is inside grid`, but I failed to find a better
     * name for this method.
     *
     * @param x {number} x
     * @param y {number} y
     * @return {boolean}
     */
    public boolean isInside(int x, int y) {
        return (x >= 0 && x < this.width) && (y >= 0 && y < this.height);
    }

    /**
     * Set whether the node on the given position is walkable.
     * NOTE: throws exception if the coordinate is not inside the grid.
     *
     * @param x        {number} x - The x coordinate of the node.
     * @param y        {number} y - The y coordinate of the node.
     * @param walkable {boolean} walkable - Whether the position is walkable.
     */
    public void setWalkableAt(int x, int y, boolean walkable) {
        this.nodes.get(y).get(x).walkable = walkable;
    }

    /**
     * Determine whether the node at the given position is walkable.
     * (Also returns false if the position is outside the grid.)
     *
     * @param x {number} x - The x coordinate of the node.
     * @param y {number} y - The y coordinate of the node.
     * @return {boolean} - The walkability of the node.
     */
    public boolean isWalkableAt(int x, int y) {
        return this.isInside(x, y) && this.nodes.get(y).get(x).walkable;
    }

    public boolean isWalkableAt(Node node) {
        return this.isInside(node.x, node.y) && this.nodes.get(node.y).get(node.x).walkable;
    }

    /**
     * Get the neighbors of the given node.
     * <p>
     * offsets      diagonalOffsets:
     * +---+---+---+    +---+---+---+
     * |   | 0 |   |    | 0 |   | 1 |
     * +---+---+---+    +---+---+---+
     * | 3 |   | 1 |    |   |   |   |
     * +---+---+---+    +---+---+---+
     * |   | 2 |   |    | 3 |   | 2 |
     * +---+---+---+    +---+---+---+
     * <p>
     * When allowDiagonal is true, if offsets[i] is valid, then
     * diagonalOffsets[i] and
     * diagonalOffsets[(i + 1) % 4] is valid.
     *
     * @param node             {Node} node
     * @param diagonalMovement {DiagonalMovement} diagonalMovement
     */
    public List<Node> getNeighbors(Node node, DiagonalMovement diagonalMovement) {
        int x = node.x, y = node.y;

        List<Node> neighbors = new ArrayList<>();
        boolean s0 = false, d0,
                s1 = false, d1,
                s2 = false, d2,
                s3 = false, d3;
        List<List<Node>> nodes = this.nodes;

        // ↑
        if (this.isWalkableAt(x, y - 1)) {
            neighbors.add(nodes.get(y - 1).get(x));
            s0 = true;
        }
        // →
        if (this.isWalkableAt(x + 1, y)) {
            neighbors.add(nodes.get(y).get(x + 1));
            s1 = true;
        }
        // ↓
        if (this.isWalkableAt(x, y + 1)) {
            neighbors.add(nodes.get(y + 1).get(x));
            s2 = true;
        }
        // ←
        if (this.isWalkableAt(x - 1, y)) {
            neighbors.add(nodes.get(y).get(x - 1));
            s3 = true;
        }

        if (diagonalMovement == DiagonalMovement.Never) {
            return neighbors;
        }

        if (diagonalMovement == DiagonalMovement.OnlyWhenNoObstacles) {
            d0 = s3 && s0;
            d1 = s0 && s1;
            d2 = s1 && s2;
            d3 = s2 && s3;
        } else if (diagonalMovement == DiagonalMovement.IfAtMostOneObstacle) {
            d0 = s3 || s0;
            d1 = s0 || s1;
            d2 = s1 || s2;
            d3 = s2 || s3;
        } else if (diagonalMovement == DiagonalMovement.Always) {
            d0 = true;
            d1 = true;
            d2 = true;
            d3 = true;
        } else {
            throw new IllegalArgumentException("Incorrect value of diagonalMovement");
        }


        // ↖
        if (d0 && this.isWalkableAt(x - 1, y - 1)) {
            neighbors.add(nodes.get(y - 1).get(x - 1));
        }
        // ↗
        if (d1 && this.isWalkableAt(x + 1, y - 1)) {
            neighbors.add(nodes.get(y - 1).get(x + 1));
        }
        // ↘
        if (d2 && this.isWalkableAt(x + 1, y + 1)) {
            neighbors.add(nodes.get(y + 1).get(x + 1));
        }
        // ↙
        if (d3 && this.isWalkableAt(x - 1, y + 1)) {
            neighbors.add(nodes.get(y + 1).get(x - 1));
        }

        return neighbors;
    }

    /**
     * Get a clone of this grid.
     *
     * @return {Grid} Cloned grid.
     */
    public Grid clone() {
        int width = this.width;
        int height = this.height;
        List<List<Node>> thisNodes = this.nodes;
        Grid newGrid = new Grid(width, height);
        List<List<Node>> newNodes = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            List<Node> temps = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                // 默认路径都可以走
                temps.add(new Node(j, i, thisNodes.get(i).get(j).walkable));
            }
            newNodes.add(temps);
        }

        newGrid.nodes = newNodes;
        return newGrid;
    }
}
