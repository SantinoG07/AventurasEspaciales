package Entities;

import Resources.Resource;
import Utilities.Print;

import java.util.ArrayList;
import java.util.HashMap;

public class CargoHold {
    private int espacioNaveInicial;
    private ArrayList<Resource> resources = new ArrayList<Resource>();
    private int espacioUsado=0;
    public CargoHold(int espacioNave){

    this.espacioNaveInicial = espacioNave;
    this.espacioUsado = 0;}



    //FUNCIONES DE LA CARGOHOLD.
    public void agregarABodega(Resource resourceMinado){
        resources.add(resourceMinado);
        espacioUsado += resourceMinado.getPeso();
    }

    public void eliminarRecurso(int index){
        this.espacioUsado = espacioUsado-resources.get(index).getPeso();
        resources.remove(resources.get(index));

    }

    public void mostrarRecursos(Boolean mostrarIndex){
        if(espacioUsado!=0){
            if(!mostrarIndex){
                for (Resource r: resources){
                    Print.azul(r.getNombre());
                }
            }else{
                for (int i = 0; i < resources.size(); i++) {
                    Print.keyAzul(i+1+". ", resources.get(i).getNombre());
                }
            }
        }else{
            System.out.println("La bodega se encuentra vacia");
        }
    }

    public int vaciarBodega(){
        int precioBodega = 0;
        for(Resource r : resources){
            precioBodega += r.getValorVenta();
        }
        resources.clear();
        return precioBodega;
    }
    
    public HashMap contabilizacionResources(){
        HashMap<Resource, Integer> cantidades = new HashMap<>();
        for (Resource r : resources){
            cantidades.put(r, cantidades.getOrDefault(r,0)+1); //El getOrDefault, hace que si r no tiene un valor usa 0 default
        }
        return cantidades;
    }

    //GETTERS.
    public int getEspacioNaveInicial() {
        return espacioNaveInicial;
    }

    public ArrayList<Resource> getRecursos() {
        return resources;
    }

    public int getEspacioUsado() {
        return espacioUsado;
    }
}
