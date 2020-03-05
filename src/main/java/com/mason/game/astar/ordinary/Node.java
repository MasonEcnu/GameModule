package com.mason.game.astar.ordinary;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Created by mwu on 2020/3/4
 * 节点
 */
public class Node implements Comparable<Node> {
  // 到起点的步数
  private int G = 0;
  // 到终点的步数
  private int H = 0;
  // 坐标
  private Coords coords;
  // 父节点
  private Node parent = null;

  public Node(Coords coords) {
    this.coords = coords;
  }

  public Node(int x, int y) {
    this.coords = new Coords(x, y);
  }

  public Node(Coords coords, Node parent, int g, int h) {
    this.coords = coords;
    this.parent = parent;
    this.G = g;
    this.H = h;
  }

  // 计算最终路径长度
  public int calcFinalPathLength() {
    return G + H;
  }

  public int getG() {
    return G;
  }

  public void setG(int g) {
    this.G = g;
  }

  public int getH() {
    return H;
  }

  public void setH(int h) {
    this.H = h;
  }

  public Coords getCoords() {
    return coords;
  }

  public void setCoords(Coords coords) {
    this.coords = coords;
  }

  public Node getParent() {
    return parent;
  }

  public void setParent(Node parent) {
    this.parent = parent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Node node = (Node) o;
    return G == node.G &&
        H == node.H &&
        coords.equals(node.coords) &&
        parent.equals(node.parent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(G, H, coords, parent);
  }

  @Override
  public String toString() {
    return "Node{" +
        "startSteps=" + G +
        ", endSteps=" + H +
        ", pos=" + coords +
        ", parent=" + parent +
        '}';
  }

  public String toSimpleString() {
    return "Node{" +
        "startSteps=" + G +
        ", endSteps=" + H +
        ", pos=" + coords +
        '}';
  }

  @Override
  public int compareTo(@NotNull Node o) {
    return Integer.compare(G + H, o.G + o.H);
  }
}
