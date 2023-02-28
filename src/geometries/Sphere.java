package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere extends RadialGeometry {
    private Point center;
    public Sphere(double radius,Point center) {
        super(radius);
        this.center=center;
    }
    @Override
    public Vector getNormal(Point point)
    {
        return null;
    }

    public Point getCenter() {
        /**
         * TODO
         * return no copy but value !!
         * check it out
         */
        return center;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center + ' ' + super.toString() +
                '}';
    }
}
