package renderer;
import geometries.Intersectable.GeoPoint;


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
        List<GeoPoint> intsersections = scene.geometries.findGeoIntersections(ray);
        if(intsersections !=null) {
            GeoPoint closestPoint = ray.findClosestGeoPoint(intsersections);
            return calcColor(closestPoint);
        }
        return scene.background;
    }

    private Color calcColor(GeoPoint p) {
        return scene.ambientLight.getIntensity()
                .add(p.geometry.getEmission());
    }
}
