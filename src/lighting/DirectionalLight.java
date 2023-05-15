package lighting;

import primitives.Color;
import primitives.Vector;

public class DirectionalLight extends Light{
    private Vector direction;

    /***
     * ctor
     * @param intensity
     * @param direction
     */
    protected DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }


}
