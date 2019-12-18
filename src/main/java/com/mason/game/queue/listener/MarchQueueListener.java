package com.mason.game.queue.listener;

import com.mason.game.queue.manager.GameQueue;

/**
 * Created by mwu on 2019/12/18
 */
public class MarchQueueListener implements QueueListener {

  @Override
  public void onQueueFinished(GameQueue queue) {
    System.out.format("MarchQueue:%s finished.\n", queue);
  }

  @Override
  public void onQueueCancel(GameQueue queue) {
    System.out.format("MarchQueue:%s canceled.\n", queue);
  }
}
