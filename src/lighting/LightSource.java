package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * LightSource interface
 */
public interface LightSource {

    //for SoftShadow
    /**
     * Get the array of points that will cast shadow rays.
     *
     * @param numOfPoints The number of points to generate
     * @param reference   The reference point
     * @return The array of points
     */
    public Point[] getPoints(int numOfPoints, Point reference);

    /**
     * Gets a Point and Returns the intensity of the light at that point
     *
     * @param p
     * @return  the intensity of the light in the point
     */
    public Color getIntensity(Point p);

    /**
     * Gets a Point
     *
     * @param p given Point
     * @return a vector L to the point p
     */
    public Vector getL(Point p);


    /**
     * Gets a Point
     *
     * @param point the given point
     * @return The distance between the point and the light
     */
    public double getDistance(Point point);



}
