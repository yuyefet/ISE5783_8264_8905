package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
//Tube (infinite cylinder)
class TubeTest {
    /** Test method for {@link geometries.Tube#Tube(double radius, Ray ray)}. */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Test ==============
        try {
            new Tube(2,new Ray(new Point(1,2,3),new Vector(1,2,1)));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Sphere");
        }
    }

    /** Test method for {@link geometries.Tube#getNormal(Point)}. */
    @Test
    void getNormal() {

    }
}