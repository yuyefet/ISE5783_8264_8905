package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTests {

    /** Test method for {@link geometries.Triangle#getNormal(Point)}. */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: All the point are the same
        Point[] pts =
                {new Point(0, 0, 1), new Point(2, 0, 0), new Point(0, 4, 0)};
        final Triangle tr = new Triangle(pts[0],pts[1],pts[2]);

        assertEquals(new Vector(4, 2, 8).normalize(), tr.getNormal(new Point(0,0,0)), "The normal of the triangle is not normalized");
    }

    @Test
    void testFindIntersections() {
        Point[] pts =
                {new Point(0, 0, 1), new Point(2, 0, 0), new Point(0, 2, 0)};
        final Triangle triangle = new Triangle(pts[0],pts[1],pts[2]);
        Point intersect;

        // ============ Equivalence Partitions Tests ==============
        // TC01 : Inside polygon/triangle
        intersect = new Point(0.39,1.16,0);
        assertEquals(List.of(intersect),triangle.findIntersections(new Ray(new Point(0,0,1),new Vector(0.39,1.16,-1))),"there is one intersection");

        // TC02 : outside against edge
        assertNull(triangle.findIntersections(new Ray(new Point(0,0,1),new Vector(1,-0.5,-1))),"there is no intersection");

        // TC03 : outside against vertex
        assertNull(triangle.findIntersections(new Ray(new Point(0,0,1),new Vector(2.49,-0.23,-1))),"there is no intersection");

        //============ BVA ============
        // The ray begins "before" the plane
        // TC11 : On edge
        assertNull(triangle.findIntersections(new Ray(new Point(0,0,1),new Vector(1,0,-1))),"there is no intersection");

        // TC12 : In Vertex
        assertNull(triangle.findIntersections(new Ray(new Point(0,0,1),new Vector(2,0,-1))),"there is no intersection");

        // TC13 : On edge's continuation
        assertNull(triangle.findIntersections(new Ray(new Point(0,0,1),new Vector(0,-0.5,-1))),"there is no intersection");


    }
}