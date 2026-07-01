import Entities.Player;
import Entities.Ship;
import Entities.Planet;
import Enums.Resource;
import Enums.ShipType;
import Enums.PlanetType;
import SpaceDanger.CosmicStorm;
import SpaceDanger.Renegade;
import SpaceDanger.SpaceDanger;
import SpaceDanger.SpacePirate;
import Utilities.Print;
import Utilities.Random;
import Utilities.InputHandler;

public class Game {
    private InputHandler e = new InputHandler();
    Boolean flag=true;
    Planet planet;

    public Game(){};

    public void iniciar(){
        //Mensajes de comienzo.
        Print.amarillo("Bienvenido al juego Aventuras espaciales!");
        Print.amarillo("Presione enter para comenzar.");
        e.esperarEnter();

        //Inicializacion de entidades.
        Player player = crearJugador(e);
        Ship ship = new Ship(crearNave(e));
        SpaceBase base = new SpaceBase(player, ship);

        //Muestreo de info general.
        mostrarInformacionGeneral(player, ship);
        e.esperarEnter();

        //Inicializacion del menu.
        boolean salida;
        do{
            salida = iniciarAccion(base.mostrarMenu(e), e, player, ship);
        }while(!salida);
        player.mostrarDatosFinales();
        e.cerrarScanner();
    }

    //FUNCIONES DE LOS MENUES.
    private boolean iniciarAccion(int opc, InputHandler e, Player player, Ship ship){
        switch (opc){
            case 1:
                viajarPlaneta();
                int opcPlaneta = planet.mostrarMenu(e);
                generarPeligroEspacial(ship);
                if (ship.getVida() <= 0) {
                    System.out.println("Resultado: ");
                    System.out.println("Derrota");
                    return true;
                }
                iniciarAccionPlaneta(opcPlaneta, player, ship, planet);
                break;
            case 2:
                System.out.println(ship.getBodega().getEspacioUsado());
                break;
            case 3:
                SpaceBase.venderRecurso(ship.getBodega(), e, player);
                break;
            case 4:
                SpaceBase.mostrarMisiones(player);
                break;
            case 5:
               SpaceBase.entregarRecursosMision(player, e);
                break;
            case 6:
                SpaceBase.repararNave(player, e);
                break;
            case 7:
                player.descansar();
                System.out.println("El jugador ha restaurado su vida al 100%");
                break;
            case 8:
                System.out.println("Resultado: ");
                System.out.println("Salida voluntaria");
                return true;
        }
        return false;
    }

    private void generarPeligroEspacial(Ship ship) {
        SpaceDanger spaceDanger;
        SpacePirate spacePirate;
        Renegade renegade;
        CosmicStorm cosmicStorm;
        double peligro = Random.generarDouble(0, 1);
        if(ship.getNave().getVelocidad().getPeligroEspacial() > peligro){
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
        }
    }

    private void iniciarAccionPlaneta(int opcPlaneta, Player player, Ship ship, Planet planet){
        switch (opcPlaneta){
            case 1:
                int energiaNecesaria = Random.generarEntero(10,25);
                if(player.getEnergia()-energiaNecesaria>0){
                    Resource rec = planet.generarRecurso();
                    Print.rojo(rec.getNombre());
                    Print.rojo(Integer.toString(rec.getPeso()) );
                    if(rec.getPeso() + ship.getBodega().getEspacioUsado() < ship.getBodega().getEspacioNaveInicial()){
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
    private void viajarPlaneta(){
        System.out.println("Seleccione uno de los planetas a viajar:");
        for (int i = 0; i < PlanetType.values().length; i++) {
            Print.keyAzul(Integer.toString(i+1)+". ", PlanetType.values()[i].getNombre());
        }
        int numPlaneta = e.ingresarEntero(1, PlanetType.values().length);
        planet=null;
        planet= new Planet(PlanetType.values()[numPlaneta-1]);
    }


    //FUNCIONES PARA MOSTRAR INFORMACION.
    private void mostrarInformacionGeneral(Player player, Ship ship) {
        Print.keyAzul("Nombre jugador: ", player.getNombre());
        Print.keyAzul("Energia jugador: ", player.getEnergia());
        Print.keyAzul("Creditos jugador: ", player.getCreditos());
        Print.keyAzul("Nombre nave: ", ship.getNave().getNombre());
        Print.keyAzul("Enums.Velocidad nave: ", ship.getNave().getVelocidad());
        Print.keyAzul("Vida nave: ", ship.getVida());
        Print.keyAzul("Capacidad de carga nave: ", ship.getNave().getCapacidadCarga());
        Print.keyAzul("Carga actual nave: ", ship.getBodega().getEspacioUsado());
    }


    //FUNCIONES DE CREACION.
    private Player crearJugador(InputHandler e){
        System.out.println("Ingrese el nombre del jugador");
        String nombre = e.ingresarTexto();
        return new Player(nombre);
    }

    private ShipType crearNave(InputHandler e) {
        Print.naranja("Seleccione su nave para la aventura!");
        for (int i = 0; i < ShipType.values().length; i++) {
            Print.keyVerde((i+1)+ ". ", ShipType.values()[i]);
        }
        return switch (e.ingresarEntero(1, ShipType.values().length)-1) {
            case 1 -> ShipType.values()[1];
            case 2 -> ShipType.values()[2];
            default -> ShipType.values()[0];
        };
    }



}
