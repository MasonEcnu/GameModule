package com.mason.game.aoi;

import java.util.HashMap;

import static com.mason.game.aoi.Constants.*;

/**
 * Created by mwu on 2020/3/3
 * https://github.com/yyhero/gridview/blob/24cfbae6c2d739b8c9a3ea082fce789981195e12/aoi_test.go#L19
 */
public class AoiTest {

  public static void main(String[] args) {
    // 4000*4000的大地图
    // 20*20的格子大小
    GridManager mapGrids = new GridManager(MAP_MIN_WITH, MAP_MAX_WITH, MAP_MIN_HEIGHT, MAP_MAX_HEIGHT, GRID_WITH, GRID_HEIGHT);

    // 初始化MAX_CREATURE_COUNT个player
    HashMap<String, Creature> creatures = new HashMap<>();
    for (int i = 1; i <= MAX_CREATURE_COUNT; i++) {
      int posX = Tools.random(1, MAP_MAX_WITH);
      int posY = Tools.random(1, MAP_MAX_HEIGHT);
      String cId = Tools.generateCreateName(i);
      Creature creature = new Creature(cId, posX, posY);
      creatures.put(cId, creature);
      mapGrids.enter(cId, posX, posY);
    }

    System.out.println("Init MAX_CREATURE_COUNT Creatures over");

    // all creatures move one time
    long start = System.currentTimeMillis();
    creatures.forEach((cid, creature) -> move(mapGrids, creature));
    long end = System.currentTimeMillis();
    System.out.format("All creatures move one time used %s seconds\n", (end - start) / SECOND_TO_MILLIS);

    // one creature move MAX_CREATURE_COUNT times
    start = System.currentTimeMillis();
    int ranCid = Tools.random(1, MAX_CREATURE_COUNT);
    Creature creature = creatures.get(Tools.generateCreateName(ranCid));
    for (int i = 1; i <= MAX_CREATURE_COUNT; i++) {
      move(mapGrids, creature);
    }
    end = System.currentTimeMillis();
    System.out.format("One creature move %s time used %s seconds\n", MAX_CREATURE_COUNT, (end - start) / SECOND_TO_MILLIS);
  }

  private static MoveResult move(GridManager mapGrids, Creature creature) {
//    System.out.println("================================================================================");
//    System.out.format("%s move from (%s, %s)\n", creature.getId(), creature.getPosX(), creature.getPosY());
    int oldPosX = creature.getPosX();
    int oldPosY = creature.getPosY();
    while (true) {
      int posX = oldPosX + Tools.random(-10, 10);
      int posY = oldPosY + Tools.random(-10, 10);
      if (mapGrids.isPosValid(posX, posY)) {
        creature.setPosX(posX);
        creature.setPosY(posY);
        break;
      }
    }
//    System.out.format("%s move to (%s, %s)\n", creature.getId(), creature.getPosX(), creature.getPosY());
    return mapGrids.move(oldPosX, oldPosY, creature.getPosX(), creature.getPosY(), creature.getId());
  }
}
