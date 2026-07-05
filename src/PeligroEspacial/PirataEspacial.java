package PeligroEspacial;

import Enums.Velocity;
import Utilities.Random;
import Naves.Nave;

public class PirataEspacial extends PeligroEspacial {


    public PirataEspacial(Nave nave) {
        super("Pirate espacial ",calcularDanio(nave));
    }

    private static int calcularDanio(Nave nave) {
        int danio = Random.generarEntero(3, 15);
        if(nave.getVelocity() == Velocity.ALTA) danio *= 2;
        return danio;
    }

}
