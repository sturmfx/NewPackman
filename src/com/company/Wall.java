package com.company;

public class Wall
{
    private static int id_counter = 0;
    private int id;
    private Line[] lines = new Line[4];
    public Wall(Point click, int width, int height)
    {
        Point p1 = new Point(click.getX() - (width/2), click.getY() - (height/2));
        Point p2 = new Point(click.getX() + (width/2), click.getY() - (height/2));
        Point p3 = new Point(click.getX() + (width/2), click.getY() + (height/2));
        Point p4 = new Point(click.getX() - (width/2), click.getY() + (height/2));

        lines[0] = new Line(p1, p2);
        lines[1] = new Line(p2, p3);
        lines[2] = new Line(p3, p4);
        lines[3] = new Line(p4, p1);
        id = id_counter;
        id_counter++;
    }

    public Line[] getLines()
    {
        return lines;
    }

    public int getId() {
        return id;
    }
}
