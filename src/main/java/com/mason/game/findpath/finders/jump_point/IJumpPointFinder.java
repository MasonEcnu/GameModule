package com.mason.game.findpath.finders.jump_point;

import com.mason.game.findpath.core.Grid;
import com.mason.game.findpath.core.Node;

import java.util.List;

/**
 * Created by mwu on 2020/3/10
 */
interface IJumpPointFinder {
    List<Integer> jump(Grid grid, int x, int y, int px, int py, Node endNode);

    List<List<Integer>> findNeighbors(Grid grid, Node node);
}
