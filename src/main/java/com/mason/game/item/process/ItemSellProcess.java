package com.mason.game.item.process;

import com.mason.game.item.ItemManager;
import com.mason.game.item.manager.Item;
import com.mason.game.item.process.manager.ItemOperation;

/**
 * Created by mwu on 2019/12/19
 * 使用出售
 */
public class ItemSellProcess implements ItemOperation {

  @Override
  public void execute(ItemManager manager, Item item, int operateParam) {
    System.out.format("ItemSellProcess:%s\n", item);
    if (item.isSaleable()) {
      manager.sellItem(item, operateParam);
    } else {
      System.out.format("ItemSellProcess:%s can't be sell.", item.getItemId());
    }
  }
}