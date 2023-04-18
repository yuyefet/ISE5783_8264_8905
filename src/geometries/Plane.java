package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 * Plane class
 */
public class Plane implements Geometry {

    private Point q0;

    private Vector normal;

    /**
     * ctor
     * calculate the normal according to what was learned about the normal to a triangle
     *
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point p1, Point p2, Point p3) {
        this.q0 = p1;
        Vector v1;
        Vector v2;
        Vector tmp;

        try{
            v1 = p2.subtract(p1);
            v2 = p3.subtract(p2);
            tmp = v1.crossProduct(v2);
        }
        catch(IllegalArgumentException ex){
            throw ex;
        }

        this.normal = tmp.normalize();

    }

    /**
     * put in normal filed the normalized vector received as parameter
     *
     * @param point
     * @param normal - getting a vector, not necessary a normal vector
     */
    public Plane(Point point, Vector normal) {
        this.q0 = point;
        this.normal = normal.normalize();
    }

    @Override
    public Vector getNormal(Point point) {
        return normal;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }

    /**
     * getter for normal field
     *
     * @return normal
     */
    public Vector getNormal() {
        return this.normal;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        Vector n = this.normal;
        Point q = this.q0;
        Vector sub;
        double nv;
        double nqp0;

        try{
            sub = q.subtract(p0);
        }
        catch(IllegalArgumentException ex)
        {
            return null;
        }

        nv = n.dotProduct(v);
        if(Util.isZero(nv)){
            return null;
        }

        nqp0 = n.dotProduct(sub);
        if(Util.isZero(nqp0)){
            return null;
        }

        double t = Util.alignZero(nqp0/nv);

        if(t <= 0){
            return null;
        }

        Point result = p0.add(v.scale(t));
        return List.of(result);

    }
}
