package ships;

import Enums.Velocity;

public class Fenix extends Ship {
    public static final Fenix INSTANCIA = new Fenix();

    public Fenix() {
        super("Fénix", Velocity.ALTA, 50);
    }
}
