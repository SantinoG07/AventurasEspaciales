package Misiones;

import Recursos.NucleoEnergetico;
import Recursos.Obsidiana;
import Recursos.Recursos;

public class NucleoEnergiaPrincipal extends Mision {

    public static final NucleoEnergiaPrincipal INSTANCIA = new NucleoEnergiaPrincipal();
    public NucleoEnergiaPrincipal() {
        super("Núcleo de energía principal", new Recursos[]{NucleoEnergetico.INSTANCIA, Obsidiana.INSTANCIA}, new int[]{1, 2}, 250, true);
    }
}
