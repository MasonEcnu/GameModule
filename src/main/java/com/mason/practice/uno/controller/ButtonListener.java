package com.mason.practice.uno.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mwu on 2020/3/12
 */
public class ButtonListener implements ActionListener {

    private Server myServer;

    public void setServer(Server server) {
        myServer = server;
    }

    public void drawCard() {
        if (myServer.canPlay)
            myServer.requestCard();
    }

    public void sayUNO() {
        if (myServer.canPlay)
            myServer.submitSaidUNO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
