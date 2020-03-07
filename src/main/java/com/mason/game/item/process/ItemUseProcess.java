package com.mason.game.item.process;

import com.mason.game.item.ItemManager;
import com.mason.game.item.manager.Item;
import com.mason.game.item.process.manager.ItemOperation;

/**
 * Created by mwu on 2019/12/19
 * 使用道具
 */
public class ItemUseProcess implements ItemOperation {

    @Override
    public void execute(ItemManager manager, Item item, int operateParam) {
        System.out.format("ItemUseProcess:%s\n", item);
        if (item.isUsable()) {
            manager.useItem(item, operateParam);
        } else {
            System.out.format("ItemUseProcess:%s can't be used.", item.getItemId());
        }
    }
}
