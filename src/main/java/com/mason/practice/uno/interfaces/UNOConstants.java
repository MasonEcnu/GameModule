package com.mason.practice.uno.interfaces;

import java.awt.*;

/**
 * Created by mwu on 2020/3/12
 */
public interface UNOConstants {
    // 颜色
    Color RED = new Color(192, 80, 77);
    Color BLUE = new Color(31, 73, 125);
    Color GREEN = new Color(0, 153, 0);
    Color YELLOW = new Color(255, 204, 0);

    Color BLACK = new Color(0, 0, 0);

    // 类型
    int NUMBERS = 1;
    int ACTION = 2;
    int WILD = 3;

    // 行动卡角色
    Character charREVERSE = (char) 8634;                            //Decimal
    Character charSKIP = (char) Integer.parseInt("2718", 16);    //Unicode

    // 行动卡功能
    String REVERSE = charREVERSE.toString();
    String SKIP = charSKIP.toString();
    String DRAW2PLUS = "2+";

    // 通配符功能
    String W_COLOR_PICKER = "W";
    String W_DRAW4PLUS = "4+";
}
