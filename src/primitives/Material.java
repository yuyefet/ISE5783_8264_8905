package primitives;

public class Material {
    public Double3 kD = Double3.ZERO; //diffuse reflection coefficient
    public Double3 kS = Double3.ZERO; //specular reflection
    public Double3 kT = Double3.ZERO; //transparency coefficient
    public Double3 kR = Double3.ZERO; //reflection coefficient
    public int nShininess = 0; //shininess coefficient of the material



//----- Setters - returning this -----------//

    /**
     * Set the diffuse color of the material to the given color.
     *
     * @param kD The diffuse color of the material.
     * @return The material itself.
     */
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Set the diffuse coefficient to the given value.
     *
     * @param kD Diffuse reflectivity.
     * @return The material itself.
     */
    public Material setkD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }


    /**
     * Set the specular color of the material to the given value.
     *
     * @param kS specular reflectivity
     * @return The material itself.
     */
    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Set the specular reflectance of the material to the given value.
     *
     * @param kS specular reflectivity
     * @return The material itself.
     */
    public Material setkS(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * Sets the shininess of the material.
     *
     * @param nShininess The shininess of the material.
     * @return The material itself.
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     * Set the transparency coefficient of the material
     *
     * @param kT The transparency of the material.
     * @return The material object itself.
     */
    public Material setkT(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * Set the transparency coefficient of the material
     *
     * @param kT The transparency of the material.
     * @return The material object itself.
     */
    public Material setKt(double kT){
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * Set the value of reflection Color
     *
     * @param kR The reflection coefficient of the material.
     * @return The material object itself.
     */
    public Material setkR(Double3 kR) {
        this.kR = kR;
        return this;
    }
    /**
     * Set the value of reflection coefficient
     *
     * @param kR The reflection coefficient of the material.
     * @return The material object itself.
     */
    public Material setkR(double kR) {
        this.kR = new Double3(kR);
        return this;
    }
}
