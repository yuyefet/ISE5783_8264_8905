package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable{

    private List<Intersectable> intersections;

    public Geometries() {
        this.intersections = new LinkedList<Intersectable>();
    }

    public Geometries(Intersectable... geometries) {
        this.intersections = List.of(geometries);
    }

    public void add(Intersectable ... geometries){
        for(Intersectable geo : geometries)
            intersections.add(geo);

    }

    @Override
    public List<Point> findIntersections(Ray ray) {

        List<Point> points = null;
        List<Point> hasIntersections = null;

        for (Intersectable geo: intersections) {

            hasIntersections = geo.findIntersections(ray);

            if(hasIntersections != null)
            {
                //it means that there are intersections
                if(points == null)
                    points = new ArrayList<>(hasIntersections);

                else
                    points.addAll(hasIntersections);
            }
        }

        return points; //return null in case of 0 intersections
    }
}
