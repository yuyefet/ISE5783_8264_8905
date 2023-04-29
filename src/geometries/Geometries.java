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
        {
            intersections.add(geo);
        }
    }

    @Override
    public List<Point> findIntersections(Ray ray) {

        List<Point> points = null;
        List<Point> temp;

        for (Intersectable geo: intersections) {
            temp = geo.findIntersections(ray);
            if(temp != null) {
                if(points == null) {
                    points = new ArrayList<>(temp);
                } else {
                    points.addAll(temp);
                }
            }
        }

        return points;
    }
}
