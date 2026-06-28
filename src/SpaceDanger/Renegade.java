package SpaceDanger;

import Entities.Ship;
import Enums.Velocity;
import Utilities.Random;

public class Renegade extends SpaceDanger{

    public Renegade(Ship nave) {
        super("Renegado", calcularDanio(nave));
    }

    private static int calcularDanio(Ship nave) {
        int danio = Random.generarEntero(3, 15);
        if(nave.getNave().getVelocidad() == Velocity.BAJA) danio *= 2;
        return danio;
    }
}
