package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight {

    private Color Intensity;

    public static AmbientLight NONE = new AmbientLight(Color.BLACK,Double3.ZERO);

    public AmbientLight(Color Ia, Double3 Ka) {
        this.Intensity=Ia.scale(Ka);
    }

    public AmbientLight(Color Ia,double Ka){
        this.Intensity=Ia.scale(Ka);
    }

    public Color getIntensity() {
        return Intensity;
    }

}
