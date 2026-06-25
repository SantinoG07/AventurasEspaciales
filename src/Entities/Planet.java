package Entities;

import Enums.Resource;
import Enums.PlanetType;
import Utilities.Random;
import Utilities.InputHandler;

public class Planet {
    private PlanetType planeta;
    private Resource[] recursosGenerados;

    public Planet(PlanetType planeta){
        this.planeta = planeta;
    }


    //FUNCIONES DE PLANET.
    public Resource generarRecurso(){
        int prob= Random.generarEntero(100);
        if(prob<= planeta.getProbabilidades()[0]){
            return planeta.getRecursos()[0];
        } else if (prob<= planeta.getProbabilidades()[1]) {
            return planeta.getRecursos()[1];
        } else if (prob<= planeta.getProbabilidades()[2]) {
            return planeta.getRecursos()[2];
        }
        return null;
    }

    public int mostrarMenu(InputHandler e){
        System.out.println("Bienvenido a "+ planeta.getNombre());
        System.out.println("Seleccione alguna de las siguientes opciones!");
        System.out.println("1. Minar");
        System.out.println("2. Viajar a otro planeta");
        System.out.println("3. Volver a la base");
        int opc = e.ingresarEntero(1,3);
        return opc;
    }


    //SETTERS.
    public void setTipoPlaneta(PlanetType tipo){this.planeta = tipo;}


}
