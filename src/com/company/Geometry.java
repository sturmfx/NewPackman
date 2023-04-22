package com.company;

public class Geometry
{
    public static boolean intersectsCircleWithEdges(Point topLeft, Point topRight, Point bottomRight, Point bottomLeft, Point circleCenter, double radius)
    {

        if (isPointInsideCircle(topLeft, circleCenter, radius) ||
                isPointInsideCircle(topRight, circleCenter, radius) ||
                isPointInsideCircle(bottomRight, circleCenter, radius) ||
                isPointInsideCircle(bottomLeft, circleCenter, radius))
        {
            return true;
        }


        if (isLineIntersectingCircle(topLeft, topRight, circleCenter, radius) ||
                isLineIntersectingCircle(topRight, bottomRight, circleCenter, radius) ||
                isLineIntersectingCircle(bottomRight, bottomLeft, circleCenter, radius) ||
                isLineIntersectingCircle(bottomLeft, topLeft, circleCenter, radius))
        {
            return true;
        }

        return false;
    }

    private static boolean isPointInsideCircle(Point point, Point circleCenter, double radius)
    {
        double distance = distanceBetweenPoints(point, circleCenter);
        return distance <= radius;
    }

    private static boolean isLineIntersectingCircle(Point p1, Point p2, Point circleCenter, double radius)
    {
        double distance = distanceFromPointToLineSegment(p1, p2, circleCenter);
        return distance <= radius;
    }

    private static double distanceBetweenPoints(Point p1, Point p2)
    {
        double deltaX = p1.getX() - p2.getX();
        double deltaY = p1.getY() - p2.getY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    private static double distanceFromPointToLineSegment(Point p1, Point p2, Point p)
    {
        double lineLengthSquared = Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2);
        if (lineLengthSquared == 0.0)
        {
            return distanceBetweenPoints(p1, p);
        }
        double t = ((p.getX() - p1.getX()) * (p2.getX() - p1.getX()) + (p.getY() - p1.getY()) * (p2.getY() - p1.getY())) / lineLengthSquared;
        t = Math.max(0, Math.min(1, t));
        Point projection = new Point(p1.getX() + t * (p2.getX() - p1.getX()), p1.getY() + t * (p2.getY() - p1.getY()));
        return distanceBetweenPoints(p, projection);
    }

    public static boolean lineSegmentsIntersect(Point p1, Point q1, Point p2, Point q2)
    {
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4)
        {
            return true;
        }

        if (o1 == 0 && onSegment(p1, p2, q1)) return true;
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        return false;
    }

    private static int orientation(Point p, Point q, Point r)
    {
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX()) - (q.getX() - p.getX()) * (r.getY() - q.getY());

        if (val == 0) return 0;
        return (val > 0) ? 1 : 2;
    }

    private static boolean onSegment(Point p, Point q, Point r)
    {
        if (q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX()) &&
                q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY()))
        {
            return true;
        }
        return false;
    }
}

