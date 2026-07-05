package Naves;

import Enums.Velocity;

public class Galaxian extends Nave {

    public Galaxian() {
        super("Galaxian", Velocity.BAJA, 150, "Incremento de velocidad");
    }

    @Override
    public void mejorarNave() {
        setVelocity(Velocity.ALTA);
    }
}
