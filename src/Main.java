import Entities.CargoHold;
import Entities.Player;
import Missions.ExternalHullRepair;
import Missions.Mission;
import Missions.PrimaryEnergyCore;
import Missions.ReactorStabilization;
import Resources.Resource;
import SpaceDanger.CosmicStorm;
import SpaceDanger.Renegade;
import SpaceDanger.SpaceDanger;
import SpaceDanger.SpacePirate;
import Utilities.Print;
import Utilities.Random;
import Utilities.InputHandler;
import Planets.Planet;
import ships.Eclipse;
import ships.Fenix;
import ships.Galaxian;
import ships.Ship;

public class Main {
    private static InputHandler e = new InputHandler();
    static Planet[] todosLosPlanetas = Planet.TODOS;
    static Planet planet;
    private static Mission[] missions = {new ExternalHullRepair(), new PrimaryEnergyCore(), new ReactorStabilization()};
    private static Ship[] ships = {new Fenix(), new Galaxian(), new Eclipse()};

    public static void main(String[] args) {
        iniciar();
    }

    public static void iniciar(){
        //Mensajes de comienzo.
        Print.amarillo("Bienvenido al juego Aventuras espaciales!");
        Print.amarillo("Presione enter para comenzar.");
        e.esperarEnter();

        //Inicializacion de entidades.
        Player player = crearJugador(e);
        Ship ship = crearNave(e,ships);
        SpaceBase base = new SpaceBase(player, ship);

        //Muestreo de info general.
        mostrarInformacionGeneral(player, ship);
        e.esperarEnter();

        //Inicializacion del menu.
        boolean salida;
        do{
            int completadas=0;
            for(Mission m: missions){
                if(m.getMisionPendiente()==true){
                    completadas++;
                }
            }
            if(completadas==3){salida=true;};
            salida = iniciarAccion(base.mostrarMenu(e), e, player, ship);
        }while(!salida);

        if (ship.getVida() <= 0) {
            Print.rojo("Has perdido por ataque espaciales!");
            mostrarDatosFinales(ship, player, ship.getBodega());
        }else{
            Print.verde("Has ganado!");
            mostrarDatosFinales(ship, player, ship.getBodega());
        }
        e.cerrarScanner();
    }

