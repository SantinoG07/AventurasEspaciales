package ships;

import Entities.CargoHold;
import Enums.Velocity;
import Resources.Resource;
import Utilities.Print;

public class Ship {

    private String nombre;
    private Velocity velocity;
    private int capacidadCarga;
    private int vida=100;
    private CargoHold cargoHold;

    public Ship(String nombre, Velocity velocity, int capacidadCarga) {
        this.nombre =nombre;
        this.velocity = velocity;
        this.capacidadCarga = capacidadCarga;
        this.cargoHold = new CargoHold(capacidadCarga);
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
    //public ShipType getNave() {return nave;}

    public String getNombre() {return nombre;}

    public Velocity getVelocity() {return velocity;}

    public int getCapacidadCarga() {return capacidadCarga;}

    public int getVida() {return vida;}

    public CargoHold getBodega(){return cargoHold;}
}
