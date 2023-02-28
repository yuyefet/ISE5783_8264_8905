package primitives;

import java.util.Objects;

public class Ray {
    private Point p0;
    private Vector dir;

    public Ray(Point p,Vector vec)
    {
        this.dir=vec.normalize();
        this.p0=p;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }
}
