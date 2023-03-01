package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Geometry Interface
 */
public interface Geometry {
    /**
     * Calculate the normal Vector from the param point
     * @param point used to calculate the normal Vector
     * @return return the normal Vector
     */
    public Vector getNormal(Point point);
}
