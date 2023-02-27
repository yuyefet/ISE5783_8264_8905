package primitives;

import java.util.Objects;

public class Point {
    final Double3 xyz;
    public static final Point ZERO = new Point(0,0,0);
    public Point(double x, double y, double z) {
        this.xyz = new Double3(x,y,z);
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
        return ((point.xyz.d1 -xyz.d1));
    }
}
