package Entities;

import Enums.Resource;

import java.util.ArrayList;

public class CargoHold {
    private int espacioNaveInicial;
    private ArrayList<Resource> resources;
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
