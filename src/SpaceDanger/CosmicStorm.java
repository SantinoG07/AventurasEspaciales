package SpaceDanger;

import Entities.Ship;
import Enums.Velocity;
import Utilities.Random;

public class CosmicStorm extends SpaceDanger{

    public CosmicStorm(Ship nave) {
        super("Tormenta cósmica", calcularDanio(nave));
    }

    private static int calcularDanio(Ship nave) {
        return Random.generarEntero(5, 20);
    }
}
