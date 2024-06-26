package lighting;

import primitives.Color;
import primitives.Point;

import java.util.List;

/**
 * Light class with intensity field
 */
abstract class Light {
    private final Color intensity;

    /**
     * ctor
     * @param intensity - get intensity
     */
    protected Light(Color intensity){
        this.intensity = intensity;
    }


    /**
     * get intensity
     * @return this.intensity
     */
    public Color getIntensity() {
        return intensity;
    }

}
