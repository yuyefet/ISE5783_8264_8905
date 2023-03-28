package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {
    /** Test method for {@link geometries.Sphere#Sphere(double radius,Point center)}. */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Test ==============
        try {
            new Sphere(new Point(0, 0, 1), new Point(2, 0, 0), new Point(0, 4, 0));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Plane");
        }
    }
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: All the points are the same
        // Create a plane with equal points
        final Plane plane = new Plane(new Point(0, 0, 1), new Point(2, 0, 0), new Point(0, 4, 0));
        assertEquals(new Vector(4, 2, 8).normalize(), plane.getNormal(), "The normal of the plane is not normalized");
    }
    @Test
    void getNormal() {

    }
}