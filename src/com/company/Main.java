package com.company;

public class Main
{

    public static void main(String[] args)
    {
        Point p1 = new Point(0,0);
        Point p2 = new Point(1,1);

        System.out.println(Line.distanceBetweenPoints(p1, p2));

    }
}
