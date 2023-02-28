package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry {

    protected Ray axisRay;
    public Tube(double radius,Ray ray) {
        super(radius);
        this.axisRay=ray;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay + ' ' + super.toString() +
                '}';
    }

    @Override
    public Vector getNormal(Point point)
    {
        return null;
    }


}
