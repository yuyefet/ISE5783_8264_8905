package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * LightSource interface
 */
public interface LightSource {
    public Color getIntensity(Point p);
    public Vector getL(Point p);
}
