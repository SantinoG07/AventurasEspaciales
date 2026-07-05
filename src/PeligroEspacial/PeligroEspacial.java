package PeligroEspacial;

import Naves.Nave;

public class PeligroEspacial {
    protected String nombre;
    protected int danioInflingido;

    public PeligroEspacial(String nombre, int danioInflingido) {
       this.nombre = nombre;
       this.danioInflingido = danioInflingido;
    }

    //FUNCIONES DE PELIGRO ESPACIAL
    public void atacarNave(Nave nave){
        nave.recibirDanio(danioInflingido);
    }

    //GETTERS
    public int getDanioInflingido(){return danioInflingido;}
}
