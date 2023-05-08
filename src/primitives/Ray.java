package primitives;

import java.util.List;
import java.util.Objects;

/**
 * Ray - represents a "Semi-straight - all the points on the straight line that are on one
 * side of the given point on the straight line called the beginning of the ray
 *
 */
public class Ray {
    private Point p0;
    private Vector dir;

    /**
     * ctor for Ray
     * @param p
     * @param vec
     */
    public Ray(Point p,Vector vec) {
        this.dir=vec.normalize();
        this.p0=p;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    public Point GetPoint(double t){
        Vector vec = this.dir.scale(t);
        return this.p0.add(vec);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    /***
     *
     * @param pointList
     * @return the closest point from the list
     */
    public Point findClosestPoint(List<Point> pointList){
        double temp;
        Point min= null;
        double minDistance=  Double.POSITIVE_INFINITY;
        for(Point point : pointList)
        {
             temp= this.p0.distance(point);
            if(temp< minDistance) {
                min = point;
                minDistance=temp;
            }
        }
        return min;
    }
}
