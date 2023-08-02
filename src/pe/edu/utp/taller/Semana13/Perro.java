package pe.edu.utp.taller.Semana13;

public class Perro {
    String nombre;
    String colorPelo;
    int cantidadOjos;

    Perro(String nombre, String colorPelo, int cantidadOjos) {
        this.nombre = nombre;
        this.colorPelo = colorPelo;
        this.cantidadOjos = cantidadOjos;
    }

    public Perro() {
        this.colorPelo = "Marron";
    }

    void ladrar() {
        System.out.println("Ladrando...");
    }

    void caminar() {
        System.out.println("Caminando...");
    }

    void comer() {
        System.out.println("Comiendo...");
    }
}
