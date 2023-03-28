package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Sphere Class
 */
public class Sphere extends RadialGeometry {
    private Point center;

    /**
     * Sphere Constructor
     * @param radius field of Sphere
     * @param center field of Sphere
     */
    public Sphere(double radius,Point center) {
        super(radius);
        this.center=center;
    }
    @Override
    public Vector getNormal(Point point) {
        Vector n = point.subtract(center);
        return n.normalize();
    }

    /**
     * getter of center (Sphere class's field)
     * @return return the center
     */
    public Point getCenter() {
        return center;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center + ' ' + super.toString() +
                '}';
    }
}
