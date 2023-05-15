package lighting;

import primitives.Color;
import primitives.Point;

public class PointLight extends Light{

    private Point position;
    private double kC = 1, kL = 0, kQ = 0;

    /**
     * ctor
     *
     * @param intensity - get intensity
     * @param position - point position
     */
    protected PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }
}
