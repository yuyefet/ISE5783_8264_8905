package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracerBase {

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray)
    {
        List<Point> intsersections = scene.geometries.findIntersections(ray);
        if(intsersections !=null) {
            Point closestPoint = ray.findClosestPoint(intsersections);
            return calcColor(closestPoint);
        }
        return scene.background;
    }

    private Color calcColor(Point p)
    {
        return scene.ambientLight.getIntensity();
    }
}
