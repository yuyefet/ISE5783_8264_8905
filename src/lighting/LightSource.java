package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.List;

/**
 * LightSource interface
 */
public interface LightSource {


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

    /***
     * The function return list of light Vectors
     * @param p from the point that is a hit
     * @return
     */
    public List<Vector> getLightVector(Point p,int numOfPoint);



}
