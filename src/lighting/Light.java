package lighting;

import primitives.Color;

import java.util.List;

/**
 * Light class with intensity field
 */
abstract class Light {
    private Color intensity;

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
