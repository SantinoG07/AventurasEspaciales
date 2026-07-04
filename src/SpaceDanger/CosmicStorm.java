package SpaceDanger;

import Utilities.Random;
import ships.Ship;

public class CosmicStorm extends SpaceDanger{

    public CosmicStorm(Ship nave) {
        super("Tormenta cósmica", calcularDanio(nave));
    }

    private static int calcularDanio(Ship nave) {
        return Random.generarEntero(5, 20);
    }
}
