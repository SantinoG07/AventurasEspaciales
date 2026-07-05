package Recursos;

public class Recursos {
    protected String nombre;
    protected int peso, valorVenta;


    Recursos(String nombre, int peso, int valorVenta){
        this.nombre = nombre;
        this.peso = peso;
        this.valorVenta = valorVenta;
    }


    //GETTERS.
    public String getNombre() {
        return nombre;
    }

    public int getPeso() {
        return peso;
    }

    public int getValorVenta() {
        return valorVenta;
    }
}
