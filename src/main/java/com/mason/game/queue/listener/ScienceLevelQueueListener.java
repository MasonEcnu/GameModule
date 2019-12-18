package com.mason.game.queue.listener;

import com.mason.game.queue.manager.GameQueue;

/**
 * Created by mwu on 2019/12/18
 */
public class ScienceLevelQueueListener implements QueueListener {

  @Override
  public void onQueueFinished(GameQueue queue) {
    System.out.format("ScienceLevelQueue:%s finished.\n", queue);
  }

  @Override
  public void onQueueCancel(GameQueue queue) {
    System.out.format("ScienceLevelQueue:%s canceled.\n", queue);
  }
}
