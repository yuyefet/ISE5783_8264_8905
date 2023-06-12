package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Plane class
 */
public class Plane extends Geometry {

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
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        Vector n = this.normal;
        Point q = this.q0;
        Vector sub;
        try {
            sub = q0.subtract(p0);
        } catch (IllegalArgumentException ignore) {
            return null;
        }

        double nv = normal.dotProduct(v);
        if (isZero(nv)) return null;

        double t = alignZero(normal.dotProduct(sub) / nv);
        return t > 0 && alignZero(t-maxDistance)<=0 ?  List.of(new GeoPoint(this, ray.GetPoint(t))) : null;
    }

    public List<Vector> findVectorsPlanes()
    {
        Vector v1 = new Vector(-normal.getY(), normal.getX(), 0);
        if (v1.length() < 1e-6) {
            v1 = new Vector(0, -normal.getZ(), normal.getY());
        }
        Vector v2 = normal.crossProduct(v1);
        return List.of(v1,v2);
    }

}
