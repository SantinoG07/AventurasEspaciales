import Utilities.Entrada;

public class BaseEspacial {

    private Jugador jugador;
    private static Nave nave;

    public BaseEspacial(Jugador jugador, Nave nave){
        this.jugador = jugador;
        this.nave = nave;
    }

    public int mostrarMenu(Entrada e){
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


    public static void repararNave(Jugador jugador, Entrada e){
            if(jugador.getCreditos()>=25){
                int tramosDisponibles = (100-nave.getVida())/10;
                if(tramosDisponibles>=1){
                    System.out.println("La cantidad de tramos disponibles son: "+tramosDisponibles);
                    System.out.println("Indique la cantidad de tramos a reparar");
                    int cantidadTramosReparar = e.ingresarEntero(1, tramosDisponibles);
                    if(jugador.getCreditos()-(25*cantidadTramosReparar)<0){
                        System.out.println("Saldo insuficiente!");
                    }else{
                        nave.reparar(cantidadTramosReparar*10);
                        jugador.restarCreditos(cantidadTramosReparar*25);
                    }
                }else{
                    System.out.println("No tiene tramos disponibles para reparar");
                }
            }else{
                System.out.println("No tiene creditos disponibles para reparar");
            }
    }

}