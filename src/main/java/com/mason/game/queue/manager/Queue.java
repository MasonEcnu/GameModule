package com.mason.game.queue.manager;

/**
 * Created by mwu on 2019/12/17
 * 队列的数据结构
 */
public class Queue {
  private int queueId;
  private QueueType queueType;
  private long startTime;
  private int totalSeconds;
  private int speedupSeconds;
  private int firstParam;

  public Queue(int queueId, int queueType, long startTime, int totalSeconds, int firstParam) {
    this.queueId = queueId;
    this.queueType = QueueType.valueOf(queueType);
    if (this.queueType == QueueType.DEFAULT) {
      throw new RuntimeException("队列类型错误：(" + queueId + "," + queueType + ")");
    }
    this.startTime = startTime;
    this.totalSeconds = totalSeconds;
    this.speedupSeconds = 0;
    this.firstParam = firstParam;
  }

  public int getQueueId() {
    return queueId;
  }

  public QueueType getQueueType() {
    return queueType;
  }

  public long getStartTime() {
    return startTime;
  }

  public int getTotalSeconds() {
    return totalSeconds;
  }

  public int getSpeedupSeconds() {
    return speedupSeconds;
  }

  public int getFirstParam() {
    return firstParam;
  }

  /**
   * 加速队列
   *
   * @param seconds 加速的时间
   */
  public void speedUpQueue(int seconds) {
    this.speedupSeconds += seconds;
  }
}
