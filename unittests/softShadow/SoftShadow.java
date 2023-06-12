package softShadow;
import geometries.*;

import lighting.AmbientLight;
import lighting.LightSource;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;

import static java.awt.Color.*;

public class SoftShadow {

    private Scene scene2      = new Scene.SceneBuilder("Test scene").build();
    private Camera camera2     = new Camera(new Point(0, 120, 0), new Vector(0, -1, 0), new Vector(0, 0, 1))   //
            .setViewPlaneSize(150, 150).setViewPlaneDistance(100);

    private final SpotLight spotLight= (SpotLight) new SpotLight(new Color(700, 400, 400),new Point(0,0,90),new Vector(0,0,-1)).setkL(4E-4).setkQ(2E-5);
    private final Plane floor = (Plane) new Plane(new Point(0,0,-100),new Vector(0,0,1)).setEmission(new Color(DARK_GRAY)).setMaterial(new Material().setkS(0.8).setnShininess(60));
    private final Plane top = (Plane) new Plane(new Point(0,0,100),new Vector(0,0,-1)).setEmission(new Color(RED)).setMaterial(new Material().setkS(0.8).setnShininess(60));
    private final Plane wallLeft = (Plane) new Plane(new Point(100,0,0),new Vector(-1,0,0)).setEmission(new Color(BLUE)).setMaterial(new Material().setkS(0.8).setnShininess(60));
    private final Plane wallRight = (Plane) new Plane(new Point(-100,0,0),new Vector(1,0,0)).setEmission(new Color(GREEN)).setMaterial(new Material().setkS(0.8).setnShininess(60));
    private final Plane wallFront = (Plane) new Plane(new Point(0,-100,0),new Vector(0,1,0)).setEmission(new Color(GRAY)).setMaterial(new Material().setkS(0.8).setnShininess(60));

    private Intersectable sphere2     = new Sphere(20d, new Point(-50, -30,-50 ))                                         //
            .setEmission(new Color(BLUE))                                                                                  //
            .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30));
    @Test
    public void buildImage() {

        scene2.setAmbientLight(new AmbientLight(new Color(WHITE), 0.015));
        scene2.getGeometries().add(floor,top,wallFront,wallLeft,wallLeft,wallRight,sphere2);
        scene2.getLights().add(spotLight);
        int n=600;

        // interesting fact when camera is set to position(0,0,1000) an exception is thrown
        camera2.setRayTracerBase(new RayTracerBasic(scene2)).
                setImageWriter(new ImageWriter("SoftShadowTest",n , n)) //
                .renderImage() //
                .writeToImage();
    }


}
