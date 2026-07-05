package Resources;

public class Resource {
    protected String nombre;
    protected int peso, valorVenta;


    Resource(String nombre, int peso, int valorVenta){
        this.nombre = nombre;
        this.peso = peso;
        this.valorVenta = valorVenta;
    }

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
