package lighting;

import primitives.Color;
import geometries.Plane;
import primitives.Point;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Class PointLight
 * a light with location & Intensity weakens with distance
 *
 * Extends abstract Class Light and implements LightSource
 */
public class PointLight extends Light implements LightSource{

    //********** For SoftShadow *************//
    /**
     * A random number - for creating the points in the grid
     */
    private final Random rand = new Random();

    /**
     * The light's size
     */
    private double radius = 10;

    private Point[] points;


    private Point position;
    private double kC = 1, kL = 0, kQ = 0;

    /**
     * ctor
     *
     * @param intensity - get intensity
     * @param position - point position
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * Sets the factor kC
     *
     * @param kC new factor
     * @return PointLight with factor kC - builder pattern
     */
    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * Sets the factor kL
     *
     * @param kL new factor
     * @return PointLight with factor kL - builder pattern
     */
    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * Sets the factor kQ
     *
     * @param kQ new factor
     * @return PointLight with factor kQ - builder pattern
     */
    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    /**
     * Sets the size of the array of points
     *
     * @param radius Size of the array
     * @return Point light with array of point - builder pattern
     */
    public PointLight setRadius(double radius) {
        // If size > 0 : SoftShadowing
        this.radius = radius;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double d = p.distance(this.position);
        return this.getIntensity().reduce(this.kC + this.kL * d + this.kQ * d * d);
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(this.position).normalize();
    }

    @Override
    public double getDistance(Point point) {
        return this.position.distance(point);
    }

    /**
     * Retrieves the necessary light vectors for creating soft shadows.
     *
     * @param p The point of intersection.
     * @param numOfPoint The number of desired light vectors.
     * @return The list of light vectors.
     */
    @Override
    public List<Vector> getLightVector(Point p, int numOfPoint) {
        // If the radius is zero, it means there is only one point light.
        if (radius == 0)
            return List.of(getL(p));

        List<Vector> vectorsLights = new LinkedList<>();
        Vector mainLight = getL(p);

        // Create a plane perpendicular to the main light vector.
        Plane plane = new Plane(this.position, mainLight.scale(-1));

        // Find vectors on the plane.
        List<Vector> vectorsPlane = plane.findVectorsPlanes();

        // Generate random points on the plane.
        List<Point> points = Point.getPoints(vectorsPlane.get(0), vectorsPlane.get(1), numOfPoint, position, radius);

        // Calculate the light vectors by subtracting the generated points from the intersection point.
        for (int i = 0; i < points.size() && i < numOfPoint; i++) {
            vectorsLights.add(p.subtract(points.get(i)));
        }

        vectorsLights.add(mainLight);

        return vectorsLights;
    }



}
