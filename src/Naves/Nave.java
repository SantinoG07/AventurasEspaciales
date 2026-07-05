package Naves;

import Entidades.Bodega;
import Enums.Velocity;
import Recursos.Recursos;

public class Nave {

    protected String nombre;
    protected Velocity velocity;
    protected int capacidadCarga;
    protected int vida=100;
    protected Bodega bodega;

    public Nave(String nombre, Velocity velocity, int capacidadCarga) {
        this.nombre =nombre;
        this.velocity = velocity;
        this.capacidadCarga = capacidadCarga;
        this.bodega = new Bodega(capacidadCarga);
    }


    //FUNCIONES DE NAVE.
    public void reparar(int recuperacionVida){this.vida += recuperacionVida;};

    public void almacenarRecurso(Recursos recursos) {
        bodega.agregarABodega(recursos);
    }

    public void recibirDanio(double hacerdanio){
        this.vida -= hacerdanio;
    }


    //GETTERS.
    public String getNombre() {return nombre;}

    public Velocity getVelocity() {return velocity;}

    public int getCapacidadCarga() {return capacidadCarga;}

    public int getVida() {return vida;}

    public Bodega getBodega(){return bodega;}
}
