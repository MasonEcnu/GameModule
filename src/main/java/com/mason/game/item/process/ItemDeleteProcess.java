package com.mason.game.item.process;

import com.mason.game.item.ItemManager;
import com.mason.game.item.manager.Item;
import com.mason.game.item.process.manager.ItemOperation;

/**
 * Created by mwu on 2019/12/19
 */
public class ItemDeleteProcess implements ItemOperation {

    @Override
    public void execute(ItemManager manager, Item item, int operateParam) {
        System.out.format("ItemDeleteProcess:%s\n", item);
        manager.delItem(item);
    }
}
