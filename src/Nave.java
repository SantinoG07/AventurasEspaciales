public class Nave {

    private TipoNave nave;
    private int vida=100;


    private int cargaBodegaActual = 0;

    public Nave(TipoNave nave){
        this.nave=nave;
    }

    public TipoNave getNave() {return nave;}
    public int getVida() {return vida;}
    public int getCargaBodegaActual() {return cargaBodegaActual;}
    public void reparar(int recuperacionVida){this.vida += recuperacionVida;};
}
