package com.mason.practice.uno.model.game;

import com.mason.practice.uno.interfaces.GameConstants;
import com.mason.practice.uno.model.card.WildCard;
import com.mason.practice.uno.view.UNOCard;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

/**
 * Created by mwu on 2020/3/12
 */
public class PC extends Player implements GameConstants {

    PC() {
        setName("PC");
        super.setCards();
    }

    public PC(Player player) {
    }

    //PC plays a card
    boolean play(UNOCard topCard) {

        boolean done = false;

        Color color = topCard.getColor();
        String value = topCard.getValue();

        if (topCard.getType() == WILD) {
            color = ((WildCard) topCard).getWildColor();
        }

        for (UNOCard card : getAllCards()) {

            if (card.getColor().equals(color) || card.getValue().equals(value)) {

                MouseEvent doPress = new MouseEvent(card, MouseEvent.MOUSE_PRESSED,
                        System.currentTimeMillis(),
                        InputEvent.BUTTON1_MASK, 5, 5, 1, true);
                card.dispatchEvent(doPress);

                MouseEvent doRelease = new MouseEvent(card, MouseEvent.MOUSE_RELEASED,
                        System.currentTimeMillis(),
                        InputEvent.BUTTON1_MASK, 5, 5, 1, true);
                card.dispatchEvent(doRelease);

                done = true;
                break;
            }
        }

        // if no card was found, play wild card
        if (!done) {
            for (UNOCard card : getAllCards()) {
                if (card.getType() == WILD) {
                    MouseEvent doPress = new MouseEvent(card,
                            MouseEvent.MOUSE_PRESSED,
                            System.currentTimeMillis(),
                            InputEvent.BUTTON1_MASK, 5, 5, 1, true);
                    card.dispatchEvent(doPress);

                    MouseEvent doRelease = new MouseEvent(card, MouseEvent.MOUSE_RELEASED,
                            System.currentTimeMillis(),
                            InputEvent.BUTTON1_MASK, 5, 5, 1, true);
                    card.dispatchEvent(doRelease);

                    done = true;
                    break;
                }
            }
        }

        if (getTotalCards() == 1 || getTotalCards() == 2)
            saysUNO();

        return done;
    }
}
