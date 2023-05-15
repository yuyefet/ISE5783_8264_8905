package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

public class Scene {
    public String name;

    public List<LightSource> light = new LinkedList<LightSource>();
    public Color background = Color.BLACK;

    public AmbientLight ambientLight=AmbientLight.NONE;

    public Geometries geometries= new Geometries();


    public Scene(String name) {
        this.name = name;
    }

    public Scene setBackground(Color background) {
        this.background = background;
        return  this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * set Light list
     * @param light
     * @return this
     */
    public Scene setLight(List<LightSource> light) {
        this.light = light;
        return this;
    }
}
