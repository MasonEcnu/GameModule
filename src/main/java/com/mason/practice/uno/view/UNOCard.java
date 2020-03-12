package com.mason.practice.uno.view;

import com.mason.practice.uno.interfaces.CardInterface;
import com.mason.practice.uno.interfaces.UNOConstants;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

/**
 * Created by mwu on 2020/3/12
 */
public abstract class UNOCard extends JPanel implements CardInterface, UNOConstants {
    private Color cardColor = null;
    private String value = null;
    private int type = 0;

    private Border defaultBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.white, Color.gray);
    private Border focusedBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.black, Color.gray);

    public UNOCard() {
    }

    public UNOCard(Color cardColor, String cardValue, int cardType) {
        this.cardColor = cardColor;
        this.value = cardValue;
        this.type = cardType;

        this.setPreferredSize(CARD_SIZE);
        this.setBorder(defaultBorder);
        this.addMouseListener(new MouseHandler());
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        int cardWidth = CARD_SIZE.width;
        int cardHeight = CARD_SIZE.height;

        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, cardWidth, cardHeight);

        int margin = 5;
        graphics2D.setColor(cardColor);
        graphics2D.fillRect(margin, margin, cardWidth - 2 * margin, cardHeight - 2 * margin);

        graphics2D.setColor(Color.white);
        AffineTransform org = graphics2D.getTransform();
        graphics2D.rotate(45, cardWidth * 3 / 4.0, cardHeight * 3 / 4.0);

        graphics2D.fillOval(0, cardHeight / 4, cardWidth * 3 / 5, cardHeight);
        graphics2D.setTransform(org);

        //Value in the center		
        Font defaultFont = new Font("Helvetica", Font.BOLD, cardWidth / 2 + 5);
        FontMetrics fm = this.getFontMetrics(defaultFont);
        int stringWidth = fm.stringWidth(value) / 2;
        int fontHeight = defaultFont.getSize() / 3;

        graphics2D.setColor(cardColor);
        graphics2D.setFont(defaultFont);
        graphics2D.drawString(value, cardWidth / 2 - stringWidth, cardHeight / 2 + fontHeight);

        // Value in the corner
        defaultFont = new Font("Helvetica", Font.ITALIC, cardWidth / 5);
        // todo 不晓得这三行有啥意义
        fm = this.getFontMetrics(defaultFont);
        stringWidth = fm.stringWidth(value) / 2;
        fontHeight = defaultFont.getSize() / 3;

        graphics2D.setColor(Color.white);
        graphics2D.setFont(defaultFont);
        graphics2D.drawString(value, 2 * margin, 5 * margin);
    }

    public void setCardSize(Dimension newSize) {
        this.setPreferredSize(newSize);
    }

    @Override
    public Color getColor() {
        return this.cardColor;
    }

    @Override
    public void setColor(Color newColor) {
        this.cardColor = newColor;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String newValue) {
        this.value = newValue;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int newType) {
        this.type = newType;
    }

    class MouseHandler extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            setBorder(focusedBorder);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBorder(defaultBorder);
        }
    }
}
