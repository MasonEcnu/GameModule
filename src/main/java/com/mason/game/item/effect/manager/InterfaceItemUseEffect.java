package com.mason.game.item.effect.manager;

import com.mason.game.item.manager.Item;

/**
 * Created by mwu on 2019/12/20
 */
public interface InterfaceItemUseEffect {
    void check(Item item);

    void effect(Item item);
}
