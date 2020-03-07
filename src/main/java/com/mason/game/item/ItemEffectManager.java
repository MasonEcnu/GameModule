package com.mason.game.item;

import com.mason.game.item.effect.CastleShieldEffect;
import com.mason.game.item.effect.PackageDropEffect;
import com.mason.game.item.effect.PlayerExpEffect;
import com.mason.game.item.effect.PortraitUnlockEffect;
import com.mason.game.item.effect.manager.InterfaceItemUseEffect;
import com.mason.game.item.effect.manager.ItemUseEffect;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mwu on 2019/12/20
 */
class ItemEffectManager {

    private static ItemEffectManager instance;

    static {
        try {
            instance = new ItemEffectManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static ItemEffectManager getInstance() {
        return instance;
    }

    private ItemEffectManager() {
    }

    final Map<ItemUseEffect, InterfaceItemUseEffect> ITEM_USE_EFFECT_MAP = new HashMap<ItemUseEffect, InterfaceItemUseEffect>() {{
        put(ItemUseEffect.PACKAGE_DROP, new PackageDropEffect());
        put(ItemUseEffect.CASTLE_SHIELD, new CastleShieldEffect());
        put(ItemUseEffect.PLAYER_EXP, new PlayerExpEffect());
        put(ItemUseEffect.PORTRAIT_UNLOCK, new PortraitUnlockEffect());
    }};
}
