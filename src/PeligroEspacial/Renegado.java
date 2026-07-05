package PeligroEspacial;

import Enums.Velocity;
import Utilities.Random;
import Naves.Nave;

public class Renegado extends PeligroEspacial {

    public Renegado(Nave nave) {
        super("Renegado", calcularDanio(nave));
    }

    private static int calcularDanio(Nave nave) {
        int danio = Random.generarEntero(3, 15);
        if(nave.getVelocity() == Velocity.BAJA) danio *= 2;
        return danio;
    }
}
