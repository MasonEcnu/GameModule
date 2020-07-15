package com.mason.game

import com.google.common.hash.Funnel
import com.google.common.hash.HashCode
import com.google.common.hash.HashFunction
import com.google.common.hash.Hashing
import com.mason.game.db.base.DbOp
import com.mason.game.db.base.EntityWrapper
import com.mason.game.db.base.EntityWrapperKey
import java.lang.Long.max

/**
 * Created by mwu on 2020/6/30
 */
class ModificationTracker private constructor(
    /** 被跟踪对象 */
    val entityWrapper: EntityWrapper<*>,
    /** 用于序列化对象 */
    private val funnel: Funnel<EntityWrapper<*>>,
    /** 当前时间 */
    now: Long
) {
    /** [entityWrapper]的key */
    val key: EntityWrapperKey = EntityWrapperKey(entityWrapper)

    /** 下次检查时间 */
    var nextCheckTime: Long = 0L

    /** 最后一次序列化字节的hash code */
    private var lastSerBytesHashCode: HashCode? = null

    /** 最后一次[EntityWrapper.hashCode]的值 */
    private var lastIntHashCode: Int = 0

    /** 连续检查[lastIntHashCode]并且无变化的次数 */
    private var intHashCodeEqualTimes: Short = 0

    /** 已经提交正在等待完成的操作 */
    var pendingOp: DbOp? = null

    /** 下一个需要执行的操作 */
    var nextOp: DbOp? = null

    /** 用于配合[TimerWheelWriteOnlyInMemoryDb]工作 */
    var timerId: Long = -1

    /** 已经累积的delay，只在使用[TimerWheelWriteOnlyInMemoryDb]时有效 */
    var cumulativeCheckDelayMillis: Long = 0L

    /** 随机修正的检查间隔，同时限制了最小的检查间隔 */
    val checkModIntervalMillis: Long = max(
        com.mason.game.utils.RandomUtil.between(1000L, 2000L),
        entityWrapper.getCheckModInterval().toMillis() + com.mason.game.utils.RandomUtil.between(-3000L, 3000L)
    )

    init {
        cleanup()
        updateNextCheckTime(now)
    }

    /**
     * 同步内部的hash值为最新值
     */
    fun cleanup() {
        lastIntHashCode = entityWrapper.hashCode()
        lastSerBytesHashCode = hash(entityWrapper)
    }

    fun hasPendingOp(): Boolean = pendingOp != null

    fun hasNextOp(): Boolean = nextOp != null

    fun updateNextCheckTime(now: Long) {
        nextCheckTime = now + checkModIntervalMillis
    }

    fun isKnownDirty(): Boolean = pendingOp != null || nextOp != null

    /** 完整序列化比较时的hash函数 */
    private val fullCheckHashFunction: HashFunction = Hashing.goodFastHash(128)

    private fun hash(ew: EntityWrapper<*>): HashCode {
        return fullCheckHashFunction.hashObject(ew, funnel)
    }
}
