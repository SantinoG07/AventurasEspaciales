package Planetas;

import Recursos.Recursos;
import Utilities.Entradas;
import Utilities.Random;

public class Planeta {
    public static final Planeta[] TODOS = {PlanetaRocoso.INSTANCIA, PlanetaGaseoso.INSTANCIA, PlanetaVolcanico.INSTANCIA};
    protected String nombre;
    protected Recursos[] recursos;
    protected int[] probabilidades;

    Planeta(String nombre, Recursos[] recursos, int[] probabilidades){
        this.nombre = nombre;
        this.recursos = recursos;
        this.probabilidades = probabilidades;
    }

    //FUNCIONES DE PLANETA
    public Recursos generarRecurso(){
        int prob= Random.generarEntero(100);
        Recursos recursoGenerado;
        if(prob<=probabilidades[0]){
            recursoGenerado = recursos[0];
        } else if (prob <= probabilidades[0] + probabilidades[1]) {
            recursoGenerado = recursos[1];
        } else {
            recursoGenerado = recursos[2];
        }
        return recursoGenerado;
    }

    public int mostrarMenu(Entradas e){
        System.out.println("Bienvenido a "+ nombre);
        System.out.println("Seleccione alguna de las siguientes opciones!");
        System.out.println("1. Minar");
        System.out.println("2. Viajar a otro planeta");
        System.out.println("3. Volver a la base");
        int opc = e.ingresarEntero(1,3);
        return opc;
    }



    //GETTERS
    public String getNombre() {
        return nombre;
    }

}
