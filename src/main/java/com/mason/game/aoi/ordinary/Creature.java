package com.mason.game.aoi.ordinary;

/**
 * Created by mwu on 2020/3/3
 */
public class Creature {
    private String id;
    private int posX;
    private int posY;

    public Creature(String id, int posX, int posY) {
        this.id = id;
        this.posX = posX;
        this.posY = posY;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "posX=" + posX +
                ", posY=" + posY +
                ", id='" + id + '\'' +
                '}';
    }
}
