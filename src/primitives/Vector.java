package primitives;

public class Vector extends Point {
    public Vector(double x, double y, double z)
    {
        /**
         * we have to cheack if it's ok to create the vector even if it's 0
         */
        super(x,y,z);

        Double3 isZero = new Double3(x, y, z);
        if(isZero.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector 0 is illegal");
    }
    public Vector(final Double3 xyz){
        super(xyz);
        /**
         * we have to cheack if it's ok to create the vector even if it's 0
         */


        if(xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector 0 is illegal");
    }
}
