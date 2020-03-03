package com.mason.game.aoi;

import com.mason.game.constans.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by mwu on 2020/3/3
 */
public class GridManager {
  // 管理的所有格子
  private ConcurrentHashMap<Integer, Grid> allGrids;
  // 地图大小
  private int minX;
  private int maxX;
  private int minY;
  private int maxY;
  // 行列
  private int row;
  private int col;
  // 格子大小
  private int lenX;
  private int lenY;

  public GridManager(int minX, int maxX, int minY, int maxY, int lenX, int lenY) {
    this.minX = minX;
    this.maxX = maxX;
    this.minY = minY;
    this.maxY = maxY;
    this.lenX = lenX;
    this.lenY = lenY;
    this.allGrids = new ConcurrentHashMap<>();

    // 初始化网络
    int col = (int) Math.ceil((maxX - minX) * 1.0 / lenX);
    int row = (int) Math.ceil((maxY - minY) * 1.0 / lenY);

    for (int i = 1; i <= col; i++) {
      for (int j = 0; j < row; j++) {
        int id = i + j * col;
        int gridMinX = i * lenX - lenX;
        int gridMaxX = i * lenX;
        int gridMinY = j * lenY - lenY;
        int gridMaxY = j * lenY;
        Grid grid = new Grid(id, gridMinX, gridMaxX, gridMinY, gridMaxY, j + 1, i);
        this.allGrids.putIfAbsent(id, grid);
      }
    }
    this.col = col;
    this.row = row;
  }

  // 位置是否有效
  public boolean isPosValid(int posX, int posY) {
    return posX > this.minX && posX <= this.maxX && posY > this.minY && posY <= this.maxY;
  }

  // 位置是否无效
  public boolean isPosInvalid(int posX, int posY) {
    return !isPosValid(posX, posY);
  }

  // 进入grid
  public Map<Integer, Grid> enter(String player, int posX, int posY) {
    Grid grid = getGridByPos(posX, posY);
    grid.addPlayer(player);
    return getInterestAreaByPos(posX, posY);
  }

  // 离开grid
  public Map<Integer, Grid> leave(String player, int posX, int posY) {
    Grid grid = getGridByPos(posX, posY);
    grid.deletePlayer(player);
    return getInterestAreaByPos(posX, posY);
  }

  // 移动
  public MoveResult move(int oldPosX, int oldPosY, int posX, int posY, String player) {
    Grid oldGrid = getGridByPos(oldPosX, oldPosY);
    Grid currGrid = getGridByPos(posX, posY);
    Map<Integer, Grid> lostGrids = new HashMap<>();
    Map<Integer, Grid> bornGrids = new HashMap<>();

    boolean isCrossGrid = false;
    if (oldGrid != currGrid) {
      isCrossGrid = true;
      oldGrid.deletePlayer(player);
      currGrid.addPlayer(player);

      Map<Integer, Grid> oldArea = getInterestAreaByPos(oldPosX, oldPosY);
      Map<Integer, Grid> currArea = getInterestAreaByPos(posX, posY);

      oldArea.forEach((gridId, grid) -> {
        if (!currArea.containsKey(gridId)) {
          lostGrids.put(gridId, grid);
        }
      });

      currArea.forEach((gridId, grid) -> {
        if (!oldArea.containsKey(gridId)) {
          bornGrids.put(gridId, grid);
        }
      });
    }
    Map<Integer, Grid> aoiGrids = getInterestAreaByPos(posX, posY);
    return new MoveResult(lostGrids, bornGrids, aoiGrids, isCrossGrid);
  }

  private void addPlayerByPos(String player, int posX, int posY) {
    if (isPosInvalid(posX, posY)) {
      String msg = String.format("AddPlayerByPos pos is illegal:(posx:%s, posY:%s)", posX, posY);
      throw new IllegalArgumentException(msg);
    }
    Grid grid = getGridByPos(posX, posY);
    grid.addPlayer(player);
  }

