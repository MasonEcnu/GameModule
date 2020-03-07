package com.mason.game.utils;

/**
 * Created by mwu on 2019/12/24
 * 计算工具
 */
public class CalcUtil {

    /**
     * 计算faciend*multiplier
     *
     * @param faciend    被乘数
     * @param multiplier 乘数
     * @return int 结果四舍五入
     */
    public static int multiplyRound(int faciend, float multiplier) {
        return Math.round(faciend * multiplier);
    }

    public static int multiplyRound(int faciend, int multiplier) {
        return Math.round(faciend * multiplier);
    }

    public static long multiplyRound(int faciend, double multiplier) {
        return Math.round(faciend * multiplier);
    }

    /**
     * 计算faciend*multiplier
     *
     * @param faciend    被乘数
     * @param multiplier 乘数
     * @return int 向下取整
     */
    public static int multiplyFloor(int faciend, float multiplier) {
        return (int) Math.floor(faciend * multiplier);
    }

    public static int multiplyFloor(int faciend, int multiplier) {
        return (int) Math.floor(faciend * multiplier);
    }

    public static int multiplyFloor(int faciend, double multiplier) {
        return (int) Math.floor(faciend * multiplier);
    }

    /**
     * 计算faciend*multiplier
     *
     * @param faciend    被乘数
     * @param multiplier 乘数
     * @return int 向上取整
     */
    public static int multiplyCeil(int faciend, int multiplier) {
        return (int) Math.ceil(faciend * multiplier);
    }

    public static int multiplyCeil(int faciend, float multiplier) {
        return (int) Math.ceil(faciend * multiplier);
    }

    public static int multiplyCeil(int faciend, double multiplier) {
        return (int) Math.ceil(faciend * multiplier);
    }
}
