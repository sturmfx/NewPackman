package com.company;

import java.awt.*;
import java.util.Random;

public class Bot
{
    private double x;
    private double y;
    private double radius;
    Color color = Color.GREEN;
    private boolean eaten = false;
    private static Random random = new Random();

    public Bot(double x, double y, double radius)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public static Bot createBot(int maxX, int maxY, int r)
    {

        Bot result = new Bot(random.nextInt(maxX), random.nextInt(maxY), r);
        return result;
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

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
    }

    public boolean isEaten()
    {
        return eaten;
    }

    public void setEaten(boolean eaten)
    {
        this.eaten = eaten;
    }
}
