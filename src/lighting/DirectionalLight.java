package lighting;

import primitives.Color;
import primitives.Vector;
import primitives.Point;

import java.util.List;

/**
 * Class Directional Light
 * a bright light with direction
 * extends the abstract class Light and implements the interface LightSource
 */
public class DirectionalLight extends Light implements LightSource{
    private Vector direction;

    /***
     * ctor
     * @param intensity
     * @param direction
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        return this.getIntensity();
    }

    @Override
    public Vector getL(Point p){
        return direction;
    }

    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public List<Vector> getLightVector(Point p,int numOfPoint) {
        return List.of(this.getL(p));
    }


}
