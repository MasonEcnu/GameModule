package com.mason.game.queue.listener;

import com.mason.game.queue.manager.GameQueue;

/**
 * Created by mwu on 2019/12/18
 */
public class BuildingLevelQueueListener implements QueueListener {

  @Override
  public void onQueueFinished(GameQueue queue) {
    System.out.format("BuildingLevelQueue:%s finished.\n", queue);
  }

  @Override
  public void onQueueCancel(GameQueue queue) {
    System.out.format("BuildingLevelQueue:%s canceled.\n", queue);
  }
}
