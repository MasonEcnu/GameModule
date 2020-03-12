package com.mason.practice.uno.model.game;

import com.mason.practice.uno.view.UNOCard;

import java.util.LinkedList;

/**
 * Created by mwu on 2020/3/12
 */
public class Player {

    private String name = null;
    private boolean isMyTurn = false;
    private boolean saidUNO = false;
    private LinkedList<UNOCard> myCards;

    private int playedCards = 0;

    Player() {
        myCards = new LinkedList<>();
    }

    Player(String player) {
        setName(player);
        myCards = new LinkedList<>();
    }

    public String getName() {
        return this.name;
    }

    void setName(String newName) {
        name = newName;
    }

    void obtainCard(UNOCard card) {
        myCards.add(card);
    }

    public LinkedList<UNOCard> getAllCards() {
        return myCards;
    }

    public int getTotalCards() {
        return myCards.size();
    }

    public boolean hasCard(UNOCard thisCard) {
        return myCards.contains(thisCard);
    }

    void removeCard(UNOCard thisCard) {
        myCards.remove(thisCard);
        playedCards++;
    }

    int totalPlayedCards() {
        return playedCards;
    }

    void toggleTurn() {
        isMyTurn = !isMyTurn;
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

    boolean hasCards() {
        return !myCards.isEmpty();
    }

    boolean getSaidUNO() {
        return !saidUNO;
    }

    void saysUNO() {
        saidUNO = true;
    }

    void setSaidUNOFalse() {
        saidUNO = false;
    }

    void setCards() {
        myCards = new LinkedList<>();
    }
}
