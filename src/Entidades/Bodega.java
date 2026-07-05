package Entidades;

import Recursos.Recursos;
import Utilities.Print;

import java.util.ArrayList;
import java.util.HashMap;

public class Bodega {
    private int espacioNaveInicial;
    private ArrayList<Recursos> recursos = new ArrayList<Recursos>();
    private int espacioUsado=0;


    public Bodega(int espacioNave){
    this.espacioNaveInicial = espacioNave;
    this.espacioUsado = 0;
    }



    //FUNCIONES DE LA BODEGA.
    public void agregarABodega(Recursos recursosMinado){
        recursos.add(recursosMinado);
        espacioUsado += recursosMinado.getPeso();
    }

    public void eliminarRecurso(int index){
        this.espacioUsado = espacioUsado- recursos.get(index).getPeso();
        recursos.remove(recursos.get(index));

    }

    public void mostrarRecursos(Boolean mostrarIndex){
        if(espacioUsado!=0){
            if(!mostrarIndex){
                for (Recursos r: recursos){
                    Print.azul(r.getNombre());
                }
            }else{
                for (int i = 0; i < recursos.size(); i++) {
                    Print.keyAzul(i+1+". ", recursos.get(i).getNombre());
                }
            }
        }else{
            System.out.println("La bodega se encuentra vacia");
        }
    }

    public int vaciarBodega(){
        int precioBodega = 0;
        for(Recursos r : recursos){
            precioBodega += r.getValorVenta();
        }
        this.espacioUsado = 0;
        recursos.clear();
        return precioBodega;
    }
    
    public HashMap contabilizacionResources(){
        HashMap<Recursos, Integer> cantidades = new HashMap<>();
        for (Recursos r : recursos){
            cantidades.put(r, cantidades.getOrDefault(r,0)+1); //El getOrDefault, hace que si r no tiene un valor usa 0 default
        }
        return cantidades;
    }

    //GETTERS.
    public int getEspacioNaveInicial() {
        return espacioNaveInicial;
    }

    public ArrayList<Recursos> getRecursos() {
        return recursos;
    }

    public int getEspacioUsado() {
        return espacioUsado;
    }
}
