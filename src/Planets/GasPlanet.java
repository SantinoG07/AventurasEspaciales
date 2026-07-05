package Planets;

import Resources.Crystal;
import Resources.Gas;
import Resources.Plasma;
import Resources.Resource;

public class GasPlanet extends Planet{
    public static final GasPlanet INSTANCIA = new GasPlanet();

    GasPlanet(){
        super("Planeta gaseoso", new Resource[]{Gas.INSTANCIA, Plasma.INSTANCIA, Crystal.INSTANCIA}, new int[]{60,25,15});
    }
}
