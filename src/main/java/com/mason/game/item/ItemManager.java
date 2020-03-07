package com.mason.game.item;

import com.mason.game.item.effect.manager.InterfaceItemUseEffect;
import com.mason.game.item.effect.manager.ItemUseEffect;
import com.mason.game.item.manager.Item;
import com.mason.game.item.manager.ItemType;
import com.mason.game.item.process.manager.ItemOperateType;
import com.mason.game.item.process.manager.ItemOperation;

import java.util.*;

/**
 * Created by mwu on 2019/12/19
 * 道具管理器
 * 实际应用中，应该每个玩家对应一个管理器
 * 模拟时一切从简
 */
public class ItemManager {

    private List<Item> items = new ArrayList<>();
    private Random random = new Random();
    private int maxItemId = 0;
    private Timer timer = new Timer();
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            System.out.println("ItemManager--check item:" + items.size());
            checkItems();
        }
    };

    private ItemEffectManager effectManager = ItemEffectManager.getInstance();
    private ItemOperateManager operateManager = ItemOperateManager.getInstance();

    ItemManager() {
        initSomeItems();
        checkMaxQueueId();
        startTimer();
    }

    ItemManager(List<Item> items) {
        this.items = items;
        initSomeItems();
        checkMaxQueueId();
        startTimer();
    }

    private void checkMaxQueueId() {
        Optional<Item> result = items.stream().max(Comparator.comparingInt(Item::getItemId));
        result.ifPresent(item -> maxItemId = item.getItemId());
    }

    private void initSomeItems() {
        for (int i = 0; i < 10; i++) {
            List<ItemType> types = ItemType.valuesExceptZero();
            Collections.shuffle(types);
            ItemType type = types.get(0);

            List<ItemUseEffect> useEffect = ItemUseEffect.valuesExceptZero();
            Collections.shuffle(types);
            ItemUseEffect effect = useEffect.get(0);
            addItem(type.getType(), effect.getEffect());
        }
    }

    private void startTimer() {
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    private void checkItems() {
        List<Item> needToRemove = new ArrayList<>();
        items.forEach(item -> {
                    if (item != null) {
                        if (item.isExpired()) {
                            needToRemove.add(item);
                        }
                    }
                }
        );
        needToRemove.forEach(this::deleteItem);
    }

    void addItem(int itemType, int useEffect, long obtainTime, int lastTime, boolean saleable, boolean usable) {
        Item item = new Item(nextItemId(), itemType, useEffect, obtainTime, lastTime, saleable, usable);
        addItem(item);
    }

    void addItem(int itemType, int useEffect, long obtainTime, int lastTime, boolean saleable) {
        Item item = new Item(nextItemId(), itemType, useEffect, obtainTime, lastTime, saleable);
        addItem(item);
    }

    void addItem(int itemType, int useEffect, long obtainTime, int lastTime) {
        Item item = new Item(nextItemId(), itemType, useEffect, obtainTime, lastTime);
        addItem(item);
    }

    void addItem(int itemType, int useEffect, long obtainTime) {
        Item item = new Item(nextItemId(), itemType, useEffect, obtainTime);
        addItem(item);
    }

    void addItem(int itemType, int useEffect) {
        Item item = new Item(nextItemId(), itemType, useEffect);
        addItem(item);
    }

    private int nextItemId() {
        maxItemId++;
        return maxItemId;
    }

    private void addItem(Item item) {
        items.remove(item);
        items.add(item);
        checkMaxQueueId();
        System.out.format("New Add item:%s\n", item);
    }

    private void deleteItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void operateItem(ItemOperateType operateType, Item item) {
        operateItem(operateType, item, 1);
    }

    public void operateItem(ItemOperateType operateType, Item item, int operateParam) {
        if (!items.contains(item)) {
            System.out.format("Item not exist:%s\n", item);
        } else {
            ItemOperation operation = operateManager.ITEM_OPERATE_PROCESS_MAP.get(operateType);
            if (operation != null) {
                operation.execute(this, item, operateParam);
            }
        }
    }

    public void useItem(Item item, int operateParam) {
        if (item.getCount() <= 0) {
            deleteItem(item);
        } else {
            for (int i = 0; i < operateParam; i++) {
                int count = item.getCount();
                if (count > 0) {
                    InterfaceItemUseEffect itemUseEffect = effectManager.ITEM_USE_EFFECT_MAP.get(item.getUseEffect());
                    itemUseEffect.check(item);
                    itemUseEffect.effect(item);
                    count--;
                    item.setCount(count);
                } else {
                    deleteItem(item);
                }
            }
        }
    }

    public void delItem(Item item) {
        deleteItem(item);
    }

    public void sellItem(Item item, int operateParam) {
        int count = item.getCount();
        int diff = count - operateParam;
        if (diff <= 0) {
            deleteItem(item);
        } else {
            item.setCount(diff);
        }
    }

}
