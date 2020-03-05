package com.mason.game.findpath.core;

/**
 * Created by mwu on 2020/3/5
 */
public class Heuristic {

  /**
   * Manhattan distance.
   *
   * @param dx Difference in x
   * @param dy Difference in y
   * @return dx + dy
   */
  public static int manhattan(int dx, int dy) {
    return dx + dy;
  }

  /**
   * Euclidean distance.
   *
   * @param dx - Difference in x.
   * @param dy - Difference in y.
   * @return sqrt(dx * dx + dy * dy)
   */
  public static double euclidean(int dx, int dy) {
    return Math.sqrt(dx * dx + dy * dy);
  }


  /**
   * Octile distance.
   *
   * @param dx - Difference in x.
   * @param dy - Difference in y.
   * @return sqrt(dx * dx + dy * dy) for grids
   */
  public static double octile(int dx, int dy) {
    double F = Math.sqrt(2);
    return (dx < dy) ? F * dx + dy : F * dy + dx;
  }

  /**
   * Chebyshev distance.
   *
   * @param dx - Difference in x.
   * @param dy - Difference in y.
   * @return max(dx, dy)
   */
  public static int chebyshev(int dx, int dy) {
    return Math.max(dx, dy);
  }
}
