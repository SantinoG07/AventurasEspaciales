package Missions;

import Resources.CommonMineral;
import Resources.Crystal;
import Resources.Resource;

public class ExternalHullRepair extends Mission{
    public static final ExternalHullRepair INSTANCIA = new ExternalHullRepair();
    public ExternalHullRepair() {
        super("Reparación del casco exterior", new Resource[]{CommonMineral.INSTANCIA, Crystal.INSTANCIA}, new int[]{3,1}, 120,true);
    }

}
