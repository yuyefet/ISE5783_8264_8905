package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.ArrayList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SphereTests {
    /** Test method for {@link geometries.Sphere#Sphere(double radius,Point center)}. */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Test ==============
        // TC01 : Try to create a Sphere
        try {
            new Sphere(2,new Point(1,1,1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Sphere");
        }
    }

    /** Test method for {@link geometries.Sphere#getNormal(Point)}. */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: All the points are the same
        final Sphere sphere = new Sphere(2,new Point(1,1,1));
        assertEquals(new Vector(2, 0,0 ).normalize(), sphere.getNormal(new Point(3,1,1)), "The normal of the sphere is not normalized");
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(Ray)}.
     */
    @Test
    void testFindIntersectionPoints() {
        final Sphere sphere = new Sphere(2,new Point(0,0,0));

        // ============ Equivalence Partitions Test ==============
        // TC01 : A ray on the line that is completely off the sphere

        Ray ray = new Ray(new Point(-3,0,0), new Vector(1,3,0));
        assertNull(sphere.findIntersections(ray), "There is no intersection!");

        // TC02 : A ray on the straight line that intersect with the sphere,
        // when the beginning of the ray is before the sphere
        ray = new Ray(new Point(3,-3,0), new Vector(-7,4,0));
        List<Point> intersections = new ArrayList<>();
        intersections.add(new Point(0.89, -1.79, 0));
        intersections.add(new Point(-1.99, -0.15, 0));
        assertEquals(, sphere.findIntersections(ray), "" );



    }
}