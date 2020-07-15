package com.mason.game.db.base;

/**
 * Created by mwu on 2020/7/1
 */
public class CommonDaoHibernate implements CommonDao {

    @Override
    public void save(IEntity entity) {
        System.out.println("CommonDaoHibernate-save-entity:" + entity.toString());
    }

    @Override
    public void update(IEntity entity) {
        System.out.println("CommonDaoHibernate-update-entity:" + entity.toString());
    }

    @Override
    public void saveOrUpdate(IEntity entity) {
        System.out.println("CommonDaoHibernate-saveOrUpdate-entity:" + entity.toString());
    }

    @Override
    public void delete(IEntity entity) {
        System.out.println("CommonDaoHibernate-delete-entity:" + entity.toString());
    }

    @Override
    public void close() {
        System.out.println("CommonDaoHibernate-close");
    }
}
