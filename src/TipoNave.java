public enum TipoNave {

    FENIX("Fénix", Velocidad.ALTA, 50),
    ECLIPSE("Eclipse", Velocidad.MEDIA, 100),
    GALAXIAN("Galaxian", Velocidad.BAJA, 150);

    private String nombre;
    private Velocidad velocidad;
    private int capacidadCarga;

    TipoNave(String nombre, Velocidad velocidad, int capacidadCarga) {
        this.nombre =nombre;
        this.velocidad = velocidad;
        this.capacidadCarga = capacidadCarga;
    }

    public String getNombre() {
        return nombre;
    }

    public Velocidad getVelocidad() {
        return velocidad;
    }

    public int getCapacidadCarga() {
        return capacidadCarga;
    }
}
