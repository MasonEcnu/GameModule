package com.mason.game

import com.mason.game.db.base.HibernateSession
import com.mason.game.db.base.IEntity
import java.io.Serializable

/**
 * Created by mwu on 2020/7/1
 */
interface CommonDao {

    fun save(entity: IEntity)

    fun <T : IEntity> findById(clazz: Class<T>, id: Serializable): T?

    fun update(entity: IEntity)

    fun saveOrUpdate(entity: IEntity)

    fun delete(entity: IEntity)

    fun <R> findWithTransaction(query: (HibernateSession) -> R): R

    fun execWithTransaction(operate: (HibernateSession) -> Unit)

    fun close()
}