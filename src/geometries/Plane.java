package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {

    private Point q0;

    private Vector normal;

    public Plane(Point p1,Point p2,Point p3)
    {
        /**
         * TODO
         * ...
         */
    }

    public Plane(Point point , Vector normal)
    {
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

    public Vector getNormal()
    {
        return this.normal;
    }
}
