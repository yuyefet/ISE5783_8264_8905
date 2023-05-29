package primitives;

public class Material {
    public Double3 kD = Double3.ZERO; //diffuse reflection coefficient
    public Double3 kS = Double3.ZERO; //specular reflection
    public Double3 kT = Double3.ZERO; //transparency coefficient
    public Double3 kR = Double3.ZERO; //reflection coefficient
    public int nShininess = 0; //shininess coefficient of the material



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

    /**
     *
     * @param kT
     * @return
     */
    public Material setkT(Double3 kT) {
        this.kT = kT;
        return this;
    }

    public Material setkR(Double3 kR) {
        this.kR = kR;
        return this;
    }
}
