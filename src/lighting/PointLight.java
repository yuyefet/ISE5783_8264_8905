package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

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
    private double size = 0;

    /**
     * Array of points - to illustrate a bigger lightSource
     */
    protected Point[] points;


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
     * @param size Size of the array
     * @return Point light with array of point - builder pattern
     */
    public PointLight setSize(double size) {
        // If size > 0 : SoftShadowing
        this.size = size;
        return this;
    }

    /**
     * Get the array of points that will cast shadow rays.
     *
     * @param reference           The reference point
     * @param numOfPoints The number of points to generate
     * @return The array of points
     */
    @Override
    public Point[] getPoints(int numOfPoints, Point reference) {
        // If size is zero, return null
        if (size == 0)
            return null;

        // If points are already generated, return them
        if (this.points != null)
            return this.points;

        Point[] points = new Point[numOfPoints];
        Vector to = reference.subtract(position).normalize();
        Vector vX = to.getOrthogonal().normalize();
        Vector vY = vX.crossProduct(to).normalize();
        double x, y, radius;

        // Generate points in sets of 4
        for (int i = 0; i < numOfPoints; i += 4)
        {
            // Calculate random radius and x-coordinate within the radius
            radius = rand.nextDouble(size) + 0.1;
            x = rand.nextDouble(radius) + 0.1;

            // Calculate y-coordinate using the equation of a circle
            y = radius * radius - x * x;

            // Generate the 4 mirrored points
            for (int j = 0; j < 4; j++)
            {
                // mirroring the point 4 times, for each quarter of the grid
                points[i + j] = position.add(vX.scale(j % 2 == 0 ? x : -x)).add(vY.scale((j <= 1 ? -y : y)));
            }
        }

        this.points = points;
        return points;
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
}
