package com.mason.game.item.process.manager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by mwu on 2019/12/20
 */
public enum ItemOperateType {
    DEFAULT(0),
    USE(1),  // 使用
    SELL(2), // 出售
    DEL(3);  // 删除

    private int operateType;

    ItemOperateType(int operateType) {
        this.operateType = operateType;
    }


    public static ItemOperateType valueOf(int operateType) {
        Optional<ItemOperateType> result = Arrays.stream(values()).filter(it -> it.operateType == operateType).findFirst();
        return result.orElse(ItemOperateType.DEFAULT);
    }

    public static List<ItemOperateType> valuesExceptZero() {
        return Arrays.stream(values()).filter(it -> it != DEFAULT).collect(Collectors.toList());
    }

    public int getOperateType() {
        return operateType;
    }
}
