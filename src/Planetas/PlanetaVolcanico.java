package Planetas;

import Recursos.*;

public class PlanetaVolcanico extends Planeta {
    public static final PlanetaVolcanico INSTANCIA = new PlanetaVolcanico();
    PlanetaVolcanico(){
        super("Planeta volcánico", new Recursos[]{Lava.INSTANCIA, Obsidiana.INSTANCIA, NucleoEnergetico.INSTANCIA}, new int[] {50,30,20});
    }

}
