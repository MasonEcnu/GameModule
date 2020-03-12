package com.mason.practice.uno.interfaces;

import com.mason.practice.uno.controller.ButtonListener;
import com.mason.practice.uno.controller.CardListener;
import com.mason.practice.uno.view.InfoPanel;

import java.awt.*;

/**
 * Created by mwu on 2020/3/12
 */
public interface GameConstants extends UNOConstants {
    int TOTAL_CARDS = 108;
    int FIRSTHAND = 8;

    Color[] UNO_COLORS = {RED, BLUE, GREEN, YELLOW};
    Color WILD_CARDCOLOR = BLACK;

    int[] UNO_NUMBERS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    String[] ActionTypes = {REVERSE, SKIP, DRAW2PLUS};
    String[] WildTypes = {W_COLOR_PICKER, W_DRAW4PLUS};

    int vsPC = 1;
    int MANUAL = 2;

    int[] GAMEMODES = {vsPC, MANUAL};

    CardListener CARDLISTENER = new CardListener();
    ButtonListener BUTTONLISTENER = new ButtonListener();

    InfoPanel infoPanel = new InfoPanel();
}
