package com.mason.game.queue.listener;

import com.mason.game.queue.manager.GameQueue;

/**
 * Created by mwu on 2019/12/17
 * 队列事件监听接口
 */
public interface QueueListener {
  /**
   * 当队列完成时触发
   *
   * @param queue 队列对象
   */
  void onQueueFinished(GameQueue queue);

  /**
   * 当队列取消时触发
   *
   * @param queue 队列对象
   */
  void onQueueCancel(GameQueue queue);
}
