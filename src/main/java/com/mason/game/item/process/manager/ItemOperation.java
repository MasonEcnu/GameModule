package com.mason.game.item.process.manager;

import com.mason.game.item.manager.Item;

/**
 * Created by mwu on 2019/12/20
 */
public interface ItemOperation {

  void execute(ItemOperateType operateType, Item item, int operateParam);
}
