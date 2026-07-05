package Missions;

import Resources.Gas;
import Resources.Plasma;
import Resources.Resource;

public class ReactorStabilization extends Mission {
    public static final ReactorStabilization INSTANCIA = new ReactorStabilization();
    public ReactorStabilization() {        super("Estabilización del reactor", new Resource[]{Gas.INSTANCIA, Plasma.INSTANCIA}, new int[]{2, 1}, 170, true);
    }

}
