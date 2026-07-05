package SpaceDanger;

import ships.Ship;

public class SpaceDanger {
    protected String nombre;
    protected int danioInflingido;

    public SpaceDanger(String nombre, int danioInflingido) {
       this.nombre = nombre;
       this.danioInflingido = danioInflingido;
    }

    public void atacarNave(Ship nave){
        nave.recibirDanio(danioInflingido);
    }

    public int getDanioInflingido(){return danioInflingido;}
}
