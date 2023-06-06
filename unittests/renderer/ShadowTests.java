package renderer;

import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import geometries.*;
import lighting.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/** Testing basic shadows
 * @author Dan */
public class ShadowTests {
   private Intersectable sphere     = new Sphere(60d, new Point(0, 0, -200))                                         //
      .setEmission(new Color(BLUE))                                                                                  //
      .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30));
   private Material      trMaterial = new Material().setkD(0.5).setkS(0.5).setnShininess(30);

   private Scene         scene      = new Scene.SceneBuilder("Test scene").build();
   private Camera        camera     = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))   //
      .setViewPlaneSize(200, 200).setViewPlaneDistance(1000)                                                                       //
      .setRayTracerBase(new RayTracerBasic(scene));

   /** Helper function for the tests in this module */
   void sphereTriangleHelper(String pictName, Triangle triangle, Point spotLocation) {
      scene.getGeometries().add(sphere, triangle.setEmission(new Color(BLUE)).setMaterial(trMaterial));
      scene.getLights().add( //
                       new SpotLight(new Color(400, 240, 0), spotLocation, new Vector(1, 1, -3)) //
                          .setkL(1E-5).setkQ(1.5E-7));
      camera.setImageWriter(new ImageWriter(pictName, 400, 400)) //
         .renderImage() //
         .writeToImage();
   }

   /** Produce a picture of a sphere and triangle with point light and shade */
   @Test
   public void sphereTriangleInitial() {
      sphereTriangleHelper("shadowSphereTriangleInitial", //
                           new Triangle(new Point(-70, -40, 0), new Point(-40, -70, 0), new Point(-68, -68, -4)), //
                           new Point(-100, -100, 200));
   }


   /**
	 * Sphere-Triangle shading - move triangle up-right
	 */
	@Test
	public void sphereTriangleMove1() {
		sphereTriangleHelper("shadowSphereTriangleMove2", //
                new Triangle(new Point(-62, -32, 0), new Point(-32, -62, 0), new Point(-60, -60, -4)), //
				new Point(-100, -100, 200));
	}

   /**
	 * Sphere-Triangle shading - move triangle upper-righter
	 */
	@Test
	public void sphereTriangleMove2() {
		sphereTriangleHelper("shadowSphereTriangleMove1", //
                new Triangle(new Point(-49, -19, 0), new Point(-19, -49, 0), new Point(-47, -47, -4)), //
				new Point(-100, -100, 200));
	}

   /** Sphere-Triangle shading - move spot closer */
   @Test
   public void sphereTriangleSpot1() {
      sphereTriangleHelper("shadowSphereTriangleSpot1", //
                           new Triangle(new Point(-70, -40, 0), new Point(-40, -70, 0), new Point(-68, -68, -4)), //
              new Point(-88, -88, 120));
   }

   /** Sphere-Triangle shading - move spot even more close */
   @Test
   public void sphereTriangleSpot2() {
      sphereTriangleHelper("shadowSphereTriangleSpot2", //
                           new Triangle(new Point(-70, -40, 0), new Point(-40, -70, 0), new Point(-68, -68, -4)), //
              new Point(-76, -76, 70)) ;
   }

   /** Produce a picture of a two triangles lighted by a spot light with a Sphere
    * producing a shading */
   @Test
   public void trianglesSphere() {
      scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

      scene.getGeometries().add( //
                           new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                                        new Point(75, 75, -150)) //
                              .setMaterial(new Material().setkS(0.8).setnShininess(60)), //
                           new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                              .setMaterial(new Material().setkS(0.8).setnShininess(60)), //
                           new Sphere(30d, new Point(0, 0, -11)) //
                              .setEmission(new Color(BLUE)) //
                              .setMaterial(new Material().setkD(0.5).setkD(0.5).setnShininess(30)) //
      );
      scene.getLights().add( //
              new SpotLight(new Color(700, 400, 400), new Point(40, 40, 115), new Vector(-1, -1, -4)) //
                      .setkL(4E-4).setkQ(2E-5));

      camera.setImageWriter(new ImageWriter("shadowTrianglesSphere", 600, 600)) //
         .renderImage() //
         .writeToImage();
   }

    private Scene scene2      = new Scene.SceneBuilder("Test scene").build();
    private Camera camera2     = new Camera(new Point(0, 120, 0), new Vector(0, -1, 0), new Vector(0, 0, 1))   //
            .setViewPlaneSize(150, 150).setViewPlaneDistance(100);

    private final SpotLight spotLight= (SpotLight) new SpotLight(new Color(700, 400, 400),new Point(0,-50,90),new Vector(0,0,-1)).setkL(4E-4).setkQ(2E-5);
    private final SpotLight spotLight2= (SpotLight) new SpotLight(new Color(700, 400, 400),new Point(90,-50,90),new Vector(0,0,-1)).setkL(4E-4).setkQ(2E-5);

    private final Plane floor = (Plane) new Plane(new Point(0,0,-100),new Vector(0,0,1)).setEmission(new Color(WHITE)).setMaterial(new Material().setkS(0.8).setnShininess(60));
    private final Plane top = (Plane) new Plane(new Point(0,0,100),new Vector(0,0,-1)).setEmission(new Color(RED)).setMaterial(new Material().setkS(0.8).setnShininess(60));
    private final Plane wallRight = (Plane) new Plane(new Point(100,0,0),new Vector(-1,0,0)).setEmission(new Color(BLUE)).setMaterial(new Material().setkS(0.8).setnShininess(60));
    private final Plane wallLeft = (Plane) new Plane(new Point(-100,0,0),new Vector(1,0,0)).setEmission(new Color(GREEN)).setMaterial(new Material().setkS(0.8).setnShininess(60));
    private final Plane wallFront = (Plane) new Plane(new Point(0,-100,0),new Vector(0,1,0)).setEmission(new Color(GRAY)).setMaterial(new Material().setkS(0.8).setnShininess(60));
    private final Plane wallBack = (Plane) new Plane(new Point(0,150,0),new Vector(0,-1,0)).setEmission(new Color(ORANGE)).setMaterial(new Material().setkS(0.8).setnShininess(60));

    private final Sphere sphere2 = (Sphere) new Sphere(20,new Point(0,20,-20)).setEmission(new Color(BLUE)).setMaterial(new Material().setkD(0.5).setkD(0.5).setnShininess(30)) ;

    @Test
    public void buildImage() {

        scene2.getGeometries().add(floor,top,wallBack,wallFront,wallLeft,wallLeft,wallRight,sphere2);
        scene2.getLights().add(spotLight);
        scene2.getLights().add(spotLight2);
        int n=600;

        // interesting fact when camera is set to position(0,0,1000) an exception is thrown
        camera2.setRayTracerBase(new RayTracerBasic(scene2)).
                setImageWriter(new ImageWriter("SoftShadowTest",n , n)) //
                .renderImage() //
                .writeToImage();
    }

}
