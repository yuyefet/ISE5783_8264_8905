package primitives;

public class Vector extends Point {
    public Vector(double x, double y, double z)
    {
        /**
         * TODO
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
         * TODO
         * we have to cheack if it's ok to create the vector even if it's 0
         */


        if(xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector 0 is illegal");
    }

    public Vector add(Vector vec)
    {
        Double3 temp = this.xyz.add(vec.xyz);
        return new Vector(temp);
    }

    public Vector scale(double scalar)
    {
        return new Vector(this.xyz.scale(scalar));
    }

    public Vector crossProduct(Vector vec)
    {
        Double3 temp1 = this.xyz;
        Double3 temp2 = vec.xyz;
        double x = temp1.d2*temp2.d3- temp1.d3* temp2.d2;
        double y = -(temp1.d1* temp2.d3-temp1.d3*temp2.d1);
        double z = temp1.d1* temp2.d2-temp1.d2*temp2.d1;
        return new Vector(x,y,z);

    }

    public double dotProduct(Vector vec)
    {
        Double3 temp1=this.xyz;
        Double3 temp2=vec.xyz;
        return (temp1.d1*temp2.d1 +temp1.d2*temp2.d2+temp1.d3*temp2.d3 );

    }
    public double lengthSquared() {
        return this.dotProduct(this);
    }

    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    public Vector normalize()
    {
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
