package ru.stqa.pft.sandbox;

public class Point {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double distance(Point p1) {
        double xResult = p1.x - this.x;
        double yResult = p1.y - this.y;
        double sumXY = xResult * xResult + yResult * yResult;
        return Math.sqrt(sumXY);
    }
}
