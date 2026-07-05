package Planetas;

import Recursos.MineralComun;
import Recursos.Cristal;
import Recursos.NucleoEnergetico;
import Recursos.Recursos;

public class PlanetaRocoso extends Planeta {
    public static final PlanetaRocoso INSTANCIA = new PlanetaRocoso();

    PlanetaRocoso() {
        super("Planeta rocoso", new Recursos[]{MineralComun.INSTANCIA, Cristal.INSTANCIA, NucleoEnergetico.INSTANCIA}, new int[] {60,25,15});
    }
}
