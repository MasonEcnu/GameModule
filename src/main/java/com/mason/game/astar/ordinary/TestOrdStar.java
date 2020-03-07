package com.mason.game.astar.ordinary;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mwu on 2020/3/4
 * http://qiao.github.io/PathFinding.js/visual/
 */
public class TestOrdStar {

    public static void main(String[] args) {
        int[][] maps = {
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}
        };

        Node start = new Node(1, 1);
        Node end = new Node(4, 5);
        StarMap starMap = new StarMap(maps, maps[0].length, maps.length, start, end);
        OrdStar ordStar = new OrdStar();
        ordStar.start(starMap);
        printMap(maps);
        printMovingTrail(ordStar.getMovingTrail());
    }

    private static void printMovingTrail(List<Node> movingTrail) {
        AtomicInteger index = new AtomicInteger();
        StringBuffer sb = new StringBuffer();
        movingTrail.forEach(node -> {
            String str = String.format("(x=%2s, y=%2s)", node.getCoords().getX(), node.getCoords().getY());
            index.getAndIncrement();
            if (index.get() % 5 == 0) {
                sb.append(str);
                sb.append("\n");
            } else {
                if (node == movingTrail.get(movingTrail.size() - 1)) {
                    sb.append(str);
                } else {
                    sb.append(str).append("-->");
                }
            }
        });
        System.out.println(sb.toString());
    }

    private static void printMap(int[][] maps) {
        for (int[] map : maps) {
            for (int i : map) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
