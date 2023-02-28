package geometries;
import primitives.Vector;
import primitives.Point;
import primitives.Ray;

/**
 * TODO
 * check the inheritence and implementation
 */
public class Cylinder extends Tube {

    private double height;
    public Cylinder(double radius, Ray ray,double height) {
        super(radius, ray);
        this.height=height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        /**
         * TODO
         * check print format
         */
        return "Cylinder{" +
                "height=" + height +
                ", axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Vector getNormal(Point point) {return null;}
}
