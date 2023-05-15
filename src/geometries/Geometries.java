package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable {

    private List<Intersectable> intersections;

    public Geometries() {
        this.intersections = new LinkedList<>();
    }

    public Geometries(Intersectable... geometries) {
        this.intersections = List.of(geometries);
    }

    public void add(Intersectable... geometries) {
        Collections.addAll(intersections, geometries);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {

        List<Point> points = null;

        for (Intersectable geo : intersections) {

            List<Point> hasIntersections = geo.findIntersections(ray);

            if (hasIntersections != null) {
                //it means that there are intersections
                if (points == null) {
                    points = new ArrayList<>();
                }
                points.addAll(hasIntersections);
            }
        }

        return points; //return null in case of 0 intersections
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionHelper(Ray ray) {
        List<GeoPoint> geoIntersections = null;
        for(Intersectable item : intersections){
            List<GeoPoint> current = item.findGeoIntersections(ray);
            if(current != null){
                if(geoIntersections == null)
                    geoIntersections = new LinkedList<>(current);
                else geoIntersections.addAll(current);
            }
        }
        return geoIntersections;
    }
}
