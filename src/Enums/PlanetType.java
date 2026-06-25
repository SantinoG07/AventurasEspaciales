package Enums;

public enum PlanetType {

    ROCOSO("Entities.Planeta rocoso",new Resource[]{Resource.MINERAL_COMUN, Resource.CRISTAL, Resource.NUCELO_ENERGETICO}, new int[] {60,25,15}),
    GASEOSO("Entities.Planeta gaseoso", new Resource[]{Resource.GAS, Resource.PLASMA, Resource.CRISTAL}, new int[] {60,25,15}),
    VOLCANICO("Entities.Planeta volcánico",new Resource[]{Resource.LAVA, Resource.OBSIDIANA, Resource.NUCELO_ENERGETICO}, new int[] {50,30,20});

    private String nombre;
    private Resource[] resources;
    private int[] probabilidades;

    PlanetType(String nombre, Resource[] resources, int[] probabilidades) {
        this.nombre =nombre;
        this.resources = resources;
        this.probabilidades = probabilidades;
    }


    //GETTERS
    public String getNombre() {
        return this.nombre;
    }
    public Resource[] getRecursos(){return resources;};
    public int[] getProbabilidades(){return probabilidades;};


}
