import Entidades.Bodega;
import Entidades.Jugador;
import Misiones.Mision;
import Recursos.Recursos;
import Utilities.Entradas;
import Utilities.Print;
import Naves.Nave;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BaseEspacial {
    //Inicializacion de las entidades.
    private Jugador jugador;
    private static Nave nave;

    public BaseEspacial(Jugador jugador, Nave nave){
        this.jugador = jugador;
        this.nave = nave;
    }

    //MUESTREO DE MENU.
    public int mostrarMenu(Entradas e, Mision[] misiones){
        int opc = 0;
        if(chequearVictoria(misiones)){
            opc = 10;
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
            Print.keyAzul("8. ", "Mostrar informacion actual");
            Print.keyAzul("9. ", "Mejorar nave");
            Print.keyAzul("10. ", "Salir del juego");
            opc = e.ingresarEntero(1,10);
        }
        return opc;
    }

    private boolean chequearVictoria(Mision[] misiones) {
        int cantCompletada = 0;
        for (Mision m : misiones) {
            if(!m.getMisionPendiente()) cantCompletada++;
        }
        return cantCompletada == 3;
    }

    //FUNCIONES DE SPACE BASE;
    public static void repararNave(Jugador jugador, Entradas e){
            if(jugador.getCreditos()>=25){
                int tramosDisponibles = (100- nave.getVida())/10;
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

    public static void venderRecurso(Bodega bodega, Entradas e, Jugador jugador){
        System.out.println("Bienvenido al mercado");
        if(bodega.getEspacioUsado()!=0){
            System.out.println("Ingrese una de las siguientes opciones!");
            System.out.println("1. Vender un recurso espcífico");
            System.out.println("2. Vender todos los recursos");
            int op = e.ingresarEntero(1, 2);
            if(op ==1 ){
                System.out.println("Seleccione un recurso a vender");
                nave.getBodega().mostrarRecursos(true);
                int index = e.ingresarEntero(1, bodega.getRecursos().size())-1;
                jugador.recibirCreditos(bodega.getRecursos().get(index).getValorVenta());
                bodega.eliminarRecurso(index);
                Print.azul("Vendido con exito!");
                e.esperarEnter();
            } else {
                int precioBodega = bodega.vaciarBodega();
                jugador.recibirCreditos(precioBodega);
                Print.azul("Vendido con exito!");
                e.esperarEnter();
            }
        }else{
            Print.rojo("La bodega esta vacia");
            e.esperarEnter();
        }
    }

    public static void mostrarMisiones(Mision[] misions, Entradas e){
        for (int i = 0; i < misions.length; i++) {
            Print.keyAzul((i+1)+ ". ", misions[i].getNombreMision());
            for (int j = 0; j < misions[i].getResources().length; j++) {
                Print.keyAzul("\t - ",  misions[i].getResources()[j].getNombre()+" ("+misions[i].getCantidadResources()[j]+")");
            }
            Print.keyAzul("Recompensa: ", misions[i].getRecompensa());
            Print.keyAzul("Estado: " , (misions[i].getMisionPendiente() ? "Pendiente" : "Completada"));
        }
        e.esperarEnter();
    }

    public static void entregarRecursosMision(Jugador jugador, Entradas input, Mision[] misions){
        int opc=0;
        do{
            System.out.println("Seleccione la mision que quiera entregar recursos");
            mostrarMisiones(misions, input);
            int indexMission = input.ingresarEntero(1, misions.length)-1;
            if(!misions[indexMission].getMisionPendiente()){
                System.out.println("La mision seleccionada ya ha sido completada!");
            }else{
                if(verificarResourcesDisponibles(misions[indexMission])){
                    jugador.recibirCreditos(misions[indexMission].getRecompensa());
                    Main.marcarMisionCompletada(indexMission);
                    eliminarRecursosMission(indexMission, misions);
                    System.out.println("Mision completada con exito!");
                }else{
                    Print.rojo("Los recursos recolectados no son suficientes para completar la mision");
                };
            }
            System.out.println("Desea seleccionar otra?(1.Si, 2.No)");
            opc=input.ingresarEntero(1,2);
        }while(opc!=2);
    }

    private static void eliminarRecursosMission(int indexMission, Mision[] misions){
        for (int i = 0; i < misions[indexMission].getResources().length; i++) {
            int cantidadEliminar = misions[indexMission].getCantidadResources()[i];
            Recursos recursosEliminar = misions[indexMission].getResources()[i];
            for (int j = 0; j < cantidadEliminar; j++) {
                int indexEliminar = nave.getBodega().getRecursos().indexOf(recursosEliminar);
                nave.getBodega().eliminarRecurso(indexEliminar);
            }
        }
    };

    private static boolean verificarResourcesDisponibles(Mision mision){
        HashMap<Recursos, Integer> cantidades = nave.getBodega().contabilizacionResources();

        for (int i = 0; i < mision.getResources().length; i++) {
            if(!cantidades.containsKey(mision.getResources()[i])||
            cantidades.getOrDefault(mision.getResources()[i],0)< mision.getCantidadResources()[i]){
                return false;
            }
        }
        return true;
    }


}