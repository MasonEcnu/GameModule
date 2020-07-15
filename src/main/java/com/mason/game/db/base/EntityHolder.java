package com.mason.game.db.base;

import com.mason.game.utils.RandomUtil;

import java.io.Serializable;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Created by mwu on 2020/7/1
 */
public abstract class EntityHolder<T extends IEntity> implements EntityWrapper<T> {

    public T entity;

    protected abstract Long getCheckModIntervalSeconds();

    @Override
    public void wrap(T entity) {
        this.entity = entity;
    }

    private Duration floatingCheckInterval = null;

    @Override
    public Duration getCheckModInterval() {
        if (floatingCheckInterval == null) {
            // 随机浮动一定比例的时间，分散时间间隔相近的实体
            long baseMillis = TimeUnit.SECONDS.toMillis(getCheckModIntervalSeconds());
            floatingCheckInterval = Duration.ofMillis(baseMillis + RandomUtil.between(0L, baseMillis / 3) - baseMillis / 6);
        }
        return floatingCheckInterval;
    }

    @Override
    public Serializable getPrimaryKey() {
        return entity.primaryKey();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj.getClass() != this.getClass()) return false;
        return entity == ((EntityHolder) obj).entity;
    }

    @Override
    public int hashCode() {
        return entity.hashCode();
    }
}
