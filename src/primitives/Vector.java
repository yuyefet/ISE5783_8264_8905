package primitives;

/**
 * Vector class
 */
public class Vector extends Point {
    /**
     * Three params Constructor
     * if the vector isZero = (0,0,0) => throw an Exception
     * @param x coordinate x
     * @param y coordinate y
     * @param z coordinate z
     */
    public Vector(double x, double y, double z) {
        super(x,y,z);

        Double3 isZero = new Double3(x, y, z);
        if(isZero.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector 0 is illegal");
    }

    /**
     * One param Constructor
     * if the vector isZero = (0,0,0) => throw an Exception
     * @param xyz variable of type Double3 which represent the coordinate of the Vector
     */
    public Vector(final Double3 xyz) {
        super(xyz);

        if(xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector 0 is illegal");
    }

    /**
     * Add two Vectors
     * @param vec Vector to add with
     * @return return the Vector who represent the addition
     */
    public Vector add(Vector vec) {
        Double3 temp = this.xyz.add(vec.xyz);
        return new Vector(temp);
    }

    /**
     * Scale function : multiply
     * @param scalar the scalar to multiply with
     * @return return the Vector multiply by the scalar
     */
    public Vector scale(double scalar) {

        return new Vector(this.xyz.scale(scalar));
    }

    /**
     *
     * @param vec Vector to multiply by
     * @return return the result of the vector multiplication
     */
    public Vector crossProduct(Vector vec) {
        /** there are three units vectors i(1,0,0), j(0,1,0) and k(0,0,1) */
        Double3 temp1 = this.xyz;
        Double3 temp2 = vec.xyz;
        double x = temp1.d2*temp2.d3- temp1.d3* temp2.d2; /** Calculate determinant of j and k columns*/
        double y = -(temp1.d1* temp2.d3-temp1.d3*temp2.d1); /** Calculate determinant of i and k columns*/
        double z = temp1.d1* temp2.d2-temp1.d2*temp2.d1; /** Calculate determinant of i and j columns*/
        return new Vector(x,y,z);

    }

    /**
     *
     * @param vec vector to scalar product by
     * @return return the vector result of the scalar product
     */
    public double dotProduct(Vector vec) {
        Double3 temp1=this.xyz;
        Double3 temp2=vec.xyz;
        return (temp1.d1*temp2.d1 +temp1.d2*temp2.d2+temp1.d3*temp2.d3 ); /** algebraic formula of scalar product */

    }

    /**
     * Calculate the length squared of the vector.
     * @return return the vector's length squared
     */
    public double lengthSquared() {
        return this.dotProduct(this);
    }

    /**
     * Calculate the length of the vector
     * @return return the vector's length
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * normalize the vector
     * @return return the vector normalized
     */
    public Vector normalize() {
        Double3 temp=this.xyz;
        double x = temp.d1/this.length();
        double y = temp.d2/this.length();
        double z = temp.d3/this.length();
        return new Vector(x,y,z);
    }
    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector vector)) return false;
        return xyz.equals(vector.xyz);
    }
}
