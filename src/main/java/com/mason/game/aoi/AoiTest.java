package com.mason.game.aoi;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

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

    // 初始化20000个player
    HashMap<String, Creature> creatures = new HashMap<>();
    for (int i = 1; i <= 20000; i++) {
      int posX = Tools.random(1, MAP_MAX_WITH);
      int posY = Tools.random(1, MAP_MAX_HEIGHT);

      String cId = Tools.generateCreateName(i);
      Creature creature = new Creature(cId, posX, posY);
      creatures.put(cId, creature);
      mapGrids.enter(cId, posX, posY);
    }

    System.out.println("Init 20000 Creatures over");

    // all creatures move one time
    long start = System.currentTimeMillis();
    AtomicInteger index = new AtomicInteger();
    creatures.forEach((cid, creature) -> {
      int oldPosX = creature.getPosX();
      int oldPosY = creature.getPosY();
      int posX, posY;
      while (true) {
        posX = oldPosX + Tools.random(-5, 5);
        posY = oldPosY + Tools.random(-5, 5);
        if (mapGrids.isPosValid(posX, posY)) {
          creature.setPosX(posX);
          creature.setPosY(posY);
          break;
        }
      }
      System.out.println(index.getAndIncrement());
      mapGrids.move(oldPosX, oldPosY, posX, posY, cid);
    });
    long end = System.currentTimeMillis();
    System.out.format("All creatures move one time used %s seconds", (end - start) / SECOND_TO_MILLIS);

    // one creature move 20000 times
    start = System.currentTimeMillis();
    int ranCid = Tools.random(1, 20000);
    Creature creature = creatures.get(Tools.generateCreateName(ranCid));
    for (int i = 1; i <= 20000; i++) {
      int oldPosX = creature.getPosX();
      int oldPosY = creature.getPosY();
      int posX, posY;
      while (true) {
        posX = oldPosX + Tools.random(-10, 10);
        posY = oldPosY + Tools.random(-10, 10);
        if (mapGrids.isPosValid(posX, posY)) {
          creature.setPosX(posX);
          creature.setPosY(posY);
          break;
        }
      }
      MoveResult moveResult = mapGrids.move(oldPosX, oldPosY, posX, posY, creature.getId());
      System.out.println(moveResult);
    }
    end = System.currentTimeMillis();
    System.out.format("One creature move 20000 time used %s seconds", (end - start) / SECOND_TO_MILLIS);
  }
}