  private void addPlayerByGridId(String player, int gridId) {
    if (!allGrids.containsKey(gridId)) {
      String msg = String.format("AddPlayerByGridId gridId is illegal:(gridId:%s)", gridId);
      throw new IllegalArgumentException(msg);
    }
    Grid grid = allGrids.get(gridId);
    grid.addPlayer(player);
  }

  // 根据坐标获取grid
  private Grid getGridByPos(int posX, int posY) {
    if (isPosInvalid(posX, posY)) {
      String msg = String.format("GetGridByPos pos is illegal:(posx:%s, posY:%s)", posX, posY);
      throw new IllegalArgumentException(msg);
    }
    Pair<Integer> rowCol = getRowColByPos(posX, posY);
    Grid result = getGridByRowCol(rowCol.getFirst(), rowCol.getSecond());
    if (result == null) {
      String msg = String.format("GetGridByPos grid is null:(posx:%s, posY:%s, row:%s, col:%s)",
          posX, posY, rowCol.getFirst(), rowCol.getSecond());
      throw new IllegalArgumentException(msg);
    }
    return result;
  }

  // 根据xy坐标位置，获取行列
  private Pair<Integer> getRowColByPos(int posX, int posY) {
    if (isPosInvalid(posX, posY)) {
      String msg = String.format("GetRowColByPos pos is illegal:(posx:%s, posY:%s)", posX, posY);
      throw new IllegalArgumentException(msg);
    }
    int col = (int) Math.ceil(posX * 1.0 / this.lenX);
    int row = (int) Math.ceil(posY * 1.0 / this.lenY);
    if (col == 0) {
      col += 1;
    }
    if (row == 0) {
      row += 1;
    }
    return new Pair<>(row, col);
  }

  // 通过行列，获取grid
  private Grid getGridByRowCol(int row, int col) {
    int gridId = col + (row - 1) * this.col;
    return getGridById(gridId);
  }

  // 通过id获取grid
  private Grid getGridById(int gridId) {
    if (!this.allGrids.containsKey(gridId)) {
      throw new IllegalArgumentException("GridId:" + gridId);
    }
    return this.allGrids.get(gridId);
  }

  // 获取对输入坐标感兴趣的grids
  // 即就是包含当前坐标在内的九宫格范围
  private Map<Integer, Grid> getInterestAreaByPos(int posX, int posY) {
    if (isPosInvalid(posX, posY)) {
      String msg = String.format("GetInterestAreaByPos pos is illegal:(posx:%s, posY:%s)", posX, posY);
      throw new IllegalArgumentException(msg);
    }
    Pair<Integer> rowCol = getRowColByPos(posX, posY);
    // 当前格子
    Grid currGrid = getGridByRowCol(rowCol.getFirst(), rowCol.getSecond());
    // 创建最终返回的结果集合
    Map<Integer, Grid> grids = new HashMap<>();
    // 中心格子
    Map<Integer, Grid> midGrids = new HashMap<>();

    grids.put(currGrid.getGridId(), currGrid);
    // 以当前格子为中心
    midGrids.put(currGrid.getGridId(), currGrid);

    int col = rowCol.getSecond();
    int row = rowCol.getFirst();
    // 中间左边
    if (col > 1) {
      Grid temp = getGridByRowCol(row, col - 1);
      grids.put(temp.getGridId(), temp);
      midGrids.put(temp.getGridId(), temp);
    }

    // 中间右边
    if (col < this.col) {
      Grid temp = getGridByRowCol(row, col + 1);
      grids.put(temp.getGridId(), temp);
      midGrids.put(temp.getGridId(), temp);
    }

    // 遍历中间，搜索上下行
    midGrids.values().forEach(grid -> {
      if (grid.getRow() > 1) {
        Grid temp = getGridByRowCol(grid.getRow() - 1, grid.getCol());
        grids.put(temp.getGridId(), temp);
      }
      if (grid.getRow() < this.row) {
        Grid temp = getGridByRowCol(grid.getRow() + 1, grid.getCol());
        grids.put(temp.getGridId(), temp);
      }
    });
    return grids;
  }
}
