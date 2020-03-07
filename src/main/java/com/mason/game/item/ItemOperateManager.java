package com.mason.game.item;

import com.mason.game.item.process.ItemDeleteProcess;
import com.mason.game.item.process.ItemSellProcess;
import com.mason.game.item.process.ItemUseProcess;
import com.mason.game.item.process.manager.ItemOperateType;
import com.mason.game.item.process.manager.ItemOperation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mwu on 2019/12/23
 */
class ItemOperateManager {

    private static ItemOperateManager instance;

    static {
        try {
            instance = new ItemOperateManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    final Map<ItemOperateType, ItemOperation> ITEM_OPERATE_PROCESS_MAP = new HashMap<ItemOperateType, ItemOperation>() {{
        put(ItemOperateType.USE, new ItemUseProcess());
        put(ItemOperateType.SELL, new ItemSellProcess());
        put(ItemOperateType.DEL, new ItemDeleteProcess());
    }};

    private ItemOperateManager() {
    }

    static ItemOperateManager getInstance() {
        return instance;
    }

}
