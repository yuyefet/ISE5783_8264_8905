package renderer;

import geometries.Intersectable.GeoPoint;


import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Ray head shift size for shading rays
     */
    private static final double DELTA = 0.1;

    /**
     * Check non-shading between a dot and the signature light source
     *
     * @param gp
     * @param l
     * @param n
     * @param nl
     * @param lightSource
     * @return true
     */
    private boolean unshaded(GeoPoint gp, Vector l, Vector n, double nl, LightSource lightSource) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(nl <0 ? DELTA : -DELTA).normalize();
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(lightRay);
        if (intersections == null)
            return true;
        for (GeoPoint geoPoint : intersections) {
            if (geoPoint.point.distance(point) < lightSource.getDistance(geoPoint.point))
                return false;
        }
        return true;
    }


    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intsersections = scene.getGeometries().findGeoIntersections(ray);
        if (intsersections == null)
            return scene.getBackground();

        GeoPoint closestPoint = ray.findClosestGeoPoint(intsersections);
        return calcColor(closestPoint, ray);
    }

    /**
     * This function returns a color from a given point
     *
     * @param p point
     * @return the color of the ambient light of the scene
     */
    private Color calcColor(GeoPoint p, Ray ray) {
        return scene.getAmbientLight().getIntensity().add(p.geometry.getEmission(), calcLocalEffects(p, ray));
    }

    /**
     * Calculates the color of a point on a geometry, by calculating the color of the light sources that affect it
     *
     * @param geoPoint The point on the geometry that the ray intersects with.
     * @param ray      the ray that intersects the geometry
     * @return The color of the point.
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray) {
        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Material material = geoPoint.geometry.getMaterial();
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.getLights()) {
            Vector l = lightSource.getL(geoPoint.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                if (unshaded(geoPoint, l, n, nl, lightSource)) {
                    Color intensity = lightSource.getIntensity(geoPoint.point);
                    color = color.add(calcDiffusive(material.kD, l, n, intensity),
                            calcSpecular(material.kS, l, n, v, material.nShininess, intensity));
                }
            }
        }
        return color;
    }

    /**
     * Calculate the diffusive component of the light intensity at a point on a surface
     *
     * @param kD        The diffuse coefficient of the material.
     * @param l         the vector from the point on the surface to the light source
     * @param n         the normal vector of the surface
     * @param intensity the color of the light source
     * @return The color of the diffuse reflection.
     */
    private Color calcDiffusive(Double3 kD, Vector l, Vector n, Color intensity) {
        return intensity.scale(kD.scale(Math.abs(l.dotProduct(n))));
    }

    /**
     * The specular component
     * is equal to the product of the intensity of the light source and the specular coefficient of the
     * material, raised to the power of the shininess of the material
     *
     * @param kS         The specular coefficient.
     * @param l          the vector from the point to the light source
     * @param n          normal vector
     * @param v          the vector from the point to the camera
     * @param nShininess The shininess of the material.
     * @param intensity  the color of the light source
     * @return The color of the point on the surface of the sphere.
     */
    private Color calcSpecular(Double3 kS, Vector l, Vector n, Vector v, int nShininess, Color intensity) {
        Vector r = l.subtract(n.scale(2 * (l.dotProduct(n))));
        return intensity.scale(kS.scale(Math.pow(Math.max(v.scale(-1).dotProduct(r), 0), nShininess)));
    }
}
