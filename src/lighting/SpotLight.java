package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight {

    private Vector direction;

    /***
     * ctor using super
     * @param intensity
     * @param position
     * @param direction
     */
    protected SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }
}
