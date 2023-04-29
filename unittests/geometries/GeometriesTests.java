package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTests {

    @Test
    void findIntersections() {
        Geometries geometries= new Geometries();
        Plane plane = new Plane(new Point(-5, 0, 0), new Point(0, -4,0), new Point(0,0,4));
        Triangle triangle = new Triangle(new Point(0,0,3), new Point(-3,0,0), new Point(0,3,0));
        Sphere sphere = new Sphere(2, new Point(2,0,0));
        List<Point> result;

        Ray ray = new Ray(new Point(0,0,1), new Vector(0,1,0));
        // ********** BVA *************
        // TC11: Empty collection of geometries
        assertNull(geometries.findIntersections(ray), "There are no intersections");

        // TC12: No intersections
        geometries= new Geometries(plane, triangle, sphere);
        result = geometries.findIntersections(ray);

        assertEquals(0, result.size());



    }
}