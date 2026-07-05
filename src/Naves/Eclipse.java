package Naves;

import Enums.Velocity;

public class Eclipse extends Nave {

    public Eclipse() {
        super("Eclipse", Velocity.MEDIA, 100, "Incremento de vida");
    }


    @Override
    public void mejorarNave() {
        setVida(getVida() + 40);
    }


}
