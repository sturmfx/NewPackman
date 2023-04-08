package com.company;

public class Line
{
    private Point point1;
    private Point point2;

    public Line(Point p1, Point p2)
    {
        point1 = new Point(p1.getX(), p1.getY());
        point2 = new Point(p2.getX(), p2.getY());
    }

    public Line(double x1, double y1, double x2, double y2)
    {
        point1 = new Point(x1, y1);
        point2 = new Point(x2, y2);
    }

    public static boolean itersects(Line l1, Line l2)
    {
        boolean result = false;

        return result;
    }

}
