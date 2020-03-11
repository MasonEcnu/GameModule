package com.mason.game.findpath.core;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Created by mwu on 2020/3/5
 */
public class Node implements Comparable<Node> {
    public int x;
    public int y;
    boolean walkable;
    public Node parent = null;
    public double g = 0;
    public double h = 0;
    public double f = 0;
    // for A star
    public Object closed = false;
    public Object opened = false;
    // for IDA star
    public int retainCount = 0;
    public boolean tested = false;
    // for BreadthFirst
    public StartType startType = StartType.BY_START;

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
        return Double.compare(f, o.f);
    }
}
