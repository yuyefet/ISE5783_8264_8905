package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: All the points are the same
        Point[] pts =
                { new Point(1, 2, 3), new Point(1, 2, 3), new Point(1, 2, 3)};
        assertThrows(IllegalArgumentException.class,()->pts[1].subtract(pts[0]),"Subtract function doesn't work");
        assertThrows(IllegalArgumentException.class,()->pts[2].subtract(pts[1]),"Subtract function doesn't work");
        assertThrows(IllegalArgumentException.class,()->(pts[1].subtract(pts[0])).subtract((pts[2].subtract(pts[1]))) ,"Subtract function doesn't work");
        Plane plane = new Plane(pts[0],pts[1],pts[2]);
        Vector result = plane.getNormal();

    }
}