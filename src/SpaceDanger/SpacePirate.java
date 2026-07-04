package SpaceDanger;

import Enums.Velocity;
import Utilities.Random;
import ships.Ship;

public class SpacePirate extends SpaceDanger{


    public SpacePirate(Ship nave) {
        super("Pirate espacial ",calcularDanio(nave));
    }

    private static int calcularDanio(Ship nave) {
        int danio = Random.generarEntero(3, 15);
        if(nave.getVelocity() == Velocity.ALTA) danio *= 2;
        return danio;
    }

}
