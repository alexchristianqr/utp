package semana08;

public class Main {

    public static void main(String[] args) {
        Serie serie = new Serie();
//        System.out.println(serie.siguiente());
//        System.out.println(serie.siguiente());
//        serie.reiniciar();
//        System.out.println(serie.siguiente());
//        
        for (int i = 0; i < 5; i++) {
            System.out.println(serie.siguiente());
        }
        serie.reiniciar();
        for (int i = 0; i < 5; i++) {
            System.out.println(serie.siguiente());
        }
        serie.comenzar(100);
        for (int i = 0; i < 5; i++) {
            System.out.println(serie.siguiente());
        }
    }
}
