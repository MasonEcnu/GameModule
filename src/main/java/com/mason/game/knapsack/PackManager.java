package com.mason.game.knapsack;

import com.mason.game.item.ItemManager;
import com.mason.game.item.manager.Item;

import java.util.ArrayList;

/**
 * Created by mwu on 2020/3/12
 * 背包管理器
 * 背包里放道具
 * 理论上来说，背包里啥都能放
 */
public class PackManager {
    private ItemManager itemManager;

    // 背包容量
    private int capacity = 100;

    public PackManager() {
        itemManager = new ItemManager(new ArrayList<>());
    }

    public boolean addItem(Item item) {
        if (itemManager.currItemNums() < capacity) {
            itemManager.addItem(item);
            return true;
        } else {
            return false;
        }
    }
}
