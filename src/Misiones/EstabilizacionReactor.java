package Misiones;

import Recursos.Gas;
import Recursos.Plasma;
import Recursos.Recursos;

public class EstabilizacionReactor extends Mision {
    public static final EstabilizacionReactor INSTANCIA = new EstabilizacionReactor();
    public EstabilizacionReactor() {        super("Estabilización del reactor", new Recursos[]{Gas.INSTANCIA, Plasma.INSTANCIA}, new int[]{2, 1}, 170, true);
    }

}
