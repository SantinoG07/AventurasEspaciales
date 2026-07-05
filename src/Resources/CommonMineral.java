package Resources;

public class CommonMineral extends Resource{
    public static final CommonMineral INSTANCIA = new CommonMineral();
    public CommonMineral(){
        super("Mineral común", 10, 10);
    }
}
