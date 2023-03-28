package geometries;

import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTests {

    /** Test method for {@link geometries.Plane#Plane(Point p1,Point p2,Point p3)}. */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Test ==============
        try {
            Plane p = new Plane(new Point(0, 0, 1), new Point(2, 0, 0), new Point(0, 4, 0));
            assertEquals(new Vector(4, 2, 8).normalize(), p.getNormal(), "The normal of the plane is not normelaized");
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Plane");
        }


    }
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: All the points are the same
        // Create a plane with equal points
        /*
        TODO: check this test
         */
        try{
            final Plane plane = new Plane(new Point(1, 1, 1), new Point(1, 1, 1), new Point(1, 1, 1));
        }
        catch(IllegalArgumentException ex){
            return;
        }

        // Test the calculation of the normal vector
        //assertThrows(IllegalArgumentException.class, () -> plane.getNormal(), "The getNormal does not throwing the right exception");

    }
}