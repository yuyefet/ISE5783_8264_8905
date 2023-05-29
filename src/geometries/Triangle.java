package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

public class Triangle extends Polygon {
    /**
     * Triangle ctor
     *
     * @param p1
     * @param p2
     * @param p3
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionHelper(Ray ray) {
        Vector v = ray.getDir();
        Point p0 = ray.getP0();

        List<GeoPoint> intersections = this.plane.findGeoIntersectionHelper(ray);

        if (intersections == null)
            return null;

        Vector v1, v2, v3;
        Vector n1, n2, n3;

        Point p1 = this.vertices.get(0);
        Point p2 = this.vertices.get(1);
        Point p3 = this.vertices.get(2);
        try {
            v1 = p1.subtract(p0);
            v2 = p2.subtract(p0);
            v3 = p3.subtract(p0);
        } catch (IllegalArgumentException ex) {
            return null;
        }

        try {
            n1 = v1.crossProduct(v2).normalize();
            n2 = v2.crossProduct(v3).normalize();
            n3 = v3.crossProduct(v1).normalize();
        } catch (IllegalArgumentException ex) {
            return null;
        }

        double vn1, vn2, vn3;

        vn1 = alignZero(v.dotProduct(n1));
        vn2 = alignZero(v.dotProduct(n2));
        vn3 = alignZero(v.dotProduct(n3));

        if ((vn1 < 0 && vn2 < 0 && vn3 < 0) || (vn1 > 0 && vn2 > 0 && vn3 > 0)) {
            intersections.get(0).geometry = this;
            return intersections;
        }
        return null;
    }

}
