package com.mason.game.queue;

import com.mason.game.queue.manager.GameQueue;
import com.mason.game.queue.manager.QueueType;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Created by mwu on 2019/12/18
 * 测试队列系统
 */
public class Test {
  public static void main(String[] args) throws Exception {
    QueueManager manager = new QueueManager();
    Random random = new Random();
    for (int i = 0; i < 10; i++) {
      List<QueueType> types = QueueType.valuesExceptZero();
      Collections.shuffle(types);
      QueueType type = types.get(0);
      manager.addGameQueue(type.type, Instant.now().getEpochSecond(), random.nextInt(10) + 10, 1);
    }
    for (int i = 0; i < 3; i++) {
      Thread.sleep(2000L);
      Optional<GameQueue> result = manager.getQueues().stream().findAny();
      result.ifPresent(manager::cancelGameQueue);
    }
  }
}
