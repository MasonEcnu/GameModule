package com.mason.practice.uno.model.card;

import com.mason.practice.uno.interfaces.GameConstants;
import com.mason.practice.uno.view.UNOCard;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by mwu on 2020/3/12
 * This Class contains standard 108-Card stack
 */
public class CardDeck implements GameConstants {

    private final LinkedList<NumberCard> numberCards;
    private final LinkedList<ActionCard> actionCards;
    private final LinkedList<WildCard> wildCards;

    private LinkedList<UNOCard> unoCards;

    public CardDeck() {

        //Initialize Cards
        numberCards = new LinkedList<>();
        actionCards = new LinkedList<>();
        wildCards = new LinkedList<>();

        unoCards = new LinkedList<>();

        addCards();
        addCardListener();
    }


    //Create 108 cards for this CardDeck
    private void addCards() {
        for (Color color : UNO_COLORS) {

            //Create 76 NumberCards --> doubles except 0s.
            for (int num : UNO_NUMBERS) {
                int i = 0;
                do {
                    unoCards.add(new NumberCard(color, Integer.toString(num)));
                    i++;
                } while (num != 0 && i < 2);
            }

            //Create 24 ActionCards --> everything twice
            for (String type : ActionTypes) {
                for (int i = 0; i < 2; i++)
                    unoCards.add(new ActionCard(color, type));
            }
        }

        for (String type : WildTypes) {
            for (int i = 0; i < 4; i++) {
                unoCards.add(new WildCard(type));
            }
        }

    }

    //Cards have MouseListener
    private void addCardListener() {
        for (UNOCard card : unoCards)
            card.addMouseListener(GameConstants.CARDLISTENER);
    }

    public LinkedList<UNOCard> getCards() {
        return unoCards;
    }
}
