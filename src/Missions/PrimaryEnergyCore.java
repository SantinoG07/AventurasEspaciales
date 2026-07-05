package Missions;

import Resources.EnergyCore;
import Resources.Obsidian;
import Resources.Resource;

public class PrimaryEnergyCore extends Mission{

    public static final PrimaryEnergyCore INSTANCIA = new PrimaryEnergyCore();
    public PrimaryEnergyCore() {
        super("Núcleo de energía principal", new Resource[]{EnergyCore.INSTANCIA, Obsidian.INSTANCIA}, new int[]{1, 2}, 250, true);
    }
}
