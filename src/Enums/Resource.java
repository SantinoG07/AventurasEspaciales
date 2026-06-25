package Enums;

public enum Resource {
    MINERAL_COMUN("Mineral común", 10, 10),
    CRISTAL("Cristal", 15, 35),
    NUCELO_ENERGETICO("Núcleo energético", 40, 80),
    GAS("Gas", 20, 15),
    PLASMA("Plasma", 25, 45),
    LAVA("Lava", 30, 20),
    OBSIDIANA("Obsidiana", 25, 50);

   private String nombre;

    public int getValorVenta() {
        return valorVenta;
    }

    public int getPeso() {
        return peso;
    }

    public String getNombre() {
        return nombre;
    }

    private int peso, valorVenta;


    //GETTERS.

    Resource(String nombre, int peso, int valorVenta){
        this.nombre = nombre;
        this.peso = peso;
        this.valorVenta = valorVenta;
    }
}
