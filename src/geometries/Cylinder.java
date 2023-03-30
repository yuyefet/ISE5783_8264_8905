package geometries;
import primitives.Vector;
import primitives.Point;
import primitives.Ray;

/**
 * Cylinder class
 */
public class Cylinder extends Tube {

    private double height;

    /**
     * Cylinder Constructor
     * @param radius
     * @param ray
     * @param height
     */
    public Cylinder(double radius, Ray ray,double height) {
        super(radius, ray);
        this.height=height;
    }

    /**
     * getter of height
     * @return the value of height
     */
    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Vector getNormal(Point point) {

        if(this.axisRay.getP0().equals(point))
            return this.axisRay.getDir().normalize();

        double t= this.axisRay.getDir().dotProduct(point.subtract(this.axisRay.getP0()));
        if(t==0)
            return  this.axisRay.getDir().normalize();
        if (t==this.height)
            return this.axisRay.getDir();
        Point o = this.axisRay.getP0().add(this.axisRay.getDir().scale(t));
        Vector normal = point.subtract(o).normalize();
        return normal;
    }
}
