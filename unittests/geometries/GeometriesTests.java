package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTests {

    @Test
    void findIntersections() {
        // ********** BVA *************
        // Empty collection of geometries
        Geometries geometries= new Geometries(new Sphere(2,new Point(1,1,1)),new Plane());


        // ********** BVA *************
        // No intersections



    }
}