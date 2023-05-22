package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

public class Scene {
     private final String name;
//
    private final List<LightSource> lights;


    private final Color background;
    private final Geometries geometries;

    private  AmbientLight ambientLight;
    public List<LightSource> getLights() {
        return lights;
    }

    public Color getBackground() {
        return background;
    }

    public Geometries getGeometries() {
        return geometries;
    }

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

//

  //  public Scene(String name) {
       // this.name = name;
   // }
    private Scene (SceneBuilder sceneBuilder){

        this.ambientLight = sceneBuilder.ambientLight;
        geometries = sceneBuilder.geometries;
        lights = sceneBuilder.lights;
        background = sceneBuilder.background;
        name = sceneBuilder.name;
    }





    public static class SceneBuilder {
        private final String name;
        private AmbientLight ambientLight=AmbientLight.NONE;
        private Geometries geometries=new Geometries();
        private List<LightSource> lights= new LinkedList<>();
        private Color background=Color.BLACK;

        public SceneBuilder(String name) {
            this.name = name;
        }

        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        public SceneBuilder setGeometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }

        public SceneBuilder setLights(List<LightSource> lights) {
            this.lights = lights;
            return this;
        }

        public SceneBuilder setBackground(Color background) {
            this.background = background;
            return this;
        }

        public Scene build(){
            return new Scene(this);
        }
    }
}
