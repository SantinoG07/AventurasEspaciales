package SpaceDanger;

import Entities.Ship;
import Enums.Velocity;
import Utilities.Random;

public class SpacePirate extends SpaceDanger{


    public SpacePirate(Ship nave) {
        super("Pirate espacial ",calcularDanio(nave));
    }

    private static int calcularDanio(Ship nave) {
        int danio = Random.generarEntero(3, 15);
        if(nave.getNave().getVelocidad() == Velocity.ALTA) danio *= 2;
        return danio;
    }

}
