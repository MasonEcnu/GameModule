package com.mason.game.item;

import com.mason.game.item.manager.Item;
import com.mason.game.item.process.manager.ItemOperateType;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by mwu on 2019/12/23
 */
public class TestItem {

    public static void main(String[] args) {
        ItemManager manager = new ItemManager();
        for (int i = 0; i < 5; i++) {
            Optional<Item> optionalItem = manager.getItems().stream().findAny();
            List<ItemOperateType> operateTypes = ItemOperateType.valuesExceptZero();
            Collections.shuffle(operateTypes);
            ItemOperateType operateType = operateTypes.get(0);
            optionalItem.ifPresent(item -> manager.operateItem(operateType, item));
        }
    }
}
