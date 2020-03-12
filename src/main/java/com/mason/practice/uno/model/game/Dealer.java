package com.mason.practice.uno.model.game;

import com.mason.practice.uno.interfaces.GameConstants;
import com.mason.practice.uno.model.card.CardDeck;
import com.mason.practice.uno.view.UNOCard;

import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

/**
 * Created by mwu on 2020/3/12
 */
public class Dealer implements GameConstants {

    private CardDeck cardDeck;
    private Stack<UNOCard> cardStack;

    Dealer() {
        this.cardDeck = new CardDeck();
    }

    //Shuffle cards
    public Stack<UNOCard> shuffle() {

        LinkedList<UNOCard> DeckOfCards = cardDeck.getCards();
        LinkedList<UNOCard> shuffledCards = new LinkedList<>();

        while (!DeckOfCards.isEmpty()) {
            int totalCards = DeckOfCards.size();

            Random random = new Random();
            int pos = (Math.abs(random.nextInt())) % totalCards;

            UNOCard randomCard = DeckOfCards.get(pos);
            DeckOfCards.remove(pos);
            shuffledCards.add(randomCard);
        }

        cardStack = new Stack<>();
        cardStack.addAll(shuffledCards);

        return cardStack;
    }

    //Spread cards to players - 8 each
    void spreadOut(Player[] players) {

        for (int i = 1; i <= FIRSTHAND; i++) {
            for (Player p : players) {
                p.obtainCard(cardStack.pop());
            }
        }
    }

    public UNOCard getCard() {
        return cardStack.pop();
    }
}
