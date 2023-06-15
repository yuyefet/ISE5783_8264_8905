package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

public class SpotLight extends PointLight {

    private Vector direction;
    private int narrowBeam = 1;

    /***
     * ctor using super
     * @param intensity
     * @param position
     * @param direction
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }
    @Override
    public Color getIntensity(Point p) {
        double dl = alignZero(getL(p).dotProduct(this.direction));
        return dl <= 0 ? Color.BLACK : super.getIntensity(p).scale(dl);
    }

    /**
     * Sets the narrow beam of the spotlight
     *
     * @param narrowBeam The angle of the narrow beam in degrees.
     * @return The SpotLight object.
     */
    public SpotLight setNarrowBeam(int narrowBeam) {
        this.narrowBeam = narrowBeam;
        return this;
    }

    @Override
    public Vector getL(Point p) {
        return super.getL(p);
    }


}
