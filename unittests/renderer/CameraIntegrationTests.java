package renderer;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CameraIntegrationTests {

    final int VIEW_PLANE= 3;
    static final Point ZERO_POINT = new Point(0, 0, 0);

    /***
     * This function return the number of intersection between the camera's rays and the geometry
     * @param camera the camera
     * @param geometry Sphere, Triangle or Plane
     * @return
     */
    int countIntersections(Camera camera,Intersectable geometry)
    {
        int counter =0;
        camera.setViewPlaneSize(VIEW_PLANE,VIEW_PLANE);
        for(int i = 0;i<VIEW_PLANE;i++) {
            for (int j=0;j<VIEW_PLANE;j++) {
                List<Point> inter = geometry.findIntersections(camera.constructRay(3, 3, j, i));
                if (inter != null)
                    counter += inter.size();
            }
        }
        return counter;
    }

    @Test
    void CameraSphereIntersections() {

        // TC01 Sphere radius = 1
        Camera camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, -1, 0)).setViewPlaneDistance(1);
        assertEquals(2,countIntersections(camera,new Sphere(1,new Point(0,0,-3))),"wrong number of intersection");

        // TC02 Sphere radius = 2.5
        camera = new Camera(new Point(0,0,0.5), new Vector(0, 0, -1), new Vector(0, -1, 0)).setViewPlaneDistance(1);
        assertEquals(18,countIntersections(camera,new Sphere(2.5,new Point(0,0,-2.5))),"wrong number of intersection");

        // TC03 Sphere radius = 2
        assertEquals(10,countIntersections(camera,new Sphere(2,new Point(0,0,-2))),"wrong number of intersection");

        // TC04 Sphere radius = 4
        camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, -1, 0)).setViewPlaneDistance(1);
        assertEquals(9,countIntersections(camera,new Sphere(4,new Point(0,0,-2))),"wrong number of intersection");

        // TC05 Sphere radius = 0.5
        camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, -1, 0)).setViewPlaneDistance(1);
        assertEquals(0,countIntersections(camera,new Sphere(0.5,new Point(0,0,1))),"wrong number of intersection");
    }

    @Test
    void CameraPlaneIntersections()
    {
        final Camera camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, -1, 0)).setViewPlaneDistance(1);


        //TC01
        assertEquals(9,countIntersections(camera,new Plane(new Point(0,0,-2),camera.getVTo())));

        // TC02
        //assertEquals(9,countIntersections(camera,new Plane(new Point(-0.99,0,0),new Point(-0.99,-1,0), new Point(-1,-1,1))));

        // TC03
        assertEquals(6,countIntersections(camera,new Plane(new Point(0,0,0),new Point(0,-1,0), new Point(-1,-1,1))));

    }

    @Test
    public void cameraTriangleIntersections() {

        final Camera camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, -1, 0)).setViewPlaneDistance(1);

        //TC01
        assertEquals(1,countIntersections(camera,new Triangle(new Point(0,1,-2),new Point(1,-1,-2),new Point(-1,-1,-2))));

        //TC02
        assertEquals(2,countIntersections(camera,new Triangle(new Point(0,1,-2),new Point(1,-1,-2),new Point(0,20,-2))));


    }
}
