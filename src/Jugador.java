import Utilities.Aleatorio;

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

    public void descansar(){this.energia = 100;}
    public void restarCreditos(int saldoRestar){this.creditos += saldoRestar;};
    public void minar(int energia){this.energia -=energia;}

    public void almacenarRecurso(Recurso recurso) {

    }
}
