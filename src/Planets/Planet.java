package Planets;

import Resources.Resource;
import Utilities.InputHandler;
import Utilities.Random;

public class Planet {
    protected String nombre;
    protected Resource[] resources;
    protected int[] probabilidades;

    Planet(String nombre, Resource[] resources, int[] probabilidades){
        this.nombre = nombre;
        this.resources = resources;
        this.probabilidades = probabilidades;
    }

    //FUNCIONES DE PLANET.
    public Resource generarRecurso(){
        int prob= Random.generarEntero(100);
        Resource recursoGenerado;

        if(prob >= probabilidades[0] && prob <= probabilidades[2]){
            recursoGenerado = resources[1];
        }else if (prob <= probabilidades[0]){
            recursoGenerado = resources[0];
        }else {
            recursoGenerado = resources[2];
        }

        return recursoGenerado;
    }

    public int mostrarMenu(InputHandler e){
        System.out.println("Bienvenido a "+ nombre);
        System.out.println("Seleccione alguna de las siguientes opciones!");
        System.out.println("1. Minar");
        System.out.println("2. Viajar a otro planeta");
        System.out.println("3. Volver a la base");
        int opc = e.ingresarEntero(1,3);
        return opc;
    }

    public String getNombre() {
        return nombre;
    }

    public Resource[] getResources() {
        return resources;
    }

    public int[] getProbabilidades() {
        return probabilidades;
    }

    public static final Planet[] TODOS = {
            RockyPlanet.INSTANCIA, GasPlanet.INSTANCIA, VolcanicPlanet.INSTANCIA
    };
}
