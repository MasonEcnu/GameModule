package com.mason.practice.uno.view;

import com.mason.practice.uno.model.game.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mwu on 2020/3/12
 */
public class Session extends JPanel {
    private PlayerPanel player1;
    private PlayerPanel player2;
    private TablePanel table;

    private Game game;

    public Session(Game newGame, UNOCard firstCard) {
        setPreferredSize(new Dimension(960, 720));
        setBackground(new Color(30, 36, 40));
        setLayout(new BorderLayout());

        game = newGame;

        setPlayers();
        table = new TablePanel(firstCard);
        player1.setOpaque(false);
        player2.setOpaque(false);

        add(player1, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        add(player2, BorderLayout.SOUTH);
    }

    private void setPlayers() {
        player1 = new PlayerPanel(game.getPlayers()[0]);
        player2 = new PlayerPanel(game.getPlayers()[1]);
    }

    public void refreshPanel() {
        player1.setCards();
        player2.setCards();

        table.revalidate();
        revalidate();
    }

    public void updatePanel(UNOCard playedCard) {
        table.setPlayedCard(playedCard);
        refreshPanel();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}