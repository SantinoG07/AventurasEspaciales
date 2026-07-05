package Planets;

import Resources.CommonMineral;
import Resources.Crystal;
import Resources.EnergyCore;
import Resources.Resource;

public class RockyPlanet extends Planet{
    public static final RockyPlanet INSTANCIA = new RockyPlanet();

    RockyPlanet() {
        super("Planeta rocoso", new Resource[]{CommonMineral.INSTANCIA, Crystal.INSTANCIA, EnergyCore.INSTANCIA}, new int[] {60,25,15});
    }
}
