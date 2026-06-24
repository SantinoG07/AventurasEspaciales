import Utilities.Aleatorio;
import Utilities.Entrada;

public class Planeta {

    private TipoPlaneta planeta;
    private Recurso[] recursosGenerados;

    public Planeta(TipoPlaneta planeta){
        this.planeta = planeta;
    }

    public Recurso generarRecurso(){
        int prob= Aleatorio.generarEntero(100);
        if(prob<= planeta.getProbabilidades()[0]){
            return planeta.getRecursos()[0];
        } else if (prob<= planeta.getProbabilidades()[1]) {
            return planeta.getRecursos()[1];
        } else if (prob<= planeta.getProbabilidades()[2]) {
            return planeta.getRecursos()[2];
        }
        return null;
    }

    public int mostrarMenu(Entrada e){
        System.out.println("Bienvenido a "+ planeta.getNombre());
        System.out.println("Seleccione alguna de las siguientes opciones!");
        System.out.println("1. Minar");
        System.out.println("2. Viajar a otro planeta");
        System.out.println("3. Volver a la base");
        int opc = e.ingresarEntero(1,3);
        return opc;
    }

    public void setTipoPlaneta(TipoPlaneta tipo){this.planeta = tipo;}







}
