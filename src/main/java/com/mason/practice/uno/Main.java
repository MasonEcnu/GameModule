package com.mason.practice.uno;

import com.mason.practice.uno.view.MainFrame;

import javax.swing.*;

/**
 * Created by mwu on 2020/3/12
 * 游戏启动的主类
 */
public class Main {

    public static void main(String[] args) {
        //Create Frame and invoke it.
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new MainFrame();
            frame.setVisible(true);
            frame.setResizable(false);
            frame.setLocation(200, 100);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}
