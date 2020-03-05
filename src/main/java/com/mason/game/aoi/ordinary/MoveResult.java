package com.mason.game.aoi.ordinary;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * Created by mwu on 2020/3/3
 */
public class MoveResult {
  private Map<Integer, Grid> lostGrids;
  private Map<Integer, Grid> bornGrids;
  private Map<Integer, Grid> aoiGrids;
  private boolean isCrossGrid;

  public MoveResult(Map<Integer, Grid> lostGrids, Map<Integer, Grid> bornGrids, Map<Integer, Grid> aoiGrids, boolean isCrossGrid) {
    this.lostGrids = lostGrids;
    this.bornGrids = bornGrids;
    this.aoiGrids = aoiGrids;
    this.isCrossGrid = isCrossGrid;
  }

  public Map<Integer, Grid> getLostGrids() {
    return lostGrids;
  }

  public Map<Integer, Grid> getBornGrids() {
    return bornGrids;
  }

  public Map<Integer, Grid> getAoiGrids() {
    return aoiGrids;
  }

  public boolean isCrossGrid() {
    return isCrossGrid;
  }

  @Override
  public String toString() {
    return "MoveResult{" +
        "lostGrids=" + JSON.toJSONString(lostGrids) +
        ", bornGrids=" + JSON.toJSONString(bornGrids) +
        ", aoiGrids=" + JSON.toJSONString(aoiGrids) +
        ", isCrossGrid=" + isCrossGrid +
        '}';
  }
}
