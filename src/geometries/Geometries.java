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
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance)
    {
        List<GeoPoint> intersects = null;
        for(Intersectable item : intersections){
            List<GeoPoint> current = item.findGeoIntersections(ray,maxDistance);
            if(current != null){
                if(intersects == null)
                    intersects = new LinkedList<>(current);
                else
                    intersects.addAll(current);
            }
        }

        return intersects;
    }
}
