package renderer;

import geometries.Intersectable.GeoPoint;


import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

    private static final Double3 INITIAL_K= Double3.ONE;

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /***
     * check the closest intersection point and return the color
     * @param ray
     * @return color
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.getBackground() : calcColor(closestPoint, ray);
    }

    /**
     * Calculates the color of the intersected object
     * on the intersection point with a given ray.
     * Starts the recursion call to calculate the reflection
     * and the refraction with starting level of
     * {@code MAX_CALC_COLOR_LEVEL} and starting k
     * of {@code INITIAL_K}.
     *
     * @param gp  the intersection point with geometry
     * @param ray the ray that caused the intersection
     * @return the color on the intersection point
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.getAmbientLight().getIntensity());
    }

    /**
     * Calculates the color of the intersected object
     * on the intersection point with a given ray.
     * This is recursive function that calculates also
     * the reflection and refraction.
     *
     * @param p  the intersection point with geometry
     * @param ray the ray that caused the intersection
     * @param level the recursion level
     * @param k the ratio og the current ray's color to the color of the previous ray
     * @return the color on the intersection point
     */
    private Color calcColor(GeoPoint p, Ray ray, int level, Double3 k) {
        Color color =calcLocalEffects(p, ray, k);
        return 1 == level ? color : color.add(calcGlobalEffects(p, ray, level, k));
    }


    /**
     * Calculates the color of a point on a geometry, by calculating the color of the light sources that affect it
     *
     * @param geoPoint The point on the geometry that the ray intersects with.
     * @param ray      the ray that intersects the geometry
     * @param k
     * @return The color of the point.
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray,Double3 k) {
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
               Double3 ktr= transparency(geoPoint,l,n,lightSource,nv);
               if(!(ktr.product(k).lowerThan(MIN_CALC_COLOR_K))) {
                   Color intensity = lightSource.getIntensity(geoPoint.point).scale(ktr);
                   color = color.add(intensity.scale(calcDiffusive(material.kD, nl)),
                           intensity.scale(calcSpecular(material, l, n, v)));
               }
            }
        }
        return color;
    }

    /**
     * It calculates the color of the point by calculating the color of the reflected and refracted rays
     *
     * @param gp The point on the geometry that the ray intersected with.
     * @param ray the ray that hit the geometry
     * @param level the recursion level.
     * @param k the color of the light that is reflected from the current point.
     * @return The color of the point.
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Material material = gp.geometry.getMaterial();
        Double3 kr =material.kR;
        Double3 kkr = k.product(kr);
        Vector normal = gp.geometry.getNormal(gp.point);

        Ray reflectedRay = constructReflectedRay(normal, gp.point, ray.getDir());
        GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
            if (reflectedPoint != null)
                color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
        }

        Double3 kt= material.kT;
        Double3 kkt = k.product(kt);
        Ray refractedRay = constructRefractedRay(normal, gp.point, ray.getDir());
        GeoPoint refractedPoint = findClosestIntersection(refractedRay);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
            if (refractedPoint != null) {
                color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
            }
        }
        return color;
    }


    /**
     * Calculate the diffusive component of the light intensity at a point on a surface
     *
     * @param kD        The diffuse coefficient of the material.
     * @param nl         the vector from the point on the surface to the light source dotproduct with the normal vector of the surface
     * @return The diffuse coefficient
     */
    private Double3 calcDiffusive(Double3 kD, double nl) {
        return kD.scale(Math.abs(nl));
    }

    /**
     * The specular component
     * is equal to the product of the intensity of the light source and the specular coefficient of the
     * material, raised to the power of the shininess of the material
     *
     * @param l          the vector from the point to the light source
     * @param n          normal vector
     * @param v          the vector from the point to the camera
     * @return The specular coefficient
     */
    private Double3 calcSpecular(Material mat, Vector l, Vector n, Vector v) {
        Vector r = l.subtract(n.scale(2 * (l.dotProduct(n))));
        return mat.kS.scale(Math.pow(Math.max(v.scale(-1).dotProduct(r), 0), mat.nShininess));
    }

    /**
     * If the ray from the point to the light source intersects any geometry, then the point is shaded, otherwise it's
     * unshaded
     *
     * @param LightSource The light source
     * @param gp    The point on the geometry that we're shading.
     * @param l     The vector from the light source to the point on the geometry.
     * @param n     the normal vector to the surface at the intersection point
     * @param nv    the dot product of the normal vector and the vector from the light source to the point.
     * @return true if the point is unshaded, and false if it is shaded.
     */
    /*private boolean unshaded(GeoPoint gp,LightSource lightSource, Vector l, Vector n, double nl)
    {
        return transparency(gp,l,n,lightSource,nl) == Double3.ZERO ? true : false;
    }*/

    /**
     * The function calculates the transparency of the point, by calculating the transparency of each point on the way to
     * the light source
     * @param geoPoint The point on the geometry that we are currently calculating the color for.
     * @param l the direction of the light source
     * @param n The normal vector of the point on the surface of the object.
     * @param nl
     * @param lightSource The light source
     * @return the transparency coefficient
     */
    private Double3 transparency(GeoPoint geoPoint, Vector l, Vector n, LightSource lightSource,double nl) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geoPoint.point, lightDirection,n);
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(lightRay,lightSource.getDistance(geoPoint.point));
        if(intersections ==null)
            return Double3.ONE;
        Double3 ktr = Double3.ONE;
        double distance =lightSource.getDistance(geoPoint.point);
        for(GeoPoint gp :intersections){
            if(alignZero(gp.point.distance(geoPoint.point)-distance)<=0){
                ktr = ktr.product(gp.geometry.getMaterial().kT);
                if(ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
            }
        }
        return ktr;
    }



    /**
     *  Construct a reflected ray from a point, a normal vector, and an incoming ray
     *
     * @param normal The normal vector of the surface at the point of intersection.
     * @param point The point of intersection
     * @param inRay The ray that hit the object
     * @return A ray that is reflected off the surface of the object.
     */
    private Ray constructReflectedRay(Vector normal, Point point, Vector inRay) {
        Vector dir = inRay.subtract(normal.scale(2 * inRay.dotProduct(normal)));
        return new Ray(point, dir, normal);
    }

    /**
     * Construct a refracted ray from the intersection point, the incoming ray, and the normal vector.
     *
     * @param normal the normal vector of the surface
     * @param point The point of intersection between the ray and the object.
     * @param inRay the ray that hit the object
     * @return A new ray with the same origin as the point of intersection, the same direction as the incoming ray, and the
     * same normal as the normal of the surface.
     */
    private Ray constructRefractedRay(Vector normal, Point point, Vector inRay) {
        return new Ray(point, inRay, normal);
    }

    /**
     * It finds the closest intersection point of a ray with the scene's geometries
     *
     * @param ray The ray that we want to find the closest intersection to.
     * @return The closest intersection point to the camera.
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(ray);
        return ray.findClosestGeoPoint(intersections);
    }

}
