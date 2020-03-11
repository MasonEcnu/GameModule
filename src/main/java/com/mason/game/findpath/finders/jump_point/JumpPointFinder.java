package com.mason.game.findpath.finders.jump_point;

import com.mason.game.findpath.core.*;
import com.mason.game.findpath.core.heuristic.Heuristic;
import com.mason.game.findpath.core.heuristic.HeuristicFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by mwu on 2020/3/10
 */
public class JumpPointFinder {
    private boolean trackJumpRecursion;
    private DiagonalMovement diagonalMovement;
    private HeuristicFunction heuristic;
    private Queue<Node> openList = new PriorityQueue<>(); // 优先队列(升序)

    private IJumpPointFinder jumpFinder;

    /**
     * Base class for the Jump Point Search algorithm
     *
     * @param opt {FindPathOption} opt
     *            opt.heuristic Heuristic function to estimate the distance
     *            (defaults to manhattan).
     */
    public JumpPointFinder(FindPathOption opt) {
        this.heuristic = opt.heuristic;
        this.trackJumpRecursion = opt.trackJumpRecursion;
        this.jumpFinder = JumpPointFactory.getFinder(opt);
        System.out.println(this.jumpFinder.getClass().getSimpleName());
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

        // set the `g` and `f` value of the start node to be 0
        startNode.g = 0;
        startNode.f = 0;

        // push the start node into the open list
        openList.add(startNode);
        startNode.opened = true;

        while (!openList.isEmpty()) {
            // pop the position of node which has the minimum `f` value.
            Node node = openList.poll();
            node.closed = true;

            if (node == endNode) {
                return Util.expandPath(Util.backtrace(endNode));
            }

            identifySuccessors(grid, node, endNode);
        }

        return new ArrayList<>();
    }

    /**
     * Identify successors for the given node. Runs a jump point search in the
     * direction of each available neighbor, adding any points found to the open
     * list.
     */
    private void identifySuccessors(Grid grid, Node node, Node endNode) {
        HeuristicFunction heuristic = this.heuristic;
        Queue<Node> openList = this.openList;
        int endX = endNode.x;
        int endY = endNode.y;
        int x = node.x;
        int y = node.y;
        List<List<Integer>> neighbors = jumpFinder.findNeighbors(grid, node);
        for (List<Integer> neighbor : neighbors) {
            List<Integer> jumpPoint = jumpFinder.jump(grid, neighbor.get(0), neighbor.get(1), x, y, endNode);
            if (jumpPoint != null && !jumpPoint.isEmpty()) {
                int jx = jumpPoint.get(0);
                int jy = jumpPoint.get(1);
                Node jumpNode = grid.getNodeAt(jx, jy);

                if (TypeConversion.isBoolean(jumpNode.closed) && (boolean) jumpNode.closed) {
                    continue;
                }

                // include distance, as parent may not be immediately adjacent:
                double d = Heuristic.octile(Math.abs(jx - x), Math.abs(jy - y));
                double ng = node.g + d; // next `g` value

                if (TypeConversion.isBoolean(jumpNode.opened)) {
                    if (!(boolean) jumpNode.opened || ng < jumpNode.g) {
                        jumpNode.g = ng;
                        if (jumpNode.h <= 0) {
                            jumpNode.h = heuristic.calcDistance(Math.abs(jx - endX), Math.abs((jy - endY)));
                        }
                        jumpNode.f = jumpNode.g + jumpNode.h;
                        jumpNode.parent = node;

                        if (!(boolean) jumpNode.opened) {
                            openList.add(jumpNode);
                            jumpNode.opened = true;
                        } else {
                            openList.removeIf(temp -> temp.equals(jumpNode));
                            openList.add(jumpNode);
                        }
                    }
                }
            }
        }
    }
}
