package com.mason.practice.uno.view;

import com.mason.practice.uno.interfaces.GameConstants;
import com.mason.practice.uno.model.card.WildCard;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mwu on 2020/3/12
 */
class TablePanel extends JPanel implements GameConstants {

    private UNOCard topCard;
    private JPanel table;

    TablePanel(UNOCard firstCard) {
        setOpaque(false);
        setLayout(new GridBagLayout());

        topCard = firstCard;
        table = new JPanel();
        table.setBackground(new Color(64, 64, 64));

        setTable();
        setComponents();
    }

    private void setTable() {

        table.setPreferredSize(new Dimension(500, 200));
        table.setLayout(new GridBagLayout());

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        table.add(topCard, gridBag);
    }

    private void setComponents() {
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.insets = new Insets(0, 130, 0, 45);
        add(table, gridBag);

        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.anchor = GridBagConstraints.LINE_END;
        gridBag.gridwidth = GridBagConstraints.REMAINDER;
        gridBag.gridx = 1;
        gridBag.gridy = 0;
        gridBag.insets = new Insets(0, 1, 0, 1);
        add(infoPanel, gridBag);
    }

    void setPlayedCard(UNOCard playedCard) {
        table.removeAll();
        topCard = playedCard;
        setTable();

        setBackgroundColor(playedCard);
    }

    private void setBackgroundColor(UNOCard playedCard) {
        Color background;
        if (playedCard.getType() == WILD)
            background = ((WildCard) playedCard).getWildColor();
        else
            background = playedCard.getColor();

        table.setBackground(background);
    }
}
