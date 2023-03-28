package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Tube Class
 * Tube (infinite cylinder)
 */

public class Tube extends RadialGeometry {
    /** axisRay (line with direction) */
    protected Ray axisRay;

    /**
     * Tube's Constructor
     * @param radius double's type
     * @param ray Ray's type
     */
    public Tube(double radius,Ray ray) {
        super(radius);
        this.axisRay=ray;
    }

    /**
     * getter of axisRay (Tube's field)
     * @return axisRay
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay + ' ' + super.toString() +
                '}';
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }


}
