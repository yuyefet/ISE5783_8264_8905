package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Plane class
 */
public class Plane implements Geometry {

    private Point q0;

    private Vector normal;

    /**
     * ctor
     * calculate the normal according to what was learned about the normal to a triangle
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point p1,Point p2,Point p3) {
       this.q0 = p1;

       Vector v1 = p2.subtract(p1);
       Vector v2 = p3.subtract(p2);
       this.normal = v1.crossProduct(v2).normalize();

    }

    /**
     * put in normal filed the normalized vector received as parameter
     * @param point
     * @param normal - getting a vector, not necessary a normal vector
     */
    public Plane(Point point , Vector normal) {
        this.q0=point;
        if(normal.length()==1)
            this.normal=normal;
        else
            this.normal=normal.normalize();
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
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
     * @return normal
     */
    public Vector getNormal() {
        return this.normal;
    }
}
