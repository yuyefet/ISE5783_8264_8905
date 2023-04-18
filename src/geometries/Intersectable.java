package geometries;
import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * Intersectable Interface
 */
public interface Intersectable {
    /***
     * find Intersections
     * @param ray
     * @return
     */
    List<Point> findIntersections(Ray ray);

}
