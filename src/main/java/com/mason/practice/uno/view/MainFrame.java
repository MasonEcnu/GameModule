package com.mason.practice.uno.view;

import com.mason.practice.uno.controller.Server;
import com.mason.practice.uno.interfaces.GameConstants;

import javax.swing.*;

/**
 * Created by mwu on 2020/3/12
 */
public class MainFrame extends JFrame implements GameConstants {

    private Session mainPanel;
    private Server server;

    public MainFrame() {
        server = new Server();
        CARDLISTENER.setServer(server);
        BUTTONLISTENER.setServer(server);

        mainPanel = server.getSession();
        add(mainPanel);
    }
}