    //FUNCIONES DE LOS MENUES.
    private static boolean iniciarAccion(int opc, InputHandler e, Player player, Ship ship){
        switch (opc){
            case 1:
                boolean volverBase=false;
                do{
                    Boolean cambiarPlaneta=false;
                    viajarPlaneta();
                    generarPeligroEspacial(ship);
                    do{
                        if (ship.getVida() <= 0) {
                            System.out.println("Resultado: ");
                            System.out.println("Derrota");
                            return true;
                        }
                        int opcPlaneta = planet.mostrarMenu(e);
                        switch (opcPlaneta) {
                            case 1:
                                iniciarAccionPlaneta(opcPlaneta, player, ship, planet);
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
                Print.keyAzul("Toneladas usadas: ", ship.getBodega().getEspacioUsado());
                ship.getBodega().mostrarRecursos(false);
                e.esperarEnter();
                break;
            case 3:
                SpaceBase.venderRecurso(ship.getBodega(), e, player);
                break;
            case 4:
                SpaceBase.mostrarMisiones(player, missions,e);
                break;
            case 5:
                SpaceBase.entregarRecursosMision(player, e, missions);
                break;
            case 6:
                SpaceBase.repararNave(player, e);
                break;
            case 7:
                player.descansar();
                System.out.println("El jugador ha restaurado su vida al 100%");
                break;
            case 8:
                Print.keyAzul("Creditos jugador: ", player.getCreditos());
                Print.keyAzul("Vida nave: ", ship.getVida());
                Print.keyAzul("Carga actual de la nave: ",ship.getCapacidadCarga());
                e.esperarEnter();
                break;
            case 9:
                System.out.println("Resultado: Salida voluntaria");
                return true;
        }
        return false;
    }

    private static void generarPeligroEspacial(Ship ship) {
        SpaceDanger spaceDanger = null;
        double peligro = Random.generarDouble(0, 1);
        if(ship.getVelocity().getPeligroEspacial() > peligro){
            int numPeligro = Random.generarEntero(1, 3);
            switch (numPeligro){
                case 1 : spaceDanger= new SpacePirate(ship);
                    spaceDanger.atacarNave(ship);
                    break;
                case 2: spaceDanger = new Renegade(ship);
                    spaceDanger.atacarNave(ship);
                    break;
                case 3: spaceDanger = new CosmicStorm(ship);
                    spaceDanger.atacarNave(ship);
                    break;
            }
            Print.rojo("La nave a sufrido un ataque! Se le restaron "+ spaceDanger.getDanioInflingido()+" puntos de vida!");
        }
    }

    private static void iniciarAccionPlaneta(int opcPlaneta, Player player, Ship ship, Planet planet){
        switch (opcPlaneta){
            case 1:
                int energiaNecesaria = Random.generarEntero(10,25);
                if(player.getEnergia()-energiaNecesaria>=0){
                    Resource rec = planet.generarRecurso();
                    if(rec.getPeso() + ship.getBodega().getEspacioUsado() < ship.getBodega().getEspacioNaveInicial()){
                        Print.rojo(rec.getNombre());
                        Print.keyAzul(Integer.toString(rec.getPeso()), " de peso" );
                        player.minar(energiaNecesaria);
                        ship.almacenarRecurso(rec);
                        System.out.println("Recurso recolectado! "+rec.getNombre());
                    }else{
                        System.out.println("El espacio de la bodega es insuficiente!");
                    }
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

    //FUNCIONES DE PLANETAS.
    private static void viajarPlaneta(){
        System.out.println("Seleccione uno de los planetas a viajar:");
        for (int i = 0; i < todosLosPlanetas.length; i++) {
            Print.keyAzul(i + 1 +". ", todosLosPlanetas[i].getNombre());
        }
        int numPlaneta = e.ingresarEntero(1, todosLosPlanetas.length);
        planet = todosLosPlanetas[numPlaneta -1];
    }


    //FUNCIONES PARA MOSTRAR INFORMACION.
    private static void mostrarInformacionGeneral(Player player, Ship ship) {
        Print.keyAzul("Nombre jugador: ", player.getNombre());
        Print.keyAzul("Energia jugador: ", player.getEnergia());
        Print.keyAzul("Creditos jugador: ", player.getCreditos());
        Print.keyAzul("Nombre nave: ", ship.getNombre());
        Print.keyAzul("Velocidad nave: ", ship.getVelocity());
        Print.keyAzul("Vida nave: ", ship.getVida());
        Print.keyAzul("Capacidad de carga nave: ", ship.getCapacidadCarga());
        Print.keyAzul("Carga actual nave: ", ship.getBodega().getEspacioUsado());
    }


    //FUNCIONES DE CREACION.
    private static Player crearJugador(InputHandler e){
        System.out.println("Ingrese el nombre del jugador");
        String nombre = e.ingresarTexto();
        return new Player(nombre);
    }

    private static Ship crearNave(InputHandler e, Ship[] ships) {
        Print.naranja("Seleccione su nave para la aventura!");
        for (int i = 0; i < ships.length; i++) {
            Print.keyVerde((i+1)+ ". ", ships[i].getNombre());
        }
        return switch (e.ingresarEntero(1, ships.length)-1) {
            case 1 -> ships[1];
            case 2 -> ships[2];
            default -> ships[0];
        };
    }


    public static void marcarMisionCompletada(int indexMission) {
        missions[indexMission].setMisionCompletada();
    }


    public static void mostrarDatosFinales(Ship ship, Player jugador, CargoHold bodega) {
        System.out.println("Nombre del jugador: " + jugador.getNombre());
        System.out.println("Nave utilizada: " + ship.getNombre());
        System.out.println("Créditos obtenidos: " + jugador.getCreditos());
        System.out.println("Recursos en la bodega");
        bodega.mostrarRecursos(false);
        System.out.println("Misiones ");
        for(Mission mision: missions){
            if(!mision.getMisionPendiente()){
                Print.azul(mision.getNombreMision() + ", completada!");
            }else{
                Print.rojo(mision.getNombreMision()+", sin completar :(");
            }
        }


    }



}
