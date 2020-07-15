package com.mason.game.db.base;

/**
 * Created by mwu on 2020/6/30
 */
public enum DbOp {
    SAVE,
    DELETE,
    UPDATE,
    SAVE_OR_UPDATE,
    /**
     * 表示放弃save，直接清除
     */
    ABORT_SAVE,
    /**
     * 表示需要替换对象实例
     */
    REPLACE,
}
