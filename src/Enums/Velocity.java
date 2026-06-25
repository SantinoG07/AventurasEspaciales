package Enums;

public enum Velocity {
    ALTA(0.20),
    MEDIA(0.40),
    BAJA(0.60);

    private double peligroEspacial;
    Velocity(double peligroEspacial){
        this.peligroEspacial = peligroEspacial;
    };

    public double getPeligroEspacial() {
        return peligroEspacial;
    }
}
