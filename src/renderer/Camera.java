package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

/***
 * Camera class with represented by position (Point), three vectors and the ViewPlane.
 */
public class Camera {
    private Point position;
    private Vector v_right, v_up, v_to;
    private double height, width, distance;


    /***
     * camera ctor.
     * throws an IllegalArgumentException exeption if vectors are not orthogonal
     * @param position
     * @param v_up
     * @param v_to
     */
    public Camera(Point position, Vector v_up, Vector v_to){

        this.position = position;
        if(Util.isZero(v_up.dotProduct(v_to))) {
            this.v_up = v_up.normalize();
            this.v_to = v_to.normalize();
            this.v_right = this.v_up.crossProduct(this.v_to).normalize();//vectors are orthogonal
        }
        else
            throw new IllegalArgumentException("vectors are not orthogonal");

    }

    /**
     * set View Plane Size - width & height
     * @param width
     * @param height
     * @return the camera
     */
    public Camera setViewPlaneSize(double width, double height){
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * set view plane distance
     * @param distance
     * @return the camera
     */
    public Camera setViewPlaneDistance(double distance){
        this.distance = distance;
        return this;
    }
    public Point getPosition() {
        return position;
    }


    public Vector getV_right() {
        return v_right;
    }


    public Vector getV_up() {
        return v_up;
    }


    public Vector getV_to() {
        return v_to;
    }


    public double getHeight() {
        return height;
    }


    public double getWidth() {
        return width;
    }


    public double getDistance() {
        return distance;
    }

    /***
     * Construct Ray method
     * @param nX according to width
     * @param nY according to height
     * @param j according to width
     * @param i according to height
     * @return
     */
    public Ray constructRay(int nX, int nY, int j, int i){
        return null;
    }


}
