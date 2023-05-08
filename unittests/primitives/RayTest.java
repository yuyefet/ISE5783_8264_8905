package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void findClosestPoint() {
        final Ray ray = new Ray(new Point(1,0,0), new Vector(-1,2,2));

        // EP
        //TC01: The point in the middle of the list is the closest one
        List<Point> pointList = List.of(new Point(1.7,1,0), new Point(1.5,-2,0), new Point(1.31,-0.62,-0.62), new Point(0.5,1,1), new Point(1.16,-0.32,-0.32));
        assertEquals(pointList.get(2), ray.findClosestPoint(pointList), "Bad point");

        // BVA
        //TC11: empty list
        pointList.clear();
        assertNull(ray.findClosestPoint(pointList), "The list is Empty!");

        //TC12: first point is the closest
        pointList = List.of(new Point(1.31,-0.62,-0.62), new Point(1.7,1,0), new Point(1.5,-2,0), new Point(0.5,1,1), new Point(1.16,-0.32,-0.32));
        assertEquals(pointList.get(0), ray.findClosestPoint(pointList), "Bad point");

        //TC13: last point is the closest
        pointList = List.of(new Point(1.7,1,0), new Point(1.5,-2,0), new Point(0.5,1,1), new Point(1.16,-0.32,-0.32), new Point(1.31,-0.62,-0.62));
        assertEquals(pointList.get(4), ray.findClosestPoint(pointList), "Bad point");

    }
}