public class Jugador {
    private String nombre;
    private int energia=100, creditos = 0;
    private Nave nave;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEnergia() {
        return energia;
    }

    public int getCreditos() {
        return creditos;
    }

}
