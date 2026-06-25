package Entities;

public class Player {
    private String nombre;
    private int energia=100, creditos = 0;
    private Ship ship;

    public Player(String nombre) {
        this.nombre = nombre;
    }


    //FUNCIONES DE PLAYER.
    public void descansar(){this.energia = 100;}

    public void restarCreditos(int saldoRestar){this.creditos += saldoRestar;};

    public void minar(int energia){this.energia -=energia;}

    public void recibirCreditos(int precioRecurso){
        this.creditos += precioRecurso;
    }

    //GETTERS.
    public String getNombre() {return nombre;}

    public int getEnergia() {return energia;}

    public int getCreditos() {return creditos;}
}
