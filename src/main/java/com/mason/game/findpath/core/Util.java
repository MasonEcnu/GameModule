package com.mason.game.findpath.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mwu on 2020/3/5
 * 工具类
 */
public class Util {

    /**
     * Backtrace according to the parent records and return the path.
     * (including both start and end nodes)
     *
     * @param node {Node} node End node
     * @return {Array<Array<number>>} the path
     */
    public static List<List<Integer>> backtrace(Node node) {
        List<List<Integer>> path = new ArrayList<>();
        path.add(packageList(node));
        while (node.parent != null) {
            node = node.parent;
            path.add(packageList(node));
        }
        Collections.reverse(path);
        return path;
    }

    /**
     * Backtrace from start and end node, and return the path.
     * (including both start and end nodes)
     *
     * @param nodeA {Node}
     * @param nodeB {Node}
     */
    public static List<List<Integer>> biBacktrace(Node nodeA, Node nodeB) {
        List<List<Integer>> pathA = backtrace(nodeA);
        List<List<Integer>> pathB = backtrace(nodeB);
        pathA.addAll(pathB);
        return pathA;
    }

    /**
     * Compute the length of the path.
     *
     * @param path {Array<Array<number>>} path The path
     * @return {number} The length of the path
     */
    public static int pathLength(List<List<Integer>> path) {
        int sum = 0, dx, dy;
        List<Integer> a, b;
        for (int i = 1; i < path.size(); ++i) {
            a = path.get(i - 1);
            b = path.get(i);
            dx = a.get(0) - b.get(0);
            dy = a.get(1) - b.get(1);
            sum += Math.sqrt(dx * dx + dy * dy);
        }
        return sum;
    }

    /**
     * Given the start and end coordinates, return all the coordinates lying
     * on the line formed by these coordinates, based on Bresenham's algorithm.
     * http://en.wikipedia.org/wiki/Bresenham's_line_algorithm#Simplification
     *
     * @param x0 {number} x0 Start x coordinate
     * @param y0 {number} y0 Start y coordinate
     * @param x1 {number} x1 End x coordinate
     * @param y1 {number} y1 End y coordinate
     * @return {Array<Array<number>>} The coordinates on the line
     */
    public static List<List<Integer>> interpolate(int x0, int y0, int x1, int y1) {
        List<List<Integer>> line = new ArrayList<>();
        int sx, sy, dx, dy, err, e2;
        dx = Math.abs(x1 - x0);
        dy = Math.abs(y1 - y0);

        sx = (x0 < x1) ? 1 : -1;
        sy = (y0 < y1) ? 1 : -1;

        err = dx - dy;

        while (true) {
            line.add(packageList(x0, y0));
            if (x0 == x1 && y0 == y1) break;

            e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
        return line;
    }

    /**
     * Given a compressed path, return a new path that has all the segments
     * in it interpolated.
     *
     * @param path {Array<Array<number>>} path The path
     * @return expanded {Array<Array<number>>} expanded path
     */
    public static List<List<Integer>> expandPath(List<List<Integer>> path) {
        List<List<Integer>> expanded = new ArrayList<>();
        int len = path.size();
        if (len < 2) return expanded;
        for (int i = 0; i < len - 1; ++i) {
            List<Integer> coord0 = path.get(i);
            List<Integer> coord1 = path.get(i + 1);
            List<List<Integer>> interpolated = interpolate(coord0.get(0), coord0.get(1), coord1.get(0), coord1.get(1));
            int interpolatedLen = interpolated.size();
            for (int j = 0; j < interpolatedLen - 1; ++j) {
                expanded.add(interpolated.get(j));
            }
        }
        expanded.add(path.get(len - 1));
        return expanded;
    }

    /**
     * Smoothen the give path.
     * The original path will not be modified; a new path will be returned.
     *
     * @param grid {PF.Grid}              grid
     * @param path {Array<Array<number>>} path The path
     */
    public static List<List<Integer>> smoothenPath(Grid grid, List<List<Integer>> path) {
        int len = path.size();
        int x0 = path.get(0).get(0),
                y0 = path.get(0).get(1),
                x1 = path.get(len - 1).get(0),
                y1 = path.get(len - 1).get(1),
                sx, sy, ex, ey;
        List<Integer> coord;
        List<List<Integer>> line;
        List<Integer> testCoord;
        boolean blocked;
        sx = x0;
        sy = y0;
        List<List<Integer>> newPath = new ArrayList<>();
        newPath.add(packageList(sx, sy));

        for (int i = 2; i < len; ++i) {
            coord = path.get(i);
            ex = coord.get(0);
            ey = coord.get(1);
            line = interpolate(sx, sy, ex, ey);

            blocked = false;
            for (int j = 1; j < line.size(); ++j) {
                testCoord = line.get(j);
                if (!grid.isWalkableAt(testCoord.get(0), testCoord.get(1))) {
                    blocked = true;
                    break;
                }
            }
            if (blocked) {
                List<Integer> lastValidCoord = path.get(i - 1);
                newPath.add(lastValidCoord);
                sx = lastValidCoord.get(0);
                sy = lastValidCoord.get(1);
            }
        }
        newPath.add(packageList(x1, y1));
        return newPath;
    }

    /**
     * Compress a path, remove redundant nodes without altering the shape
     * The original path is not modified
     *
     * @param path {Array<Array<number>>} path The path
     * @return compressed {Array<Array<number>>} The compressed path
     */
    public static List<List<Integer>> compressPath(List<List<Integer>> path) {
        // nothing to compress
        if (path.size() < 3) {
            return path;
        }

        List<List<Integer>> compressed = new ArrayList<>();
        int sx = path.get(0).get(0), // start x
                sy = path.get(0).get(1), // start y
                px = path.get(1).get(0), // second point x
                py = path.get(1).get(1), // second point y
                dx = px - sx, // direction between the two points
                dy = py - sy, // direction between the two points
                lx, ly,
                ldx, ldy,
                sq;

        // normalize the direction
        sq = (int) Math.sqrt(dx * dx + dy * dy);
        dx /= sq;
        dy /= sq;

        // start the new path
        compressed.add(packageList(sx, sy));

        for (int i = 2; i < path.size(); ++i) {
            // store the last point
            lx = px;
            ly = py;

            // store the last direction
            ldx = dx;
            ldy = dy;


            // next point
            px = path.get(i).get(0);
            py = path.get(i).get(1);

            // next direction
            dx = px - lx;
            dy = py - ly;

            // normalize
            sq = (int) Math.sqrt(dx * dx + dy * dy);
            dx /= sq;
            dy /= sq;

            // if the direction has changed, store the point
            if (dx != ldx || dy != ldy) {
                compressed.add(packageList(lx, ly));
            }
        }
        compressed.add(packageList(px, py));
        return compressed;
    }

    /**
     * 将xy包装成一个list
     *
     * @param x x坐标
     * @param y y坐标
     * @return List<Integer>
     */
    public static List<Integer> packageList(int x, int y) {
        return new ArrayList<Integer>() {{
            add(x);
            add(y);
        }};
    }

    public static List<Integer> packageList(Node node) {
        return new ArrayList<Integer>() {{
            add(node.x);
            add(node.y);
        }};
    }

}
