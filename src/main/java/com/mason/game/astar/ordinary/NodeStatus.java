package com.mason.game.astar.ordinary;

/**
 * Created by mwu on 2020/3/4
 * 节点状态
 */
enum NodeStatus {
  NONE, // 节点不存在
  OPEN, // 节点在open list中
  CLOSE // 节点在close list中
}
