package primitives;

import java.util.Objects;

public class Point {
    final Double3 xyz;
    public static final Point ZERO = new Point(0,0,0);
    public Point(double x, double y, double z) {
        this.xyz = new Double3(x,y,z);
    }

    public Point add(Vector vec)
    {
        /**
         * we have to check this
         */
        Double3 temp = this.xyz.add(vec.xyz);
        return new Point(temp.d1,temp.d2,temp.d3);
    }

    public Vector substract(Point point)
    {
        /**
         * we have to check this
         */
        Double3 temp = this.xyz.subtract(point.xyz);
        return new Vector(temp.d1,temp.d2,temp.d3);
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

    public double distance(Point point){
        return Math.sqrt(distanceSquared(point));
    }

    private double distanceSquared(Point point) {
        Double3 temp = point.xyz;
        Double3 temp2 = this.xyz;
        return ((temp.d1 -temp2.d1)*(temp.d1 -temp2.d1)+(temp.d2 -temp2.d2)*(temp.d2 -temp2.d2)+(temp.d3 -temp2.d3)*(temp.d3 -temp2.d3));
    }
}
