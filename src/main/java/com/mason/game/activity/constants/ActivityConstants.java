package com.mason.game.activity.constants;

import com.google.common.collect.Lists;
import com.mason.game.constans.Pair;
import com.mason.game.utils.RandomUtil;

import java.util.Collections;
import java.util.List;

/**
 * Created by mwu on 2020/1/2
 */
public class ActivityConstants {
    public static int ACTIVITY_PLAYER_MAX_NUM = 10;
    public static int ACTIVITY_CONFIG_MAX_NUM = 30;
    public static Pair<Integer> ACTIVITY_PLAYER_LEVEL_RANGE = new Pair<>(1, 60);
    public static Pair<Integer> ACTIVITY_PLAYER_CASTLE_LEVEL_RANGE = new Pair<>(1, 30);
    public static List<Integer> ACTIVITY_CONFIG_LEVEL = Lists.newArrayList(1, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55);
    public static List<Integer> ACTIVITY_CONFIG_CASTLE_LEVEL = Lists.newArrayList(1, 5, 10, 15, 20, 25);

    public static int randomConfigLevel() {
        List<Integer> temp = ACTIVITY_CONFIG_LEVEL;
        Collections.shuffle(temp);
        return temp.get(0);
    }

    public static int randomConfigCastleLevel() {
        List<Integer> temp = ACTIVITY_CONFIG_CASTLE_LEVEL;
        Collections.shuffle(temp);
        return temp.get(0);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            if (RandomUtil.between(ACTIVITY_PLAYER_LEVEL_RANGE) == 60) {
                System.out.println("Bingo Max Level");
            }
            if (RandomUtil.between(ACTIVITY_PLAYER_CASTLE_LEVEL_RANGE) == 30) {
                System.out.println("Bingo Max Castle Level");
            }
        }
    }
}
