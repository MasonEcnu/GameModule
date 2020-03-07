package com.mason.game.astar.ordinary;

import java.util.Arrays;

/**
 * Created by mwu on 2020/3/4
 */
public class StarMap {
    // 二维数组地图
    private int[][] maps;
    // 地图的宽度
    private int width;
    // 地图的高度
    private int height;
    // 起始节点
    private Node start;
    // 终止节点
    private Node end;

    public StarMap(int[][] maps, int width, int height, Node start, Node end) {
        this.maps = maps;
        this.width = width;
        this.height = height;
        this.start = start;
        this.end = end;
    }

    public int[][] getMaps() {
        return maps;
    }

    public void setMaps(int[][] maps) {
        this.maps = maps;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "StarMap{" +
                "maps=" + Arrays.toString(maps) +
                ", width=" + width +
                ", height=" + height +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
