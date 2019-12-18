package com.mason.game.queue;

import com.mason.game.queue.listener.QueueListener;
import com.mason.game.queue.manager.GameQueue;

import java.util.*;

/**
 * Created by mwu on 2019/12/17
 * 队列管理器
 */
public class QueueManager {

  private List<GameQueue> queues = new ArrayList<>();
  private Random random = new Random();
  private int maxQueueId = 0;
  private Timer timer = new Timer();
  private TimerTask timerTask = new TimerTask() {
    @Override
    public void run() {
      System.out.println("QueueManager--check queue:" + queues.size());
      checkQueues();
    }
  };
  private QueueListenerManager listenerManager = QueueListenerManager.getInstance();

  private void checkQueues() {
    List<GameQueue> needToRemove = new ArrayList<>();
    randomSpeedGameQueue();
    queues.forEach(queue -> {
          if (queue != null) {
            if (queue.isFinished()) {
              QueueListener listener = listenerManager.QUEUE_TYPE_LISTENER_MAP.get(queue.getQueueType());
              if (listener != null) {
                listener.onQueueFinished(queue);
                needToRemove.add(queue);
              }
            }
          }
        }
    );
    needToRemove.forEach(this::deleteGameQueue);
  }

  private void randomSpeedGameQueue() {
    Optional<GameQueue> result = queues.stream().filter(queue -> !queue.isFinished()).findAny();
    int randomSeconds = random.nextInt(3) + 1;
    result.ifPresent(gameQueue -> gameQueue.speedUpQueue(randomSeconds));
  }

  QueueManager() {
    checkMaxQueueId();
    startTimer();
  }

  private void checkMaxQueueId() {
    Optional<GameQueue> result = queues.stream().max(Comparator.comparingInt(GameQueue::getQueueId));
    result.ifPresent(gameQueue -> maxQueueId = gameQueue.getQueueId());
  }

  QueueManager(List<GameQueue> queues) {
    this.queues = queues;
    checkMaxQueueId();
    startTimer();
  }

  private int nextQueueId() {
    maxQueueId += 1;
    return maxQueueId;
  }

  void addGameQueue(int queueType, long startTime, int totalSeconds, int firstParam) {
    GameQueue queue = new GameQueue(nextQueueId(), queueType, startTime, totalSeconds, firstParam);
    addGameQueue(queue);
  }

  private void addGameQueue(GameQueue queue) {
    queues.remove(queue);
    queues.add(queue);
    checkMaxQueueId();
    System.out.format("New Add queue:%s", queue);
    System.out.println();
  }

  private void deleteGameQueue(GameQueue queue) {
    queues.remove(queue);
  }

  void cancelGameQueue(GameQueue queue) {
    QueueListener listener = listenerManager.QUEUE_TYPE_LISTENER_MAP.get(queue.getQueueType());
    if (listener != null) {
      if (queue.isFinished()) {
        listener.onQueueFinished(queue);
      } else {
        listener.onQueueCancel(queue);
      }
    }
    deleteGameQueue(queue);
  }

  List<GameQueue> getQueues() {
    return queues;
  }

  private void startTimer() {
    timer.scheduleAtFixedRate(timerTask, 0, 1000);
  }

  private int getMaxQueueId() {
    return maxQueueId;
  }

  public static void main(String[] args) {
    List<GameQueue> queues = new ArrayList<GameQueue>() {{
      add(new GameQueue(1, 1, 1, 1, 1));
      add(new GameQueue(2, 1, 1, 1, 1));
      add(new GameQueue(3, 1, 1, 1, 1));
      add(new GameQueue(4, 1, 1, 1, 1));
    }};
    QueueManager manager = new QueueManager(queues);
    System.out.println(manager.getMaxQueueId());
    manager.addGameQueue(1, 1, 1, 1);
    System.out.println(manager.getMaxQueueId());
  }
}
