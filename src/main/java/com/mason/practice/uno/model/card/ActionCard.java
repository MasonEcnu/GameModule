package com.mason.practice.uno.model.card;

import com.mason.practice.uno.view.UNOCard;

import java.awt.*;

/**
 * Created by mwu on 2020/3/12
 */
public class ActionCard extends UNOCard {

    private int function = 0;

    //Constructor
    public ActionCard() {
    }

    ActionCard(Color cardColor, String cardValue) {
        super(cardColor, cardValue, ACTION);
    }
}
