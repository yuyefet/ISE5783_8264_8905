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
        // ============ Equivalence Partitions Test ==============
        final Tube tube = new Tube(2,new Ray(new Point(1,2,3),new Vector(1,2,1)));
        Point o = new Point(2,4,4);
        Point pEP= new Point(0,4,4);
        Vector expected1 = new Vector(-2,0,0).normalize();
        assertEquals(expected1,tube.getNormal(pEP),"EP : Wrong result of normal in tube");

        //BVA when (P-P0) is orthogonal to the vector
        Point pBVA = new Point(1,0,3);
        Vector expected2= new Vector(0,-2,0);
        assertEquals(expected2,tube.getNormal(pBVA),"BVA : Wrong result of normal in tube");

    }
}