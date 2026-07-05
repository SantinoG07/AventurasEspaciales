import Entidades.Bodega;
import Entidades.Jugador;
import Misiones.ReparacionCascoExterior;
import Misiones.Mision;
import Misiones.NucleoEnergiaPrincipal;
import Misiones.EstabilizacionReactor;
import Recursos.Recursos;
import PeligroEspacial.TormentaCosmica;
import PeligroEspacial.Renegado;
import PeligroEspacial.PeligroEspacial;
import PeligroEspacial.PirataEspacial;
import Utilities.Print;
import Utilities.Random;
import Utilities.Entradas;
import Planetas.Planeta;
import Naves.*;

public class Main {
    private static Entradas entrada = new Entradas();
    private static Planeta[] todosLosPlanetas = Planeta.TODOS;
    private static Planeta planeta;
    private static Mision[] misiones = {new ReparacionCascoExterior(), new NucleoEnergiaPrincipal(), new EstabilizacionReactor()};
    private static final Nave[] naves = {new Fenix(), new Galaxian(), new Eclipse()};

    public static void main(String[] args) {
        iniciar();
    }

    public static void iniciar(){
        //Mensajes de comienzo.
        Print.amarillo("Bienvenido al juego Aventuras espaciales!");
        Print.amarillo("Presione enter para comenzar.");
        entrada.esperarEnter();

        //Inicializacion de entidades.
        Jugador jugador = crearJugador(entrada);
        Nave nave = crearNave(entrada, naves);
        BaseEspacial base = new BaseEspacial(jugador, nave);

        //Muestreo de info general.
        mostrarInformacionGeneral(jugador, nave);
        entrada.esperarEnter();

        //Inicializacion del menu.
        boolean salida=false;
        do{
            if(revisarMisionesCompletadas()==3){
                salida=true;
            }else{
                salida = iniciarAccion(base.mostrarMenu(entrada, misiones), entrada, jugador, nave);
            }
         }while(!salida);




        if (nave.getVida() <= 0) {
            Print.rojo("Has perdido por ataque espaciales!");
            mostrarDatosFinales(nave, jugador, nave.getBodega(), misiones);
        }else{
            Print.verde("Has ganado!");
            mostrarDatosFinales(nave, jugador, nave.getBodega(), misiones);
        }

        entrada.cerrarScanner();
    }

    //FUNCIONES DE LOS MENUES.
    private static boolean iniciarAccion(int opc, Entradas entrada, Jugador jugador, Nave nave){
        switch (opc){
            case 1:
                boolean volverBase=false;
                do{
                    boolean cambiarPlaneta=false;
                    viajarPlaneta();
                    generarPeligroEspacial(nave);
                    do{
                        if (nave.getVida() <= 0) {
                            System.out.println("Resultado: ");
                            System.out.println("Derrota");
                            return true;
                        }
                        int opcPlaneta = planeta.mostrarMenu(entrada);
                        switch (opcPlaneta) {
                            case 1:
                                minar(jugador, nave, planeta);
                                break;
                            case 2:
                                cambiarPlaneta=true;
                                break;
                            case 3:
                                cambiarPlaneta=true;
                                volverBase=true;
                        }
                    }while(!cambiarPlaneta);
                }while(!volverBase);
                break;
            case 2:
                Print.keyAzul("Toneladas usadas: ", nave.getBodega().getEspacioUsado());
                nave.getBodega().mostrarRecursos(false);
                entrada.esperarEnter();
                break;
            case 3:
                BaseEspacial.venderRecurso(nave.getBodega(), entrada, jugador);
                break;
            case 4:
                BaseEspacial.mostrarMisiones(misiones, entrada);
                break;
            case 5:
                BaseEspacial.entregarRecursosMision(jugador, entrada, misiones);
                break;
            case 6:
                BaseEspacial.repararNave(jugador, entrada);
                break;
            case 7:
                jugador.descansar();
                System.out.println("El jugador ha restaurado su vida al 100%");
                break;
            case 8:
                Print.keyAzul("Creditos jugador: ", jugador.getCreditos());
                Print.keyAzul("Vida nave: ", nave.getVida());
                Print.keyAzul("Carga actual de la nave: ", nave.getCapacidadCarga());
                entrada.esperarEnter();
                break;
            case 9:
                final int PRECIO_MEJORA_NAVE = 10000;
                Print.azul("La mejora disponible es:" + nave.getNombreMejora());
                Print.verde("Mejorar nave:" + PRECIO_MEJORA_NAVE + " creditos");
                Print.rojo("Desea continuar?(1.Si/2.No)");
                int continuar = entrada.ingresarEntero(1, 2);
                if(continuar == 1 && jugador.getCreditos() >= PRECIO_MEJORA_NAVE){
                    jugador.restarCreditos(PRECIO_MEJORA_NAVE);
                    nave.mejorarNave();
                } else {
                    Print.rojo("No tienes suficientes créditos para mejorar la nave.");
                }
                Print.verde("Nave mejorada con exito!");
                entrada.esperarEnter();
                break;
            case 10:
                System.out.println("Resultado: Salida voluntaria");
                return true;
        }
        return false;
    }

