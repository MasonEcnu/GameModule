package com.mason.practice.aqs

import java.util.concurrent.locks.AbstractQueuedSynchronizer
import java.util.concurrent.locks.ReentrantLock

/**
 * Created by mwu on 2020/3/5
 */
class MyLock : AbstractQueuedSynchronizer() {

  override fun tryAcquireShared(arg: Int): Int {
    val lock = ReentrantLock()
    return super.tryAcquireShared(arg)
  }

  override fun tryRelease(arg: Int): Boolean {
    return super.tryRelease(arg)
  }

  override fun tryAcquire(arg: Int): Boolean {
    return super.tryAcquire(arg)
  }

  override fun tryReleaseShared(arg: Int): Boolean {
    return super.tryReleaseShared(arg)
  }

  override fun isHeldExclusively(): Boolean {
    return super.isHeldExclusively()
  }
}