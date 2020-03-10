package com.mason.game.findpath;

import com.mason.game.findpath.core.FindPathOption;
import com.mason.game.findpath.core.Grid;
import com.mason.game.findpath.core.heuristic.Heuristic;
import com.mason.game.findpath.finders.*;
import org.junit.Test;

import java.util.List;

import static com.mason.game.findpath.Constants.PATH;

/**
 * Created by mwu on 2020/3/6
 */
public class TestFindPath {
    private static int[][] maps = {
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}
    };

    private static void printMap() {
        for (int[] map : maps) {
            for (int i : map) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    private static void printMap(int[][] localMaps) {
        for (int[] map : localMaps) {
            for (int i : map) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void testAStar() {
        int startX = 1, startY = 1, endX = 4, endY = 5;
        Grid grid = new Grid(maps[0].length, maps.length, maps);
        FindPathOption option = new FindPathOption();
        option.allowDiagonal = true;
        option.dontCrossCorners = true;
        option.heuristic = Heuristic.euclidean;
        option.weight = 1;
        AStarFinder aStarFinder = new AStarFinder(option);
        List<List<Integer>> path = aStarFinder.findPath(startX, startY, endX, endY, grid);
        path.forEach(coord -> maps[coord.get(1)][coord.get(0)] = PATH);
        printMap();
    }

    @Test
    public void testBiAStar() {
        int startX = 2, startY = 2, endX = 4, endY = 5;
        Grid grid = new Grid(maps[0].length, maps.length, maps);
        FindPathOption option = new FindPathOption();
        BiAStarFinder biAStarFinder = new BiAStarFinder(option);
        List<List<Integer>> path = biAStarFinder.findPath(startX, startY, endX, endY, grid);
        path.forEach(coord -> maps[coord.get(1)][coord.get(0)] = PATH);
        printMap();
    }

    @Test
    public void testIDAStar() {
        int startX = 1, startY = 1, endX = 4, endY = 5;
        Grid grid = new Grid(maps[0].length, maps.length, maps);
        FindPathOption option = new FindPathOption();
        option.timeLimit = 2;
        IDAStarFinder idaStarFinder = new IDAStarFinder(option);
        List<List<Integer>> path = idaStarFinder.findPath(startX, startY, endX, endY, grid);
        path.forEach(coord -> maps[coord.get(1)][coord.get(0)] = PATH);
        printMap();
    }

    @Test
    public void testTrace() {
        int startX = 1, startY = 1, endX = 4, endY = 5;
        Grid grid = new Grid(maps[0].length, maps.length, maps);
        FindPathOption option = new FindPathOption();
        option.allowDiagonal = false;
        TraceFinder traceFinder = new TraceFinder(option);
        List<List<Integer>> path = traceFinder.findPath(startX, startY, endX, endY, grid);
        path.forEach(coord -> maps[coord.get(1)][coord.get(0)] = PATH);
        printMap();
    }

    @Test
    public void testBestFirst() {
        int startX = 1, startY = 1, endX = 4, endY = 5;
        Grid grid = new Grid(maps[0].length, maps.length, maps);
        FindPathOption option = new FindPathOption();
        BestFirstFinder bestFirstFinder = new BestFirstFinder(option);
        List<List<Integer>> path = bestFirstFinder.findPath(startX, startY, endX, endY, grid);
        path.forEach(coord -> maps[coord.get(1)][coord.get(0)] = PATH);
        printMap();
    }

    @Test
    public void testBiBestFirst() {
        int startX = 1, startY = 1, endX = 4, endY = 5;
        Grid grid = new Grid(maps[0].length, maps.length, maps);
        FindPathOption option = new FindPathOption();
        BiBestFirstFinder biBestFirstFinder = new BiBestFirstFinder(option);
        List<List<Integer>> path = biBestFirstFinder.findPath(startX, startY, endX, endY, grid);
        path.forEach(coord -> maps[coord.get(1)][coord.get(0)] = PATH);
        printMap();
    }
}
