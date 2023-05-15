package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * RadialGeometry abstract Class
 */
public abstract class RadialGeometry extends Geometry {
    /**
     * Radius
     */
    protected double radius;

    /**
     * RadialGeometry Constructor
     *
     * @param radius fields of RadialGeometry
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }

    /**
     * getter of radius
     *
     * @return the value of radius
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "RadialGeometry{" +
                "radius=" + radius +
                '}';
    }
}
