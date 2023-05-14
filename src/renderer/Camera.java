package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/***
 * Camera class with represented by position (Point), three vectors and the ViewPlane.
 */
public class Camera {
    private Point position;
    private Vector vRight, vUp, vTo;
    private double height, width, distance;

    private ImageWriter imageWriter;

    private RayTracerBase rayTracerBase;

    /***
     * camera ctor.
     * throws an IllegalArgumentException exeption if vectors are not orthogonal
     * @param position
     * @param vUp
     * @param vTo
     */
    public Camera(Point position, Vector vTo, Vector vUp){

        this.position = position;
        if(isZero(vUp.dotProduct(vTo))) {
            this.vTo = vTo.normalize();
            this.vUp = vUp.normalize();
            this.vRight = this.vTo.crossProduct(this.vUp).normalize();//vectors are orthogonal
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


    public Vector getVRight() {
        return vRight;
    }


    public Vector getVUp() {
        return vUp;
    }


    public Vector getVTo() {
        return vTo;
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
        Point pC = position.add(vTo.scale(distance));
        double rY = height / nY;
        double rX = width / nX;

        double yI = alignZero(-(i - (nY - 1) / 2d) * rY);
        double xJ = alignZero((j - (nX - 1) / 2d) * rX);

        Point pIJ = pC;
        if(!isZero(xJ))
            pIJ = pIJ.add(vRight.scale(xJ));
        if(!isZero(yI))
            pIJ = pIJ.add(vUp.scale(yI));

        Vector vIJ = pIJ.subtract(position);

        return new Ray(position, vIJ);
    }


    /**
     * set imageWriter
     * @param imageWriter
     * @return
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * set RayTracerBase
     */
    public Camera setRayTracerBase(RayTracerBase rayTracerBase) {
        this.rayTracerBase = rayTracerBase;
        return this;
    }

    public void renderImage(){
        try{
            if(imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
            }
            if(rayTracerBase == null){
                throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
            }
        }
        catch(MissingResourceException ex){
            throw new UnsupportedOperationException("Not implemented yet" + ex.getClassName());
        }

    }
}
