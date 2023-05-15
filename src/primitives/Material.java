package primitives;

public class Material {
    public Double3 kD = Double3.ZERO, kS = Double3.ZERO;
    public int nShininess = 0;

//----- Setters - returning this -----------//

    /**
     * setter to kD with Double3
     * @param kD
     * @return
     */
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * setter to kD with double
     * @param kD
     * @return
     */
    public Material setkD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }


    /**
     * setter to kS with Double3
     * @param kS
     * @return
     */
    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * setter to kS with double
     * @param kS
     * @return
     */
    public Material setkS(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
