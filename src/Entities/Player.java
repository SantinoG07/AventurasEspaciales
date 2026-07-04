package Entities;

import Missions.Mission;
import ships.Ship;

import java.util.ArrayList;

public class Player {
    private String nombre;
    private int energia=100, creditos = 0;
    private Ship ship;
    private ArrayList<Mission> missions = new ArrayList<>();

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
        missions.get(indexMission).setMisionCompletada();
    }

    //GETTERS.
    public String getNombre() {return nombre;}

    public int getEnergia() {return energia;}

    public int getCreditos() {return creditos;}

    public ArrayList<Mission> getMission(){return missions;}

    public void mostrarDatosFinales() {
        System.out.println("Nombre del jugador: " + nombre);
        System.out.println("Nave utilizada: " + ship.getNombre());
        System.out.println("Créditos obtenidos: " + creditos);
        System.out.println("Recursos en la bodega");
        for (int i = 0; i < ship.getBodega().getRecursos().size(); i++){
            System.out.println((i + 1) + ship.getBodega().getRecursos().get(i).getNombre());
        }
        System.out.println("Misiones completadas: ");
        for (Mission m :missions)
            if(m.getMisionPendiente())
                System.out.println(m.getNombreMision());



    }
}
