package geometries;
import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Intersectable Interface
 */
public abstract class Intersectable {
    public static class GeoPoint {
        /**
         * geometry
         */
        public Geometry geometry;

        /**
         * point
         */
        public Point point;

        /**
         * GeoPoint's Constructor
         * @param geometry
         * @param point
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint geoPoint)) return false;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }
    /**
     * Function to calculate the intersection points between a ray and the geometric object
     * @param ray the ray which intersect the object
     * @return list of intersection points
     */
    public final List<Point> findIntersections(Ray ray) {
        List<GeoPoint> intersections = findGeoIntersections(ray);
        return intersections == null ? null
                : intersections.stream().map(gp -> gp.point).collect(Collectors.toList());
    }


    /**
     * This function returns a list of all the points where the ray intersects the surface of the sphere
     * @param ray The ray to intersect with the object
     * @return A list of GeoPoints.
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> intersections = findGeoIntersectionHelper(ray);
        return intersections;
    }

    /**
     * This function is here to help the findGeoIntersections function to find the list of GeoPoint intersections
     * @param ray The ray to intersect with the object
     * @return A list of GeoPoints.
     */
    protected abstract List<GeoPoint> findGeoIntersectionHelper(Ray ray);
    /**
     * > This function returns a list of all the points where the ray intersects the surface of the sphere
     *
     * @param ray The ray to intersect with the object
     * @return A list of GeoPoints.
     */

}
