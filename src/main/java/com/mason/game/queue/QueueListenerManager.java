package com.mason.game.queue;

import com.mason.game.queue.listener.*;
import com.mason.game.queue.manager.QueueType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mwu on 2019/12/18
 * 队列监听器管理器
 */
class QueueListenerManager {

  private static QueueListenerManager instance;

  static {
    try {
      instance = new QueueListenerManager();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static QueueListenerManager getInstance() {
    return instance;
  }

  private QueueListenerManager() {
  }

  final Map<QueueType, QueueListener> QUEUE_TYPE_LISTENER_MAP = new HashMap<QueueType, QueueListener>() {{
    put(QueueType.BUILDING_LEVEL_UP, new BuildingLevelQueueListener());
    put(QueueType.SCIENCE_LEVEL, new ScienceLevelQueueListener());
    put(QueueType.TRAIN_SOLIDER, new TrainSoliderQueueListener());
    put(QueueType.MARCH, new MarchQueueListener());
  }};
}
