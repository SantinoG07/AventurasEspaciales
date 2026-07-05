package Naves;

import Enums.Velocity;

public class Fenix extends Nave {
    public static final Fenix INSTANCIA = new Fenix();

    public Fenix() {
        super("Fénix", Velocity.ALTA, 50);
    }
}
