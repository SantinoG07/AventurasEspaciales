import Utilities.Aleatorio;
import Utilities.Entrada;

public class Juego {
    private Entrada e = new Entrada();
    Boolean flag=true;
    Planeta planeta;

    public Juego(){};

    public void iniciar(){
        System.out.println("Bienvenido al juego Aventuras espaciales!");
        System.out.println("Presione enter para comenzar.");
        e.esperarEnter();

        Jugador jugador = crearJugador(e);
        Nave nave = new Nave(crearNave(e));
        mostrarInformacionGeneral(jugador, nave);
        BaseEspacial base = new BaseEspacial(jugador, nave);
        iniciarAccion(base.mostrarMenu(e), e, jugador, nave);




        e.cerrarScanner();
    }



    private void iniciarAccion(int opc, Entrada e, Jugador jugador, Nave nave){
        switch (opc){
            case 1:
                viajarPlaneta();
                int opcPlaneta = planeta.mostrarMenu(e);
                iniciarAccionPlaneta(opcPlaneta, jugador);
                break;
            case 2:
                System.out.println(nave.getCargaBodegaActual());
                break;
            case 3:
                //BaseEspacial.venderRecursos();
                break;
            case 4:
                //BaseEspacial.verMisionesDisponibles();
                break;
            case 5:
                entregarRecursosMision();
                break;
            case 6:
                BaseEspacial.repararNave(jugador, e);
                break;
            case 7:
                jugador.descansar();
                System.out.println("El jugador ha restaurado su vida al 100%");
                break;
            case 8:
                break;

        }
    }


    private void iniciarAccionPlaneta(int opcPlaneta, Jugador jugador){
        switch (opcPlaneta){
            case 1:
                int energiaNecesaria = Aleatorio.generarEntero(10,25);
                if(jugador.getEnergia()-energiaNecesaria>0){
                    jugador.minar(energiaNecesaria);
                    Recurso rec = planeta.generarRecurso();
                    jugador.almacenarRecurso(rec);
                    System.out.println("Recurso recolectado! "+rec.nombre);
                }else{
                    System.out.println("Energia insuficiente, debes volver a la base");
                }
                break;
            case 2:
                break;
            case 3:
                break;
        }
    };

    private void entregarRecursosMision(){}

    private void viajarPlaneta(){
        System.out.println("Seleccione uno de los planetas a viajar:");
        for (int i = 0; i < TipoPlaneta.values().length; i++) {
            System.out.println(i+TipoPlaneta.values()[i].getNombre());
        }
        int numPlaneta = e.ingresarEntero(0,TipoPlaneta.values().length);
        planeta.setTipoPlaneta(TipoPlaneta.values()[numPlaneta]);
    }

    private Jugador crearJugador(Entrada e){
        System.out.println("Ingrese el nombre del jugador");
        String nombre = e.ingresarTexto();
        return new Jugador(nombre);
    }

    private TipoNave crearNave(Entrada e) {
        System.out.println("Seleccione su nave para la aventura!");
        for (int i = 0; i < TipoNave.values().length; i++) {
            System.out.println((i+1)+ ". "+TipoNave.values()[i]);
        }
        return switch (e.ingresarEntero(1,TipoNave.values().length)-1) {
            case 1 -> TipoNave.values()[1];
            case 2 -> TipoNave.values()[2];
            default -> TipoNave.values()[0];
        };
    }

    private void mostrarInformacionGeneral(Jugador jugador, Nave nave) {
        System.out.println("Nombre jugador: "+jugador.getNombre());
        System.out.println("Energia jugador: "+jugador.getEnergia());
        System.out.println("Creditos jugador: "+jugador.getCreditos());
        System.out.println("Nombre nave: "+nave.getNave().getNombre());
        System.out.println("Velocidad nave: "+nave.getNave().getVelocidad());
        System.out.println("Vida nave: "+nave.getVida());
        System.out.println("Capacidad de carga nave: "+nave.getNave().getCapacidadCarga());
        System.out.println("Carga actual nave: "+nave.getCargaBodegaActual());
    }



}
