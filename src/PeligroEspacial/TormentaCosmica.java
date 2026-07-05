package PeligroEspacial;

import Utilities.Random;
import Naves.Nave;

public class TormentaCosmica extends PeligroEspacial {

    public TormentaCosmica(Nave nave) {
        super("Tormenta cósmica", calcularDanio(nave));
    }

    private static int calcularDanio(Nave nave) {
        return Random.generarEntero(5, 20);
    }
}
