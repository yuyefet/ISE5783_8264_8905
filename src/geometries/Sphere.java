package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Sphere Class
 */
public class Sphere extends RadialGeometry {
    private Point center;

    /**
     * Sphere Constructor
     *
     * @param radius field of Sphere
     * @param center field of Sphere
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    @Override
    public Vector getNormal(Point point) {
        Vector n = point.subtract(center);
        return n.normalize();
    }

    /**
     * getter of center (Sphere class's field)
     *
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



    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
        Vector u;
        try { //when P0 and the center are the same point
            u = this.center.subtract(ray.getP0());
        } catch (IllegalArgumentException ex) {
            return  List.of(new GeoPoint(this, ray.GetPoint(this.radius)));
        }

        double tm = u.dotProduct(ray.getDir());
        double d2 = u.lengthSquared() - tm * tm;
        double th2 = (radius*radius) - d2;
        if (alignZero(th2) <= 0) return null;

        double th = Math.sqrt(th2);
        double t2 = alignZero(tm + th);// double t2 = alignZero(tm + th2);
        if (t2 <= 0) return null;

        double t1 = alignZero(tm -th);
        if(alignZero(t1-maxDistance)>0 || alignZero(t2-maxDistance)>0) return null;

        return t1 <= 0 ? List.of(new GeoPoint(this, ray.GetPoint(t2))) : List.of(new GeoPoint(this, ray.GetPoint(t1)),new GeoPoint(this, ray.GetPoint(t2)));
    }
}
