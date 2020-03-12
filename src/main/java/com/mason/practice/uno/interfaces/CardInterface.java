package com.mason.practice.uno.interfaces;

import java.awt.*;

/**
 * Created by mwu on 2020/3/12
 */
public interface CardInterface {
    int WIDTH = 50;
    int HEIGHT = 75;

    Dimension SMALL = new Dimension(WIDTH, HEIGHT);
    Dimension MEDIUM = new Dimension(WIDTH * 2, HEIGHT * 2);
    Dimension BIG = new Dimension(WIDTH * 3, HEIGHT * 3);

    // 默认Card尺寸
    Dimension CARD_SIZE = MEDIUM;

    // 默认偏移位置
    int OFFSET = 71;

    Color getColor();

    void setColor(Color color);

    String getValue();

    void setValue(String newValue);

    int getType();

    void setType(int newType);
}
