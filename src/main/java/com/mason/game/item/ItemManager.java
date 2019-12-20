package com.mason.game.item;

import com.mason.game.item.effect.manager.InterfaceItemUseEffect;
import com.mason.game.item.manager.Item;

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

  ItemManager() {
    checkMaxQueueId();
    startTimer();
  }

  private void checkMaxQueueId() {
    Optional<Item> result = items.stream().max(Comparator.comparingInt(Item::getItemId));
    result.ifPresent(item -> maxItemId = item.getItemId());
  }

  ItemManager(List<Item> items) {
    this.items = items;
    checkMaxQueueId();
    startTimer();
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

  public void operateItem(Item item) {

  }

  public void useItem(Item item) {
    if (item.getCount() <= 0) {
      deleteItem(item);
    } else {
      InterfaceItemUseEffect itemUseEffect = effectManager.ITEM_USE_EFFECT_MAP.get(item.getUseEffect());
      itemUseEffect.check(item);
      itemUseEffect.effect(item);
      deleteItem(item);
    }
  }

}
