package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Geometry Interface
 */
public interface Geometry extends Intersectable {
    /**
     * Calculate the normal Vector from the param point
     * @param point used to calculate the normal Vector
     * @return return the normal Vector
     */
    public Vector getNormal(Point point);

}
