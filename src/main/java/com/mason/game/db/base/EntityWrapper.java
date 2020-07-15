package com.mason.game.db.base;

import java.io.Serializable;
import java.time.Duration;

/**
 * Created by mwu on 2020/7/1
 */
public interface EntityWrapper<T extends IEntity> {

    /**
     * 获取Entity的主键
     *
     * @return 主键
     */
    Serializable getPrimaryKey();

    /**
     * 转为[IEntity]对象，用于同步数据到数据库中
     * <p>
     * **注意：会在IO线程中读取数据，注意线程安全问题，[EntityWrapper]中如果持有entity引用，实现此方法需要复制，不能直接返回内部引用**
     */
    T toEntity();

    /**
     * 使用指定的entity初始化
     */
    void wrap(T entity);

    /**
     * @return 检查变化的时间间隔
     */
    Duration getCheckModInterval();
}
