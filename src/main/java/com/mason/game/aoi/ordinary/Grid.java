package com.mason.game.aoi.ordinary;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.ConcurrentHashMap;

import static com.mason.game.aoi.ordinary.Constants.NOTHING;

/**
 * Created by mwu on 2020/3/3
 */
public class Grid {
    private int gridId;
    private int row;
    private int col;

    // 网络矩形坐标
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;

    // Grid内的players
    private ConcurrentHashMap<String, Integer> players;

    public Grid(int gridId, int minX, int maxX, int minY, int maxY, int row, int col) {
        this.gridId = gridId;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.row = row;
        this.col = col;
        players = new ConcurrentHashMap<>();
    }

    public boolean addPlayer(String player) {
        return players.putIfAbsent(player, NOTHING) == null;
    }

    public boolean deletePlayer(String player) {
        return players.remove(player) != null;
    }

    public int getGridId() {
        return gridId;
    }

    public void setGridId(int gridId) {
        this.gridId = gridId;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "gridId=" + gridId +
                ", row=" + row +
                ", col=" + col +
                ", minX=" + minX +
                ", maxX=" + maxX +
                ", minY=" + minY +
                ", maxY=" + maxY +
                ", players=" + JSON.toJSONString(players) +
                '}';
    }
}
