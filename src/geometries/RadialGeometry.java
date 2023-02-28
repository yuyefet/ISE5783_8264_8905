package geometries;

import primitives.Point;
import primitives.Vector;

public abstract class RadialGeometry implements Geometry {
    protected double radius;

    public RadialGeometry(double radius)
    {
        this.radius=radius;
    }

    public double getRadius() {
        return radius;
    }

    public Vector getNormal(Point point)
    {
        return null;
    }
    @Override
    public String toString() {
        return "RadialGeometry{" +
                "radius=" + radius +
                '}';
    }
}
