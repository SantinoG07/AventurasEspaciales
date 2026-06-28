package Enums;

public enum ShipType {

    FENIX("Fénix", Velocity.ALTA, 50),
    ECLIPSE("Eclipse", Velocity.MEDIA, 100),
    GALAXIAN("Galaxian", Velocity.BAJA, 150);

    private String nombre;
    private Velocity velocity;
    private int capacidadCarga;

    ShipType(String nombre, Velocity velocity, int capacidadCarga) {
        this.nombre =nombre;
        this.velocity = velocity;
        this.capacidadCarga = capacidadCarga;
    }

    //GETTERS.
    public String getNombre() {
        return nombre;
    }
    public Velocity getVelocidad() {
        return velocity;
    }
    public int getCapacidadCarga() {
        return capacidadCarga;
    }
}
