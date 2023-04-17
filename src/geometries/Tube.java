package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Util;
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

        double t;
        Point p0 = this.axisRay.getP0();
        Vector sub;
        Vector dir = this.axisRay.getDir();
        try{
            sub = point.subtract(p0);
            t = Util.alignZero(dir.dotProduct(sub));
        }
        catch(IllegalArgumentException ex){
            return null;
        }


        if(Util.isZero(t))
            return sub.normalize();

        Point o = p0.add(dir.scale(t));
        Vector normal;
        try{
            normal = point.subtract(o).normalize();
        }
        catch(IllegalArgumentException ex)
        {
            return null;
        }
        return normal;
    }


}
