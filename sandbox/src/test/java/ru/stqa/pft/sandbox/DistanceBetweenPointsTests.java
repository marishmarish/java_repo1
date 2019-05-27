package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceBetweenPointsTests {

    @Test
    public void testDistanceBetweenTwoPointsA() {
        Point pointFirst = new Point(5, 4);
        Point pointSecond = new Point(1, 1);
        assert pointFirst.distance(pointSecond) == 5.0;
    }


    @Test
    public void testDistanceBetweenTwoPointsB() {
        Point pointFirst = new Point(5, 4);
        Point pointSecond = new Point(1, 1);
        Assert.assertEquals(pointFirst.distance(pointSecond), 5.0);
    }

    @Test
    public void testDistanceBetweenTwoPointsC() {
        Point pointFirst = new Point(5, 4);
        Point pointSecond = new Point(1, 1);
        Assert.assertEquals(DistanceBetweenPoints.distance(pointFirst,pointSecond), 5.0);
    }

}

