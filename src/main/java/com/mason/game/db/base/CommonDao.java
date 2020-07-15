package com.mason.game.db.base;

/**
 * Created by mwu on 2020/7/1
 */
public interface CommonDao {

    void save(IEntity entity);

    void update(IEntity entity);

    void saveOrUpdate(IEntity entity);

    void delete(IEntity entity);

    void close();
}
