package Utilities;

public class Print {
    private Print(){};
    public static void azul(String texto){System.out.println(Colors.BLUE + texto + Colors.RESET);}
    public static void verde(String texto){System.out.println(Colors.GREEN + texto + Colors.RESET);}
    public static void rojo(String texto){System.out.println(Colors.RED + texto + Colors.RESET);}
    public static void naranja(String texto){System.out.println(Colors.ORANGE + texto + Colors.RESET);}
    public static void negro(String texto){System.out.println(Colors.RESET + texto + Colors.RESET);}
    public static void amarillo(String texto){System.out.println(Colors.YELLOW + texto + Colors.RESET);}

    public static void keyAzul(String key, Object valor){
        System.out.println(Colors.BLUE + key + Colors.RESET + valor);
    }
    public static void keyVerde(String key, Object valor){
        System.out.println(Colors.GREEN + key + Colors.RESET + valor);
    }

}
