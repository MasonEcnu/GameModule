package com.mason.game.findpath;

import com.mason.game.findpath.core.FindPathOption;
import com.mason.game.findpath.core.Grid;
import com.mason.game.findpath.core.heuristic.Heuristic;
import com.mason.game.findpath.finders.AStarFinder;
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

  @Test
  public void testAStar() {
    int startX = 1, startY = 1, endX = 4, endY = 5;
    Grid grid = new Grid(maps[0].length, maps.length, maps);
    FindPathOption option = new FindPathOption();
    option.allowDiagonal = true;
    option.dontCrossCorners = true;
    option.heuristic = Heuristic.euclidean;
    AStarFinder aStarFinder = new AStarFinder(option);
    List<List<Integer>> path = aStarFinder.findPath(startX, startY, endX, endY, grid);
    path.forEach(coord -> maps[coord.get(1)][coord.get(0)] = PATH);
    printMap();
  }

  @Test
  public void testBiAStar() {

  }

  private static void printMap() {
    for (int[] map : maps) {
      for (int i : map) {
        System.out.print(i + " ");
      }
      System.out.println();
    }
  }
}
