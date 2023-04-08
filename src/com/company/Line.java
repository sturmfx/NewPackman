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

     public static boolean doLinesIntersect(Point p1, Point p2, Point p3, Point p4) 
     {
        double det = (p2.getX() - p1.getX()) * (p4.getY() - p3.getY()) - (p4.getX() - p3.getX()) * (p2.getY() - p1.getY());
        if (det == 0) 
        {
            return false;
        }
        double A = ((p4.getX() - p3.getX()) * (p1.getY() - p3.getY()) - (p4.getY() - p3.getY()) * (p1.getX() - p3.getX())) / det;
        double B = ((p2.getX() - p1.getX()) * (p1.getY() - p3.getY()) - (p2.getY() - p1.getY()) * (p1.getX() - p3.getX())) / det;
        return A >= 0 && A <= 1 && B >= 0 && B <= 1;
    }

}
