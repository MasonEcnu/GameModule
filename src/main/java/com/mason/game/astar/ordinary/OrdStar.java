package com.mason.game.astar.ordinary;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Created by mwu on 2020/3/4
 * 普通A星算法
 */
public class OrdStar {
    private final static int BAR = 1; // 障碍
    private final static int PATH = 2;  // 路径
    private final static int DIRECT_VALUE = 10; // 横竖移动代价
    private final static int OBLIQUE_VALUE = 14; // 斜移动代价

    private Queue<Node> openList = new PriorityQueue<>(); // 优先队列(升序)
    private List<Node> closeList = new ArrayList<>();

    // 记录移动顺序
    private List<Node> movingTrail = new ArrayList<>();

    // 开始算法
    public void start(@NotNull StarMap starMap) {
        if (starMap.getMaps().length == 0) {
            System.out.println("算法不能以空地图开始运行......");
        }
        // clean
        openList.clear();
        closeList.clear();
        // 开始搜索
        openList.add(starMap.getStart());
        moveNodes(starMap);
    }

    // 移动当前节点
    private void moveNodes(StarMap starMap) {
        boolean findPath = false;
        while (!openList.isEmpty()) {
            Node current = openList.poll();
            closeList.add(current);
            addNeighborNodeInOpen(starMap, current);
            if (isCoordsInClose(starMap.getEnd().getCoords())) {
                drawPath(starMap.getMaps(), starMap.getEnd());
                findPath = true;
                break;
            }
        }
        if (!findPath) {
            System.out.println("未找到合适的路径......");
        }
    }

    // 在二维数组中绘制路径
    private void drawPath(int[][] maps, Node end) {
        if (end == null || maps == null || maps.length == 0) {
            System.out.println("地图不能为空，同时结束节点也不能为空");
            return;
        }
        System.out.println("总代价：" + end.getG());
        while (end != null) {
            movingTrail.add(end);
            Coords coords = end.getCoords();
            maps[coords.getY()][coords.getX()] = PATH;
            end = end.getParent();
        }
        Collections.reverse(movingTrail);
    }

    // 判断坐标是否在close表中
    private boolean isCoordsInClose(Coords coords) {
        return coords != null && isCoordsInClose(coords.getX(), coords.getY());
    }

    // 判断坐标是否在close表中
    private boolean isCoordsInClose(int x, int y) {
        if (closeList.isEmpty()) return false;
        for (Node node : closeList) {
            if (node.getCoords().getX() == x && node.getCoords().getY() == y) {
                return true;
            }
        }
        return false;
    }

    // 添加所有邻接节点到open表中
    private void addNeighborNodeInOpen(StarMap starMap, Node current) {
        int x = current.getCoords().getX();
        int y = current.getCoords().getY();

        // 左
        addNeighborNodeInOpen(starMap, current, x - 1, y, DIRECT_VALUE);
        // 上
        addNeighborNodeInOpen(starMap, current, x, y - 1, DIRECT_VALUE);
        // 右
        addNeighborNodeInOpen(starMap, current, x + 1, y, DIRECT_VALUE);
        // 下
        addNeighborNodeInOpen(starMap, current, x, y + 1, DIRECT_VALUE);
        // 左上
        addNeighborNodeInOpen(starMap, current, x - 1, y - 1, OBLIQUE_VALUE);
        // 右上
        addNeighborNodeInOpen(starMap, current, x + 1, y - 1, OBLIQUE_VALUE);
        // 右下
        addNeighborNodeInOpen(starMap, current, x + 1, y + 1, OBLIQUE_VALUE);
        // 左下
        addNeighborNodeInOpen(starMap, current, x - 1, y + 1, OBLIQUE_VALUE);
    }

    // 添加一个邻接节点到open表中
    private void addNeighborNodeInOpen(StarMap starMap, Node current, int x, int y, int value) {
        if (canAddNodeToOpen(starMap, x, y)) {
            Node end = starMap.getEnd();
            Coords coords = new Coords(x, y);
            // 计算邻接节点的G值
            int G = current.getG() + value;

            Node child = findNodeInOpen(coords);
            if (child == null) {
                int H = calcH(end.getCoords(), coords);
                if (isEndNode(end.getCoords(), coords)) {
                    child = end;
                    child.setParent(current);
                    child.setG(G);
                    child.setH(H);
                } else {
                    child = new Node(coords, current, G, H);
                }
                openList.add(child);
            } else if (child.getG() > G) {
                child.setG(G);
                child.setParent(current);
                openList.add(child);
            }
        }
    }

    private Node findNodeInOpen(Coords coords) {
        if (coords == null || openList.isEmpty()) return null;
        for (Node node : openList) {
            if (node.getCoords().equals(coords)) {
                return node;
            }
        }
        return null;
    }

    // 计算H的估值：“曼哈顿”法，坐标分别取差值相加
    private int calcH(Coords end, Coords coords) {
        return Math.abs(end.getX() - coords.getX()) + Math.abs(end.getY() - coords.getY());
    }

    // 判断节点是否最终节点
    private boolean isEndNode(Coords end, Coords coords) {
        return end.equals(coords);
    }

    private boolean canAddNodeToOpen(StarMap starMap, int x, int y) {
        // 是否在地图中
        if (x < 0 || x >= starMap.getWidth() || y < 0 || y >= starMap.getHeight()) return false;
        // 判断是否是不可通过的点
        if (starMap.getMaps()[y][x] == BAR) return false;
        // 判断节点是否在close表中
        return !isCoordsInClose(x, y);
    }

    public List<Node> getMovingTrail() {
        return movingTrail;
    }
}
