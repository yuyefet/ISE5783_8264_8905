package renderer;

import geometries.Intersectable.GeoPoint;


import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {

    /**
     * For soft shadow
     * The number of points that are stored in the array.
     * This determines the size of the grid required to render a sharp shadow.
     */
    private int numberOfPoints = 100;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K= Double3.ONE;

    /**
     * The RayTracerBasic class represents a basic ray tracer for rendering scenes.
     * It extends the RayTracer class and inherits its functionality.
     *
     * @param scene The scene to be rendered by the ray tracer.
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Sets the number of points to be used for generating shadow rays in the ray tracer.
     *
     * @param n The number of points to be set.
     * @return The RayTracerBasic object with the updated number of points.
     */
    protected RayTracerBasic setNumberOfPoints(int n) {
        numberOfPoints = n;
        return this;
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
     * Calculates the color of the intersected object at the intersection point with a given ray.
     * Starts the recursion call to calculate the reflection and refraction with the starting level of {@code MAX_CALC_COLOR_LEVEL} and the starting k of {@code INITIAL_K}.
     *
     * @param ray The ray that caused the intersection
     * @param gp  The intersection point with geometry
     * @return The color at the intersection point
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.getAmbientLight().getIntensity());
    }

    /**
     * Calculates the color of the intersected object at the intersection point with a given ray.
     * This is a recursive function that also calculates the reflection and refraction.
     *
     * @param p     The intersection point with geometry
     * @param ray   The ray that caused the intersection
     * @param level The recursion level
     * @param k     The ratio of the current ray's color to the color of the previous ray
     * @return The color at the intersection point
     */
    private Color calcColor(GeoPoint p, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(p, ray, k);
        return 1 == level ? color : color.add(calcGlobalEffects(p, ray, level, k));
    }


    /**
     * Calculates the color of a point on a geometry by considering the contribution of light sources that affect it.
     *
     * @param geoPoint The point on the geometry where the ray intersects.
     * @param ray The ray that intersects the geometry.
     * @param k The attenuation factor.
     * @return The color of the point.
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray, Double3 k) {
        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        double nv = alignZero(n.dotProduct(v));

        // If the dot product of the normal and view direction is zero, return black.
        if (nv == 0)
            return Color.BLACK;

        Material material = geoPoint.geometry.getMaterial();
        Color color = geoPoint.geometry.getEmission();

        for (LightSource lightSource : scene.getLights()) {

            // Soft Shadows: Calculate light vectors from the light source.
            List<Vector> vectorsLight = lightSource.getLightVector(geoPoint.point, numberOfPoints);

            // Calculate transparency factor for soft shadows.
            Double3 ktr = Double3.ZERO;

            // Iterate over the light vectors.
            ktr = calculateTransparencyFactor(geoPoint, vectorsLight, n, nv, lightSource);

            Vector l = lightSource.getL(geoPoint.point);
            double nl = alignZero(n.dotProduct(l));

            if (nl * nv > 0) { // sign(nl) == sign(nv)
                // Scale the transparency factor by the number of light vectors used.
                ktr = ktr.scale(((numberOfPoints == 0 || vectorsLight.size() == 1) ? 1 : ((double) 1 / numberOfPoints)));

                // Check if the accumulated transparency factor is above the minimum threshold.
                if (!(ktr.product(k).lowerThan(MIN_CALC_COLOR_K))) {
                    // Calculate the light intensity and add the diffuse and specular components to the color.
                    Color intensity = lightSource.getIntensity(geoPoint.point).scale(ktr);
                    color = color.add(intensity.scale(calcDiffusive(material.kD, nl)),
                            intensity.scale(calcSpecular(material, l, n, v)));
                }
            }
        }

        return color;
    }

    /**
     * Calculates the accumulated transparency factor based on the given light vectors.
     *
     * @param geoPoint     The point on the geometry.
     * @param vectorsLight The list of light vectors.
     * @param n            The surface normal.
     * @param nv           The dot product of the normal and view direction.
     * @param lightSource  The light source affecting the point.
     * @return The accumulated transparency factor.
     */
    private Double3 calculateTransparencyFactor(GeoPoint geoPoint, List<Vector> vectorsLight, Vector n, double nv, LightSource lightSource) {
        Double3 ktr = Double3.ZERO;

        if(vectorsLight == null)
            return ktr;

        // Iterate over the light vectors.
        for (Vector light : vectorsLight) {
            double nl = alignZero(n.dotProduct(light));

            if (nl * nv > 0) {
                // Accumulate the transparency factor.
                ktr = ktr.add(transparency(geoPoint, light, n, lightSource, nl));
            }
        }

        return ktr;
    }


    /**
     * Calculates the color of the point by calculating the color of the reflected and refracted rays.
     *
     * @param gp    The point on the geometry that the ray intersected with
     * @param ray   The ray that hit the geometry
     * @param level The recursion level
     * @param k     The color of the light that is reflected from the current point
     * @return The color of the point
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
     * Calculates the specular component, which is equal to the product of the intensity of the light source and the specular coefficient of the material, raised to the power of the shininess of the material.
     *
     * @param l The vector from the point to the light source
     * @param n The normal vector
     * @param v The vector from the point to the camera
     * @return The specular coefficient
     */
    private Double3 calcSpecular(Material mat, Vector l, Vector n, Vector v) {
        Vector r = l.subtract(n.scale(2 * (l.dotProduct(n))));
        return mat.kS.scale(Math.pow(Math.max(v.scale(-1).dotProduct(r), 0), mat.nShininess));
    }



    /**
     * Calculates the transparency of the point by evaluating the transparency of each point along the path to the light source.
     *
     * @param geoPoint     The point on the geometry for which the color is being calculated
     * @param l            The direction of the light source
     * @param n            The normal vector of the point on the surface of the object
     * @param nl
     * @param lightSource  The light source
     * @return The transparency coefficient
     */
    private Double3 transparency(GeoPoint geoPoint, Vector l, Vector n, LightSource lightSource,double nl) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geoPoint.point, lightDirection,n);
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(lightRay,lightSource.getDistance(geoPoint.point));
        if(intersections == null)
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
     * Constructs a reflected ray from a point, a normal vector, and an incoming ray.
     *
     * @param normal       The normal vector of the surface at the point of intersection
     * @param point        The point of intersection
     * @param incomingRay  The ray that hit the object
     * @return The ray that is reflected off the surface of the object
     */
    private Ray constructReflectedRay(Vector normal, Point point, Vector incomingRay) {
        Vector dir = incomingRay.subtract(normal.scale(2 * incomingRay.dotProduct(normal)));
        return new Ray(point, dir, normal);
    }

    /**
     * Constructs a refracted ray from the intersection point, the incoming ray, and the normal vector.
     *
     * @param normal The normal vector of the surface
     * @param point  The point of intersection between the ray and the object
     * @param incomingRay  The ray that hit the object
     * @return A new ray with the same origin as the point of intersection, the same direction as the incoming ray, and the
     *         same normal as the surface normal.
     */
    private Ray constructRefractedRay(Vector normal, Point point, Vector incomingRay) {
        return new Ray(point, incomingRay, normal);
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
