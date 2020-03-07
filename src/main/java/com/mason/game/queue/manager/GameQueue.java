package com.mason.game.queue.manager;

import java.time.Instant;

/**
 * Created by mwu on 2019/12/17
 * 队列的数据结构
 */
public class GameQueue {
    private int queueId;  // 队列唯一id
    private QueueType queueType;  // 队列类型
    private long startTime; // 队列开始时间戳，单位：秒
    private int totalSeconds; // 队列持续时长
    private int speedupSeconds; // 队列加速时长
    private int firstParam; // 类型对应参数，比如如果是建筑队列，则对应建筑id

    public GameQueue(int queueId, int queueType, long startTime, int totalSeconds, int firstParam) {
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

    private int getLastSeconds() {
        int lastSeconds = totalSeconds - speedupSeconds;
        if (lastSeconds <= 0) {
            return 0;
        } else {
            return lastSeconds;
        }
    }

    public boolean isFinished() {
        long nowSeconds = Instant.now().getEpochSecond();
        return nowSeconds - (startTime + getLastSeconds()) >= 0;
    }

    /**
     * 加速队列
     *
     * @param seconds 加速的时间
     */
    public void speedUpQueue(int seconds) {
        this.speedupSeconds += seconds;
    }

    @Override
    public String toString() {
        return String.format("[GameQueue--queueId:%s, queueType:%s, startTime:%s, totalSeconds:%s, speedupSeconds:%s, firstParam:%s]",
                this.queueId, this.queueType, this.startTime, this.totalSeconds, this.speedupSeconds, this.firstParam);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GameQueue) {
            return ((GameQueue) obj).queueId == this.queueId;
        }
        return false;
    }

    public static void main(String[] args) {
        GameQueue queue = new GameQueue(1, 1, 1, 1, 1);
        System.out.println(queue);
        queue.speedUpQueue(1);
        System.out.println(queue);
        System.out.println(queue.isFinished());
    }
}
