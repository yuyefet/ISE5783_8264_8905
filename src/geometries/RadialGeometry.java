package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * RadialGeometry abstract Class
 */
public abstract class RadialGeometry implements Geometry {
    /** Radius */
    protected double radius;

    /**
     * RadialGeometry Constructor
     * @param radius fields of RadialGeometry
     */
    public RadialGeometry(double radius) {
        this.radius=radius;
    }

    /**
     * getter of radius
     * @return the value of radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     *
     * @param point used to calculate the normal Vector
     * @return return null for the moment (stage 1)
     */
    public Vector getNormal(Point point) {
        return null;
    }
    @Override
    public String toString() {
        return "RadialGeometry{" +
                "radius=" + radius +
                '}';
    }
}
