package com.mason.practice.uno.model.game;

import com.mason.practice.uno.interfaces.GameConstants;
import com.mason.practice.uno.model.card.WildCard;
import com.mason.practice.uno.view.UNOCard;

import javax.swing.*;
import java.util.Stack;

/**
 * Created by mwu on 2020/3/12
 */
public class Game implements GameConstants {

    private Player[] players;
    private boolean isOver;
    private int gameMode;

    private PC pc;
    private Dealer dealer;
    private Stack<UNOCard> cardStack;


    public Game(int mode) {

        gameMode = mode;

        //Create players
        String name = (gameMode == MANUAL) ? JOptionPane.showInputDialog("Player 1") : "PC";
        String name2 = JOptionPane.showInputDialog("Player 2");

        if (gameMode == vsPC)
            pc = new PC();

        Player player1 = (gameMode == vsPC) ? pc : new Player(name);
        Player player2 = new Player(name2);
        player2.toggleTurn();                //Initially, player2's turn

        players = new Player[]{player1, player2};

        //Create Dealer
        dealer = new Dealer();
        cardStack = dealer.shuffle();
        dealer.spreadOut(players);

        isOver = false;
    }

    public Player[] getPlayers() {
        return players;
    }

    public UNOCard getCard() {
        return dealer.getCard();
    }

    public void removePlayedCard(UNOCard playedCard) {

        for (Player p : players) {
            if (p.hasCard(playedCard)) {
                p.removeCard(playedCard);

                if (p.getTotalCards() == 1 && p.getSaidUNO()) {
                    infoPanel.setError(p.getName() + " Forgot to say UNO");
                    p.obtainCard(getCard());
                    p.obtainCard(getCard());
                } else if (p.getTotalCards() > 2) {
                    p.setSaidUNOFalse();
                }
            }
        }
    }

    //give player a card
    public void drawCard(UNOCard topCard) {

        boolean canPlay = false;

        for (Player p : players) {
            if (p.isMyTurn()) {
                UNOCard newCard = getCard();
                p.obtainCard(newCard);
                canPlay = canPlay(topCard, newCard);
                break;
            }
        }

        if (!canPlay)
            switchTurn();
    }

    public void switchTurn() {
        for (Player p : players) {
            p.toggleTurn();
        }
        whoseTurn();
    }

    //Draw cards x times
    public void drawPlus(int times) {
        for (Player p : players) {
            if (!p.isMyTurn()) {
                for (int i = 1; i <= times; i++)
                    p.obtainCard(getCard());
            }
        }
    }

    //response whose turn it is
    public void whoseTurn() {

        for (Player p : players) {
            if (p.isMyTurn()) {
                infoPanel.updateText(p.getName() + "'s Turn");
                System.out.println(p.getName() + "'s Turn");
            }
        }
        infoPanel.setDetail(playedCardsSize(), remainingCards());
        infoPanel.repaint();
    }

    //return if the game is over
    public boolean isOver() {

        if (cardStack.isEmpty()) {
            isOver = true;
            return isOver;
        }

        for (Player p : players) {
            if (!p.hasCards()) {
                isOver = true;
                break;
            }
        }

        return isOver;
    }

    private int remainingCards() {
        return cardStack.size();
    }

    private int[] playedCardsSize() {
        int[] nr = new int[2];
        int i = 0;
        for (Player p : players) {
            nr[i] = p.totalPlayedCards();
            i++;
        }
        return nr;
    }

    //Check if this card can be played
    private boolean canPlay(UNOCard topCard, UNOCard newCard) {

        // Color or value matches
        if (topCard.getColor().equals(newCard.getColor())
                || topCard.getValue().equals(newCard.getValue()))
            return true;
            // if chosen wild card color matches
        else if (topCard.getType() == WILD)
            return ((WildCard) topCard).getWildColor().equals(newCard.getColor());

            // suppose the new card is a wild card
        else return newCard.getType() == WILD;

        // else
    }

    //Check whether the player said or forgot to say UNO
    public void checkUNO() {
        for (Player p : players) {
            if (p.isMyTurn()) {
                if (p.getTotalCards() == 1 && p.getSaidUNO()) {
                    infoPanel.setError(p.getName() + " Forgot to say UNO");
                    p.obtainCard(getCard());
                    p.obtainCard(getCard());
                }
            }
        }
    }

    public void setSaidUNO() {
        for (Player p : players) {
            if (p.isMyTurn()) {
                if (p.getTotalCards() == 2) {
                    p.saysUNO();
                    infoPanel.setError(p.getName() + " said UNO");
                }
            }
        }
    }

    public boolean isPCsTurn() {
        return pc.isMyTurn();
    }

    //if it's PC's turn, play it for pc
    public void playPC(UNOCard topCard) {

        if (pc.isMyTurn()) {
            boolean done = pc.play(topCard);

            if (!done)
                drawCard(topCard);
        }
    }
}
