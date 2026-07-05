package Misiones;

import Recursos.Recursos;

public class Mision {
    protected String nombreMision;
    protected Recursos[] recursos;
    protected int[] cantidadResources;
    protected int recompensa;
    protected Boolean misionPendiente;

    public Mision(String nombreMision, Recursos[] recursos, int[] cantidadResources, int recompensa, boolean misionPendiente){
        this.nombreMision = nombreMision;
        this.recursos = recursos;
        this.cantidadResources = cantidadResources;
        this.recompensa = recompensa;
        this.misionPendiente = misionPendiente;
    }


    //SETTERS.
    public  void setMisionCompletada(){
        this.misionPendiente = false;
    }

    //GETTERS.
    public String getNombreMision() {
        return nombreMision;
    }

    public Recursos[] getResources() {
        return recursos;
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
