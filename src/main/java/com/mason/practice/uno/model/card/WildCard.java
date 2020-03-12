package com.mason.practice.uno.model.card;

import com.mason.practice.uno.view.UNOCard;

import java.awt.*;

/**
 * Created by mwu on 2020/3/12
 */
public class WildCard extends UNOCard {

    private int function = 0;
    private Color chosenColor;

    public WildCard() {
    }

    WildCard(String cardValue) {
        super(BLACK, cardValue, WILD);
    }

    public void useWildColor(Color wildColor) {
        chosenColor = wildColor;
    }

    public Color getWildColor() {
        return chosenColor;
    }

}