    private static void generarPeligroEspacial(Nave nave) {
        PeligroEspacial peligroEspacial = null;
        double peligro = Random.generarDouble(0, 1);
        if(nave.getVelocity().getPeligroEspacial() > peligro){
            int numPeligro = Random.generarEntero(1, 3);
            switch (numPeligro){
                case 1 : peligroEspacial = new PirataEspacial(nave);
                    peligroEspacial.atacarNave(nave);
                    break;
                case 2: peligroEspacial = new Renegado(nave);
                    peligroEspacial.atacarNave(nave);
                    break;
                case 3: peligroEspacial = new TormentaCosmica(nave);
                    peligroEspacial.atacarNave(nave);
                    break;
            }
            Print.rojo("La nave a sufrido un ataque! Se le restaron "+ peligroEspacial.getDanioInflingido()+" puntos de vida!");
        }
    }


    //FUNCIONES RELACIONADAS CON PLANETAS.
    private static void minar(Jugador jugador, Nave nave, Planeta planeta){
        int energiaNecesaria = Random.generarEntero(10,25);
        if(jugador.getEnergia()-energiaNecesaria>=0){
            Recursos rec = planeta.generarRecurso();
            if(rec.getPeso() + nave.getBodega().getEspacioUsado() < nave.getBodega().getEspacioNaveInicial()){
                Print.rojo(rec.getNombre());
                Print.keyAzul(Integer.toString(rec.getPeso()), " de peso" );
                jugador.minar(energiaNecesaria);
                nave.almacenarRecurso(rec);
                System.out.println("Recurso recolectado! "+rec.getNombre());
            }else{
                System.out.println("El espacio de la bodega es insuficiente!");
            }
        }else{
            System.out.println("Energia insuficiente, debes volver a la base");
        }
    };

    private static void viajarPlaneta(){
        System.out.println("Seleccione uno de los planetas a viajar:");
        for (int i = 0; i < todosLosPlanetas.length; i++) {
            Print.keyAzul(i + 1 +". ", todosLosPlanetas[i].getNombre());
        }
        int numPlaneta = entrada.ingresarEntero(1, todosLosPlanetas.length);
        planeta = todosLosPlanetas[numPlaneta -1];
    }


    //FUNCIONES PARA MOSTRAR INFORMACION.
    private static void mostrarInformacionGeneral(Jugador jugador, Nave nave) {
        Print.keyAzul("Nombre jugador: ", jugador.getNombre());
        Print.keyAzul("Energia jugador: ", jugador.getEnergia());
        Print.keyAzul("Creditos jugador: ", jugador.getCreditos());
        Print.keyAzul("Nombre nave: ", nave.getNombre());
        Print.keyAzul("Velocidad nave: ", nave.getVelocity());
        Print.keyAzul("Vida nave: ", nave.getVida());
        Print.keyAzul("Capacidad de carga nave: ", nave.getCapacidadCarga());
        Print.keyAzul("Carga actual nave: ", nave.getBodega().getEspacioUsado());
    }


    //FUNCIONES DE CREACION.
    private static Jugador crearJugador(Entradas e){
        System.out.println("Ingrese el nombre del jugador");
        String nombre = e.ingresarTexto();
        return new Jugador(nombre);
    }

    private static Nave crearNave(Entradas entrada, Nave[] naves) {
        Print.naranja("Seleccione su nave para la aventura!");
        for (int i = 0; i < naves.length; i++) {
            Print.keyVerde((i+1)+ ". ", naves[i].getNombre());
            Print.keyAzul("Velocidad: " , naves[i].getVelocity());
            Print.keyAzul("Vida: " , naves[i].getVida());
            Print.keyAzul("Capacidad de carga: " , naves[i].getCapacidadCarga());
            System.out.println();
        }
        return switch (entrada.ingresarEntero(1, naves.length)-1) {
            case 1 -> naves[1];
            case 2 -> naves[2];
            default -> naves[0];
        };
    }

    //FUNCIONES DE MUESTREO
    public static void mostrarDatosFinales(Nave nave, Jugador jugador, Bodega bodega, Mision[] misiones) {
        System.out.println("Nombre del jugador: " + jugador.getNombre());
        System.out.println("Nave utilizada: " + nave.getNombre());
        System.out.println("Créditos obtenidos: " + jugador.getCreditos());
        System.out.println("Recursos en la bodega");
        bodega.mostrarRecursos(false);
        System.out.println("Misiones ");
        for(Mision mision: misiones){
            if(!mision.getMisionPendiente()){
                Print.azul(mision.getNombreMision() + ", completada!");
            }else{
                Print.rojo(mision.getNombreMision()+", sin completar :(");
            }
        }


    }

    //FUNCION PARA REVISAR MISIONES
    public static int revisarMisionesCompletadas(){
        int completadas=0;
        for(Mision m: misiones){
            if(m.getMisionPendiente()==false){
                completadas++;
            }
        }
        return completadas;
    }

    //FUNCION PARA MARCAR MISIONES COMO COMPLETADAS
    public static void marcarMisionCompletada(int indexMission) {
        misiones[indexMission].setMisionCompletada();
    }



}
