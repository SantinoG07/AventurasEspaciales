package Utilities;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Entradas {

    private Scanner s = new Scanner(System.in);

    public int ingresarEntero(int min, int max){
        int opc = 0;
        boolean error = false;
        do {
            error = false;
            System.out.println("Ingrese una opción entre "+Colors.GREEN+min+ Colors.RESET+ " y "+ Colors.GREEN+max+Colors.RESET);

            try {
                opc = s.nextInt();
                if(opc<min || opc>max){
                    Print.rojo("Opción inválida, el número debe estar entre "+min+" y "+max+". Intente nuevamente.");
                    error = true;
                }
            } catch(InputMismatchException e){
                Print.rojo("El dato ingresado debe ser un número, intente nuevamente.");
                error = true;
            } finally {
                s.nextLine();
            }
        } while(error);
        return opc;
    }

    public String ingresarTexto(){
        return s.nextLine();
    }

    public void esperarEnter(){s.nextLine();}

    public void cerrarScanner(){
        s.close();
    }

}

