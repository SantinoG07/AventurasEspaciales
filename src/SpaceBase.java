import Entities.CargoHold;
import Entities.Player;
import Entities.Ship;
import Enums.MissionsType;
import Enums.Resource;
import Utilities.InputHandler;

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
        System.out.println("Bienvenido a la base espacial");
        System.out.println("Seleccione alguna de las siguientes opciones!");
        System.out.println("1. Viajar a un planeta");
        System.out.println("2. Ver bodega de carga");
        System.out.println("3. Vender recursos");
        System.out.println("4. Ver misiones disponibles");
        System.out.println("5. Entregar recursos para una misión");
        System.out.println("6. Reparar nave");
        System.out.println("7. Descansar");
        System.out.println("8. Salir del juego");
        int opc = e.ingresarEntero(1,8);
        return opc;
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

    public static void mostrarMisiones(Player player){
        for (int i = 0; i < MissionsType.values().length; i++) {
            System.out.println(i+ ". "+ player.getMission().get(i).getNombreMision());
            System.out.println("/t-"+ MissionsType.values()[i]+" "+ player.getMission().get(i).getCantidadResources());
            System.out.println("Recompensa: "+player.getMission().get(i).getRecompensa());
            System.out.println("Estado: " + (player.getMission().get(i).getMisionPendiente() ? "Pendiente" : "Completada"));
        }
    }

    private void entregarRecursosMision(Ship ship, Player player, InputHandler input){
        int opc=0;
        do{
            System.out.println("Seleccione la mision que quiera entregar recursos");
            mostrarMisiones(player);
            int indexMission = input.ingresarEntero(1,MissionsType.values().length);
            if(!player.getMission().get(indexMission).getMisionPendiente()){
                System.out.println("La mision seleccionada ya ha sido completada!");
            }else{
                if(verificarResourcesDisponibles(player, MissionsType.values()[indexMission])){
                    player.recibirCreditos(player.getMission().get(indexMission).getRecompensa());
                    player.marcarMisionCompletada(indexMission);
                    eliminarRecursosMission(indexMission);
                    System.out.println("Mision completada con exito!");
                };
            }
            System.out.println("Desea seleccionar otra?(1.Si, 2.No)");
            opc=input.ingresarEntero(1,2);
        }while(opc!=2);
    }

    private void eliminarRecursosMission(int indexMission){
        for (int i = 0; i < MissionsType.values()[indexMission].getResources().length; i++) {
            int cantidadEliminar = MissionsType.values()[indexMission].getCantidadResources()[i];
            Resource resourceEliminar = MissionsType.values()[indexMission].getResources()[i];
            for (int j = 0; j < cantidadEliminar; j++) {
                int indexEliminar = ship.getBodega().getRecursos().indexOf(resourceEliminar);
                ship.getBodega().eliminarRecurso(indexEliminar);
            }
        }
    };

    private boolean verificarResourcesDisponibles(Player player, MissionsType mission){
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