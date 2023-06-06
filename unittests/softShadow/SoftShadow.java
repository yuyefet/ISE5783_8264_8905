package softShadow;
import geometries.Plane;
import geometries.Polygon;

import geometries.Sphere;
import geometries.Triangle;
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



   /* List<LightSource> lights = new LinkedList<>();
            lights.add(new SpotLight(new Color(255, 197, 143), new Point(40, 40, 115), new Vector(-1, -1, -4)).setkL(0.0004).setkQ(0.0000006));
            lights.add(new SpotLight(new Color(255, 197, 143), new Point(-8, 230, 625), new Vector(0, -1, -1)).setkL(0.0004).setkQ(0.0000006));


    Plane rightWall = (Plane) new Plane(new Point(200, 0, 0), new Vector(-1, 0, 0)).setEmission(new Color(GRAY));
    Plane leftWall = (Plane) new Plane(new Point(-200, 0, 0), new Vector(1, 0, 0)).setEmission(new Color(GRAY));
    Plane solidFloor = (Plane) new Plane(new Point(0, -50, 0), new Vector(0,1,0)).setEmission(new Color(132, 133, 98));
    Polygon backWallTop = (Polygon) new Polygon(new Point(-250, 250, 200), new Point(-250, 450, 200), new Point(250, 450, 200), new Point(250, 250, 200))
            .setEmission(new Color(121, 248, 248));
    Polygon backWallBottom = (Polygon) new Polygon(new Point(-250, -50, 200), new Point(-250, 50, 200), new Point(250, 50, 200), new Point(250, -50, 200))
            .setEmission(new Color(222, 184, 135));
    Polygon backWallLeft = (Polygon) new Polygon(new Point(-250, 50, 200), new Point(-250, 250, 200), new Point(-150, 250, 200), new Point(-150, 50, 200))
            .setEmission(new Color(222, 184, 135));
    Polygon backWallRight = (Polygon) new Polygon(new Point(150, 50, 200), new Point(150, 250, 200), new Point(250, 250, 200), new Point(250, 50, 200))
            .setEmission(new Color(222, 184, 135));



    Polygon window = (Polygon) new Polygon(new Point(-150, 50, 200), new Point(-150, 250, 200), new Point(150, 250, 200), new Point(150, 50, 200))
            .setMaterial(new Material().setKt(0.78).setkD(0.22));
    Plane sky = (Plane) new Plane(new Point(-5000, -500, 190), new Vector(0, 0, -1))
            .setEmission(new Color(201, 226, 255)).setMaterial(new Material().setnShininess(10).setkS(0.2));*/

}
