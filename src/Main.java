import Utilities.Entrada;

public class Main {
    public static void main(String[] args) {
        Entrada e = new Entrada();

        System.out.println("Bienvenido al juego Aventuras espaciales!");
        System.out.println("Presione enter para comenzar.");
        e.esperarEnter();

        Jugador jugador = crearJugador(e);
        Nave nave = new Nave(crearNave(e));
        mostrarInformacionGeneral(jugador, nave);


        e.cerrarScanner();
    }


    public static Jugador crearJugador(Entrada e){
        System.out.println("Ingrese el nombre del jugador");
        String nombre = e.ingresarTexto();
        return new Jugador(nombre);
    }


    static TipoNave crearNave(Entrada e) {

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


    private static void mostrarInformacionGeneral(Jugador jugador, Nave nave) {
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