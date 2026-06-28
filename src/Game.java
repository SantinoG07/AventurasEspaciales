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
import Utilities.Random;
import Utilities.InputHandler;

public class Game {
    private InputHandler e = new InputHandler();
    Boolean flag=true;
    Planet planet;

    public Game(){};

    public void iniciar(){
        //Mensajes de comienzo.
        System.out.println("Bienvenido al juego Aventuras espaciales!");
        System.out.println("Presione enter para comenzar.");
        e.esperarEnter();

        //Inicializacion de entidades.
        Player player = crearJugador(e);
        Ship ship = new Ship(crearNave(e));
        SpaceBase base = new SpaceBase(player, ship);

        //Muestreo de info general.
        mostrarInformacionGeneral(player, ship);

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
                iniciarAccionPlaneta(opcPlaneta, player, ship);
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

    private void iniciarAccionPlaneta(int opcPlaneta, Player player, Ship ship){
        switch (opcPlaneta){
            case 1:
                int energiaNecesaria = Random.generarEntero(10,25);
                if(player.getEnergia()-energiaNecesaria>0){
                    Resource rec = planet.generarRecurso();
                    if(rec.getPeso() + ship.getBodega().getEspacioUsado() > ship.getBodega().getEspacioNaveInicial()){
                        player.minar(energiaNecesaria);
                        ship.almacenarRecurso(rec);
                        System.out.println("Enums.Recurso recolectado! "+rec.getNombre());
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
            System.out.println(i+ PlanetType.values()[i].getNombre());
        }
        int numPlaneta = e.ingresarEntero(0, PlanetType.values().length);
        planet.setTipoPlaneta(PlanetType.values()[numPlaneta]);
    }


    //FUNCIONES PARA MOSTRAR INFORMACION.
    private void mostrarInformacionGeneral(Player player, Ship ship) {
        System.out.println("Nombre jugador: "+ player.getNombre());
        System.out.println("Energia jugador: "+ player.getEnergia());
        System.out.println("Creditos jugador: "+ player.getCreditos());
        System.out.println("Nombre nave: "+ ship.getNave().getNombre());
        System.out.println("Enums.Velocidad nave: "+ ship.getNave().getVelocidad());
        System.out.println("Vida nave: "+ ship.getVida());
        System.out.println("Capacidad de carga nave: "+ ship.getNave().getCapacidadCarga());
        System.out.println("Carga actual nave: "+ ship.getBodega().getEspacioUsado());
    }


    //FUNCIONES DE CREACION.
    private Player crearJugador(InputHandler e){
        System.out.println("Ingrese el nombre del jugador");
        String nombre = e.ingresarTexto();
        return new Player(nombre);
    }

    private ShipType crearNave(InputHandler e) {
        System.out.println("Seleccione su nave para la aventura!");
        for (int i = 0; i < ShipType.values().length; i++) {
            System.out.println((i+1)+ ". "+ ShipType.values()[i]);
        }
        return switch (e.ingresarEntero(1, ShipType.values().length)-1) {
            case 1 -> ShipType.values()[1];
            case 2 -> ShipType.values()[2];
            default -> ShipType.values()[0];
        };
    }



}
