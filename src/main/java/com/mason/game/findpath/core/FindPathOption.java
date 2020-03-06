package com.mason.game.findpath.core;

import com.mason.game.findpath.core.heuristic.Heuristic;
import com.mason.game.findpath.core.heuristic.HeuristicFunction;

/**
 * Created by mwu on 2020/3/6
 * 选择项目
 */
public class FindPathOption {
  public boolean allowDiagonal = false;
  public boolean dontCrossCorners = false;
  public DiagonalMovement diagonalMovement = DiagonalMovement.Always;
  public HeuristicFunction heuristic = Heuristic.manhattan;
  public int weight = 1;
}
