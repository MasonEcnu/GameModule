package com.mason.game.db.tracker;

import com.google.common.hash.Funnel;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.mason.game.db.base.DbOp;
import com.mason.game.db.base.EntityWrapper;
import com.mason.game.utils.RandomUtil;

/**
 * Created by mwu on 2020/6/30
 */
@SuppressWarnings("UnstableApiUsage")
public class ModificationTracker {

    /**
     * 被跟踪对象
     */
    final EntityWrapper<?> entityWrapper;

    /**
     * 用于序列化对象
     */
    private final Funnel<EntityWrapper<?>> funnel;
    /**
     * 当前时间
     */
    Long now;

    private ModificationTracker(EntityWrapper<?> entityWrapper, Funnel<EntityWrapper<?>> funnel) {
        init();
        this.entityWrapper = entityWrapper;
        this.funnel = funnel;
    }

    /**
     * [entityWrapper]的key
     */
//    EntityWrapperKey key = new EntityWrapperKey(entityWrapper);
    /**
     * 下次检查时间
     */
    long nextCheckTime = 0L;
    /**
     * 最后一次序列化字节的hash code
     */
    private HashCode lastSerBytesHashCode = null;
    /**
     * 最后一次[EntityWrapper.hashCode]的值
     */
    private int lastIntHashCode = 0;
    /**
     * 连续检查[lastIntHashCode]并且无变化的次数
     */
    private short intHashCodeEqualTimes = 0;
    /**
     * 已经提交正在等待完成的操作
     */
    DbOp pendingOp = null;
    /**
     * 下一个需要执行的操作
     */
    DbOp nextOp = null;
    /**
     * 用于配合[TimerWheelWriteOnlyInMemoryDb]工作
     */
    long timerId = -1L;
    /**
     * 已经累积的delay，只在使用[TimerWheelWriteOnlyInMemoryDb]时有效
     */
    long cumulativeCheckDelayMillis = 0L;

    /**
     * 随机修正的检查间隔，同时限制了最小的检查间隔
     */
    final long checkModIntervalMillis = Math.max(
            RandomUtil.between(1000L, 2000L), 1
//            entityWrapper.getCheckModInterval().toMillis() + RandomUtil.between(-3000L, 3000L)
    );

    /**
     * 完整序列化比较时的hash函数
     */
    private final HashFunction fullCheckHashFunction = Hashing.goodFastHash(128);

    private void init() {
        cleanup();
        updateNextCheckTime(now);
    }

    private void updateNextCheckTime(Long now) {
        nextCheckTime = now + checkModIntervalMillis;
    }

    private void cleanup() {
        lastIntHashCode = entityWrapper.hashCode();
        lastSerBytesHashCode = hash(entityWrapper);
    }

    private HashCode hash(EntityWrapper<?> entityWrapper) {
        return fullCheckHashFunction.hashObject(entityWrapper, funnel);
    }
}
