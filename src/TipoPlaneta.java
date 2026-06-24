public enum TipoPlaneta {

    ROCOSO("Planeta rocoso",new Recurso[]{Recurso.MINERAL_COMUN, Recurso.CRISTAL, Recurso.NUCELO_ENERGETICO}, new int[] {60,25,15}),
    GASEOSO("Planeta gaseoso", new Recurso[]{Recurso.GAS, Recurso.PLASMA, Recurso.CRISTAL}, new int[] {60,25,15}),
    VOLCANICO("Planeta volcánico",new Recurso[]{Recurso.LAVA, Recurso.OBSIDIANA, Recurso.NUCELO_ENERGETICO}, new int[] {50,30,20});

    private String nombre;
    private Recurso[] recursos;
    private int[] probabilidades;

    TipoPlaneta(String nombre, Recurso[] recursos, int[] probabilidades) {
        this.nombre =nombre;
        this.recursos = recursos;
        this.probabilidades = probabilidades;
    }

    public String getNombre() {
        return this.nombre;
    }
    public Recurso[] getRecursos(){return recursos;};
    public int[] getProbabilidades(){return probabilidades;};


}
