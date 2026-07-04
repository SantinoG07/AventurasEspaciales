package SpaceDanger;

import Enums.Velocity;
import Utilities.Random;
import ships.Ship;

public class Renegade extends SpaceDanger{

    public Renegade(Ship nave) {
        super("Renegado", calcularDanio(nave));
    }

    private static int calcularDanio(Ship nave) {
        int danio = Random.generarEntero(3, 15);
        if(nave.getVelocity() == Velocity.BAJA) danio *= 2;
        return danio;
    }
}
