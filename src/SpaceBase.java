import Entities.CargoHold;
import Entities.Player;
import Missions.Mission;
import Resources.Resource;
import Utilities.InputHandler;
import Utilities.Print;
import ships.Ship;

import java.util.ArrayList;
import java.util.HashMap;

public class SpaceBase {
    //Inicializacion de las entidades.
    private Player player;
    private static Ship ship;

    public SpaceBase(Player player, Ship ship){
        this.player = player;
        this.ship = ship;
    }

    //MUESTREO DE MENU.
    public int mostrarMenu(InputHandler e){
        int opc = 0;
        if(chequearVictoria(player)){
            opc = 8;
        } else {
            Print.azul("Bienvenido a la base espacial");
            Print.negro("Seleccione alguna de las siguientes opciones!");
            Print.keyAzul("1. ", "Viajar a un planeta");
            Print.keyAzul("2. ", "Ver bodega de carga");
            Print.keyAzul("3. ", "Vender recursos");
            Print.keyAzul("4. ", "Ver misiones disponibles");
            Print.keyAzul("5. ", "Entregar recursos para una misión");
            Print.keyAzul("6. ", "Reparar nave");
            Print.keyAzul("7. ", "Descansar");
            Print.keyAzul("8. ", "Salir del juego");
            opc = e.ingresarEntero(1,8);
        }
        return opc;
    }

    private boolean chequearVictoria(Player player) {
        ArrayList<Mission> missions = player.getMission();
        int cantCompletada = 0;
        for (Mission m :missions) {
            if(m.getMisionPendiente()) cantCompletada++;
        }
        return cantCompletada == 3;
    }

    //FUNCIONES DE SPACE BASE;
    public static void repararNave(Player player, InputHandler e){
            if(player.getCreditos()>=25){
                int tramosDisponibles = (100- ship.getVida())/10;
                if(tramosDisponibles>=1){
                    System.out.println("La cantidad de tramos disponibles son: "+tramosDisponibles);
                    System.out.println("Indique la cantidad de tramos a reparar");
                    int cantidadTramosReparar = e.ingresarEntero(1, tramosDisponibles);
                    if(player.getCreditos()-(25*cantidadTramosReparar)<0){
                        System.out.println("Saldo insuficiente!");
                    }else{
                        ship.reparar(cantidadTramosReparar*10);
                        player.restarCreditos(cantidadTramosReparar*25);
                    }
                }else{
                    System.out.println("No tiene tramos disponibles para reparar");
                }
            }else{
                System.out.println("No tiene creditos disponibles para reparar");
            }
    }

    public static void venderRecurso(CargoHold cargoHold, InputHandler e, Player player){
        System.out.printf("Bienvenido al mercado");
        System.out.println("Ingrese una de las siguientes opciones!");
        System.out.println("1. Vender un recurso espcífico");
        System.out.println("2. Vender todos los recursos");
        int op = e.ingresarEntero(1, 2);
        if(op ==1 ){
            Resource resource;
            System.out.println("Seleccione un recurso a vender");
            for (int i = 0; i < cargoHold.getRecursos().size(); i++) {
                System.out.println((i+1)+". "+ cargoHold.getRecursos().get(i));
            }
            int index = e.ingresarEntero(0, cargoHold.getRecursos().size());
            player.recibirCreditos(cargoHold.getRecursos().get(index).getValorVenta());
            cargoHold.eliminarRecurso(index);
        } else {
            int precioBodega = cargoHold.vaciarBodega();
            player.recibirCreditos(precioBodega);
        }
    }

    public static void mostrarMisiones(Player player, Mission[] missions){
        for (int i = 0; i < missions.length; i++) {
            System.out.println(i+ ". "+ player.getMission().get(i).getNombreMision());
            System.out.println("/t-"+ missions[i]+" "+ player.getMission().get(i).getCantidadResources());
            System.out.println("Recompensa: "+player.getMission().get(i).getRecompensa());
            System.out.println("Estado: " + (player.getMission().get(i).getMisionPendiente() ? "Pendiente" : "Completada"));
        }
    }

    public static void entregarRecursosMision(Player player, InputHandler input, Mission[] missions){
        int opc=0;
        do{
            System.out.println("Seleccione la mision que quiera entregar recursos");
            mostrarMisiones(player, missions);
            int indexMission = input.ingresarEntero(1, missions.length);
            if(!player.getMission().get(indexMission).getMisionPendiente()){
                System.out.println("La mision seleccionada ya ha sido completada!");
            }else{
                if(verificarResourcesDisponibles(player, missions[indexMission])){
                    player.recibirCreditos(player.getMission().get(indexMission).getRecompensa());
                    player.marcarMisionCompletada(indexMission);
                    eliminarRecursosMission(indexMission, missions);
                    System.out.println("Mision completada con exito!");
                };
            }
            System.out.println("Desea seleccionar otra?(1.Si, 2.No)");
            opc=input.ingresarEntero(1,2);
        }while(opc!=2);
    }

    private static void eliminarRecursosMission(int indexMission, Mission[] missions){
        for (int i = 0; i < missions[indexMission].getResources().length; i++) {
            int cantidadEliminar = missions[indexMission].getCantidadResources()[i];
            Resource resourceEliminar = missions[indexMission].getResources()[i];
            for (int j = 0; j < cantidadEliminar; j++) {
                int indexEliminar = ship.getBodega().getRecursos().indexOf(resourceEliminar);
                ship.getBodega().eliminarRecurso(indexEliminar);
            }
        }
    };

    private static boolean verificarResourcesDisponibles(Player player, Mission mission){
        HashMap<Resource, Integer> cantidades = ship.getBodega().contabilizacionResources();

        for (int i = 0; i < mission.getResources().length; i++) {
            if(!cantidades.containsKey(mission.getResources()[i])||
            cantidades.getOrDefault(mission.getResources()[i],0)< mission.getCantidadResources()[i]){
                return false;
            }
        }
        return true;
    }
}