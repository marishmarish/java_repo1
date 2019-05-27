package ru.stqa.pft.sandbox;

public class DistanceBetweenPoints {
    public static void main(String[] args) {
        Point pointFirst = new Point(5, 4);
        Point pointSecond = new Point(1, 1);
        System.out.println("Distance between 2 points: " + distance(pointFirst, pointSecond));
        double distance = pointSecond.distance(pointFirst);
        System.out.println("Distance between 2 points: " + distance);

    }

    public static double distance(Point p1, Point p2) {
        double xResult = p1.x - p2.x;
        double yResult = p1.y - p2.y;
        double sumXY = xResult * xResult + yResult * yResult;
        return Math.sqrt(sumXY);
    }
}
