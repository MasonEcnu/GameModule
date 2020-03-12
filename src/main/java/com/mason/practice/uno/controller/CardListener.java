package com.mason.practice.uno.controller;

import com.mason.practice.uno.view.UNOCard;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by mwu on 2020/3/12
 */
public class CardListener extends MouseAdapter {

    private UNOCard sourceCard;
    private Server server;

    public void setServer(Server server) {
        this.server = server;
    }

    public void mousePressed(MouseEvent e) {
        sourceCard = (UNOCard) e.getSource();

        try {
            if (server.canPlay)
                server.playThisCard(sourceCard);

        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        sourceCard = (UNOCard) e.getSource();
        Point p = sourceCard.getLocation();
        p.y -= 20;
        sourceCard.setLocation(p);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        sourceCard = (UNOCard) e.getSource();
        Point p = sourceCard.getLocation();
        p.y += 20;
        sourceCard.setLocation(p);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}