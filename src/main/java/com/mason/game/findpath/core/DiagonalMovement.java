package com.mason.game.findpath.core;

/**
 * Created by mwu on 2020/3/5
 */
public enum DiagonalMovement {
  Always(1),
  Never(2),
  IfAtMostOneObstacle(3),
  OnlyWhenNoObstacles(4);

  DiagonalMovement(int status) {
  }
}
