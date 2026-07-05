package Missions;

import Resources.Resource;

public class Mission {
    protected String nombreMision;
    protected Resource[] resources;
    protected int[] cantidadResources;
    protected int recompensa;

    public void setMisionPendiente(Boolean misionPendiente) {
        this.misionPendiente = misionPendiente;
    }

    protected Boolean misionPendiente;

    public String getNombreMision() {
        return nombreMision;
    }

    public Resource[] getResources() {
        return resources;
    }

    public int[] getCantidadResources() {
        return cantidadResources;
    }

    public int getRecompensa() {
        return recompensa;
    }

    public Boolean getMisionPendiente() {
        return misionPendiente;
    }

    public Mission(String nombreMision, Resource[] resources, int[] cantidadResources, int recompensa, boolean misionPendiente){
        this.nombreMision = nombreMision;
        this.resources = resources;
        this.cantidadResources = cantidadResources;
        this.recompensa = recompensa;
        this.misionPendiente = misionPendiente;
    }



    public  void setMisionCompletada(){
        this.misionPendiente = false;
    }



}


/*
package Enums;

public enum MissionsType {

    MISION_1("Reparacion del casco exterior", new Resource[]{Resource.MINERAL_COMUN, Resource.CRISTAL},new int[]{3,1}, 120, true),
    MISION_2("Estabilizacion del reactor", new Resource[]{Resource.GAS, Resource.PLASMA}, new int[]{2,1}, 170, true),
    MISION_3("Nucleo de energia principal", new Resource[]{Resource.NUCELO_ENERGETICO, Resource.OBSIDIANA}, new int[]{1,2},250, true);


    MissionsType(String nombreMision, Resource[] resources,int[] cantidadResources, int recompensa, boolean misionPendiente){
    this.nombreMision = nombreMision;
    this.resources = resources;
    this.cantidadResources = cantidadResources;
    this.recompensa=recompensa;
    this.misionPendiente = misionPendiente;
    }

    private String nombreMision;
    private Resource [] resources;
    private int[] cantidadResources;
    private int recompensa;
    private Boolean misionPendiente;

    public String getNombreMision() {
        return nombreMision;
    }

    public  void setMisionCompletada(){
        this.misionPendiente = false;
    }

    public Resource[] getResources() {
        return resources;
    }

    public int[] getCantidadResources() {
        return cantidadResources;
    }

    public int getRecompensa() {
        return recompensa;
    }

    public Boolean getMisionPendiente() {
        return misionPendiente;
    }
}

 */