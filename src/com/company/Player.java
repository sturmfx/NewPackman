package com.company;

import java.awt.*;

public class Player
{
    private static int id_counter = 0;
    private int id;

    private double x;
    private double y;

    private int width;
    private int height;

    Color color = Color.RED;

    public Player(double x, double y)
    {
        this.x = x;
        this.y = y;
        this.width = 50;
        this.height = 50;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
