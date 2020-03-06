package com.mason.game.findpath.core;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Created by mwu on 2020/3/5
 */
public class Node implements Comparable<Node> {
  public int x;
  public int y;
  public boolean walkable;
  public Node parent = null;
  public int g = 0;
  public int h = 0;
  public int f = 0;
  public boolean closed = false;
  public boolean opened = false;

  public Node(int x, int y, boolean walkable) {
    this.x = x;
    this.y = y;
    this.walkable = walkable;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Node node = (Node) o;
    return x == node.x &&
        y == node.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public String toString() {
    return "Node{" +
        "x=" + x +
        ", y=" + y +
        ", walkable=" + walkable +
        '}';
  }

  @Override
  public int compareTo(@NotNull Node o) {
    return Integer.compare(f, o.f);
  }
}
