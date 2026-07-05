package Misiones;

import Recursos.MineralComun;
import Recursos.Cristal;
import Recursos.Recursos;

public class ReparacionCascoExterior extends Mision {
    public static final ReparacionCascoExterior INSTANCIA = new ReparacionCascoExterior();
    public ReparacionCascoExterior() {
        super("Reparación del casco exterior", new Recursos[]{MineralComun.INSTANCIA, Cristal.INSTANCIA}, new int[]{3,1}, 120,true);
    }

}
