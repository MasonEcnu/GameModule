package com.mason.game.item.effect;

import com.mason.game.item.effect.manager.InterfaceItemUseEffect;
import com.mason.game.item.manager.Item;

/**
 * Created by mwu on 2019/12/20
 */
public class PortraitUnlockEffect implements InterfaceItemUseEffect {

    @Override
    public void check(Item item) {
        System.out.format("PortraitUnlockEffect--check--%s\n", item.getItemId());
    }

    @Override
    public void effect(Item item) {
        System.out.format("PortraitUnlockEffect--effect--%s\n", item.getItemId());
    }
}
