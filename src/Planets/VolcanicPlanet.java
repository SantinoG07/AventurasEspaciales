package Planets;

import Resources.EnergyCore;
import Resources.Lava;
import Resources.Obsidian;
import Resources.Resource;

public class VolcanicPlanet extends Planet{
    public static final VolcanicPlanet INSTANCIA = new VolcanicPlanet();
    VolcanicPlanet(){
        super("Planeta volcánico", new Resource[]{Lava.INSTANCIA, Obsidian.INSTANCIA, EnergyCore.INSTANCIA}, new int[] {50,30,20});
    }

}
