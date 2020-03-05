package com.mason.game.findpath.core;

import java.util.Objects;

/**
 * Created by mwu on 2020/3/5
 */
public class Node {
  public int x;
  public int y;
  public boolean walkable;
  public Node parent;

  public Node(int x, int y, boolean walkable) {
    this.x = x;
    this.y = y;
    this.walkable = walkable;
    this.parent = null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Node node = (Node) o;
    return x == node.x &&
        y == node.y &&
        walkable == node.walkable &&
        Objects.equals(parent, node.parent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, walkable, parent);
  }

  @Override
  public String toString() {
    return "Node{" +
        "x=" + x +
        ", y=" + y +
        ", walkable=" + walkable +
        '}';
  }
}
