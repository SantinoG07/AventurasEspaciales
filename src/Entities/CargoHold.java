package Entities;

import Enums.Resource;

import java.util.ArrayList;
import java.util.HashMap;

public class CargoHold {
    private int espacioNaveInicial;
    private ArrayList<Resource> resources = new ArrayList<Resource>();
    private int espacioUsado=0;
    CargoHold(int espacioNave){

    this.espacioNaveInicial = espacioNave;
    this.espacioUsado = 0;}



    //FUNCIONES DE LA CARGOHOLD.
    public void agregarABodega(Resource resourceMinado){
        resources.add(resourceMinado);
        espacioUsado += resourceMinado.getPeso();
    }

    public void eliminarRecurso(int index){
        resources.remove(resources.get(index));
    }

    public void mostrarRecursos(){
        if(espacioUsado!=0){
            for (Resource r: resources){
                System.out.println(r.getNombre());
            }

        }else{
            System.out.println("La bodega se encuentra vacia");
        }
    }

    public int vaciarBodega(){
        int precioBodega = 0;
        for(Resource r : resources){
            precioBodega += r.getValorVenta();
            resources.remove(r);
        }
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
