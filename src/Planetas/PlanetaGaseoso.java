package Planetas;

import Recursos.Cristal;
import Recursos.Gas;
import Recursos.Plasma;
import Recursos.Recursos;

public class PlanetaGaseoso extends Planeta {
    public static final PlanetaGaseoso INSTANCIA = new PlanetaGaseoso();

    PlanetaGaseoso(){
        super("Planeta gaseoso", new Recursos[]{Gas.INSTANCIA, Plasma.INSTANCIA, Cristal.INSTANCIA}, new int[]{60,25,15});
    }
}
