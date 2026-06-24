public enum Velocidad {
    ALTA(0.20),
    MEDIA(0.40),
    BAJA(0.60);

    private double peligroEspacial;
    Velocidad(double peligroEspacial){
        this.peligroEspacial = peligroEspacial;
    };

    public double getPeligroEspacial() {
        return peligroEspacial;
    }
}
