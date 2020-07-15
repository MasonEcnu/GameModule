package com.mason.game.db.base;

import java.io.Serializable;

/**
 * Created by mwu on 2020/7/1
 * 顶层数据类接口
 */
public interface IEntity extends Serializable {
    Serializable primaryKey();
}
