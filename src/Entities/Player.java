package Entities;

import Enums.MissionsType;

import java.util.ArrayList;

public class Player {
    private String nombre;
    private int energia=100, creditos = 0;
    private Ship ship;private ArrayList<Mision> missions = new ArrayList<>();

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

    public void marcarMisionCompletada(int indexMission) {
        //missions.get(indexMission).();
    }

    //GETTERS.
    public String getNombre() {return nombre;}

    public int getEnergia() {return energia;}

    public int getCreditos() {return creditos;}

    public ArrayList<MissionsType> getMission(){return missions;}
}
