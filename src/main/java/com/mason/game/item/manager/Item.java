package com.mason.game.item.manager;

import com.mason.game.item.effect.manager.ItemUseEffect;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

/**
 * Created by mwu on 2019/12/19
 * 道具数据结构
 */
public class Item implements Serializable {

  private int itemId;
  private ItemType itemType;  // 好像是个无关紧要的属性
  private ItemUseEffect useEffect;
  private long obtainTime; // 道具获得时间戳，单位：秒
  private int lastTime; // lastTime>0：表示道具为限时道具，单位：秒
  private boolean saleable;  // 是否可出售
  private boolean usable;  // 是否可使用
  private int count;  // 道具数量

  public Item(int itemId, int itemType, int useEffect, long obtainTime, int lastTime, boolean saleable, boolean usable, int count) {
    this.itemId = itemId;
    this.itemType = ItemType.valueOf(itemType);
    if (this.itemType == ItemType.DEFAULT) {
      throw new RuntimeException("道具类型错误：(" + itemId + "," + itemType + ")");
    }
    this.useEffect = ItemUseEffect.valueOf(useEffect);
    if (this.useEffect == ItemUseEffect.DEFAULT) {
      throw new RuntimeException("道具使用效果错误：(" + itemId + "," + useEffect + ")");
    }
    this.obtainTime = obtainTime;
    this.lastTime = lastTime;
    this.saleable = saleable;
    this.usable = usable;
    this.count = count;
  }

  public Item(int itemId, int itemType, int useEffect) {
    this(itemId, itemType, useEffect, Instant.now().getEpochSecond(), 0, true, true, 1);
  }

  public Item(int itemId, int itemType, int useEffect, long obtainTime) {
    this(itemId, itemType, useEffect, obtainTime, 0, true, true, 1);
  }

  public Item(int itemId, int itemType, int useEffect, long obtainTime, int lastTime) {
    this(itemId, itemType, useEffect, obtainTime, lastTime, true, true, 1);
  }

  public Item(int itemId, int itemType, int useEffect, long obtainTime, int lastTime, boolean saleable) {
    this(itemId, itemType, useEffect, obtainTime, lastTime, saleable, true, 1);
  }

  public Item(int itemId, int itemType, int useEffect, long obtainTime, int lastTime, boolean saleable, boolean usable) {
    this(itemId, itemType, useEffect, obtainTime, lastTime, saleable, usable, 1);
  }

  public int getItemId() {
    return itemId;
  }

  public ItemType getItemType() {
    return itemType;
  }

  public ItemUseEffect getUseEffect() {
    return useEffect;
  }

  public int getLastTime() {
    return lastTime;
  }

  public boolean isSaleable() {
    return saleable;
  }

  public boolean isUsable() {
    return usable;
  }

  public long getObtainTime() {
    return obtainTime;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public boolean isExpired() {
    long nowSeconds = Instant.now().getEpochSecond();
    return nowSeconds - (obtainTime + getLastTime()) >= 0;
  }

  @Override
  public String toString() {
    Date obtainTime = Date.from(Instant.ofEpochSecond(this.obtainTime));
    return String.format("[Item--itemId:%s, itemType:%s, useEffect:%s, obtainTime:%s, lastTime:%s, saleable:%s, usable:%s, count:%s]",
        this.itemId, this.itemType, this.useEffect, obtainTime, this.lastTime, this.saleable, this.usable, this.count);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    Item other = (Item) obj;
    return this.itemId == other.itemId;
  }
}
