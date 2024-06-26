package primitives;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Point - represents a point in the dimension
 */
public class Point {
    final Double3 xyz;
    public static final Point ZERO = new Point(0,0,0);

    /**
     * ctor for point
     * gets 3 double params
     * @param x x value of the point
     * @param y y value of the point
     * @param z z value of the point
     */
    public Point(double x, double y, double z) {

        this.xyz = new Double3(x,y,z);
    }

    /**
     * ctor for point by immutable parameter of Double3 Type
     * @param xyz
     */
    public Point(final Double3 xyz){
        this.xyz = xyz;
    }

    /**
     * adding Vector to the current point and returns a new updated point
     * @param vec vector to add to point
     * @return new updated point
     */
    public Point add(Vector vec) {
        Double3 temp = this.xyz.add(vec.xyz);
        return new Point(temp.d1,temp.d2,temp.d3);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;
        return xyz.equals(point.xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    @Override
    public String toString() {
        return "Point: " + xyz;
    }

    /**
     * calculates the distance between the current point and the point received by parameter
     * @param point - point to calculate distance from
     * @return the distance between the current point and the point received by parameter
     */
    public double distance(Point point) {
        return Math.sqrt(distanceSquared(point));
    }

    /**
     * distanceSquared calculation
     * @param point - point to calculate distance from
     * @return the pow of the distance between the current point and the point received by parameter
     */
    public double distanceSquared(Point point) {
        Double3 temp = point.xyz;
        Double3 temp2 = this.xyz;
        /**
         * x*x + y*y + z*z
         */
        return ((temp.d1 -temp2.d1)*(temp.d1 -temp2.d1)+(temp.d2 -temp2.d2)*(temp.d2 -temp2.d2)+(temp.d3 -temp2.d3)*(temp.d3 -temp2.d3));
    }

    /**
     * Vector subtraction
     * @param point - receives a second point in the parameter to substract with
     * @return a vector from the second point to the point on which the subtraction is performed the action
     */
    public Vector subtract(Point point) {
        Double3 temp = this.xyz.subtract(point.xyz);
        return new Vector(temp);
    }


    public  double getX(){
        return  xyz.d1;
    }

    public  double getY(){
        return  xyz.d2;
    }

    public  double getZ(){
        return  xyz.d3;
    }

    /**
     * Generates a list of points within a given plane.
     *
     * @param u         The first vector defining the plane.
     * @param v         The second vector defining the plane.
     * @param numPoints The number of points to generate.
     * @param center    The center point of the plane.
     * @param radius    The radius of the plane.
     * @return A list of points within the plane.
     */
    public static List<Point> getPoints(Vector u, Vector v, int numPoints, Point center, double radius) {
        List<Point> points = new LinkedList<>();
        double angleIncrement = 2 * Math.PI / numPoints;

        // Generate points within the plane.
        for (int i = 0; i < numPoints; i++) {
            double angle = i * angleIncrement;
            double x = center.getX() + radius * (Math.cos(angle) * u.getX() + Math.sin(angle) * v.getX());
            double y = center.getY() + radius * (Math.cos(angle) * u.getY() + Math.sin(angle) * v.getY());
            double z = center.getZ() + radius * (Math.cos(angle) * u.getZ() + Math.sin(angle) * v.getZ());
            points.add(new Point(x, y, z));
        }

        return points;
    }


}
