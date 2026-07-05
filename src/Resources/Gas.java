package Resources;

public class Gas extends Resource{
    public static final Gas INSTANCIA = new Gas();
    public Gas() {
        super("Gas", 20, 15);
    }
}
