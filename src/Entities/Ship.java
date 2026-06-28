package Entities;

import Enums.Resource;
import Enums.ShipType;

public class Ship {

    private ShipType nave;
    private int vida=100;
    private CargoHold cargoHold;


    public Ship(ShipType nave){
        this.nave=nave;
        this.cargoHold = new CargoHold(nave.getCapacidadCarga());
    }

    //FUNCIONES DE SHIP.
    public void reparar(int recuperacionVida){this.vida += recuperacionVida;};

    public void almacenarRecurso(Resource resource) {
        cargoHold.agregarABodega(resource);
    }

    public void recibirDanio(double hacerdanio){
        this.vida -= hacerdanio;
    }

    //GETTERS.
    public ShipType getNave() {return nave;}

    public int getVida() {return vida;}

    public CargoHold getBodega(){return cargoHold;}
}
