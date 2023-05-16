package geometries;

import primitives.*;

import java.util.List;

/**
 * Geometry Interface
 */
public abstract class Geometry extends Intersectable {
    /**
     * Calculate the normal Vector from the param point
     * @param point used to calculate the normal Vector
     * @return return the normal Vector
     */
    protected Color emission = Color.BLACK;
    private Material material = new Material();

    public abstract Vector getNormal(Point point);

    /***
     * get emission
     * @return this.emission
     */
    public Color getEmission() {
        return emission;
    }

    /***
     * setter to emission
     * @param emission
     * @return the geometry object
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }
}
