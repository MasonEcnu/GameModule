package com.mason.game.item.process.manager;

import com.mason.game.item.ItemManager;
import com.mason.game.item.manager.Item;

/**
 * Created by mwu on 2019/12/20
 */
public interface ItemOperation {

  void execute(ItemManager manager, Item item, int operateParam);
}
