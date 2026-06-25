import Entities.CargoHold;
import Entities.Player;
import Entities.Ship;
import Enums.Resource;
import Utilities.InputHandler;

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
}