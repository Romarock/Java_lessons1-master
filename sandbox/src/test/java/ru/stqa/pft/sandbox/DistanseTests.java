package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class DistanseTests {


    @Test
    public void testDistanse() {


        Point p1 = new Point(5, 5);
        Point p2 = new Point(3, 3);

        Assert.assertEquals(p1.distance(p2), 2.8284271247461903);
    }


}
