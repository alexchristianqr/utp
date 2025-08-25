package utp.edu.pe.taller.Semana02;

import java.util.Scanner;

public class Semana02 {

    // Declarar como una variable global a nivel de clase
    static Scanner dato;

    public static void main(String[] args) {

        // Instanciar o crear objeto
        dato = new Scanner(System.in);

        // Ejecutar método
        ejercicio01();
    }

    public static void ejercicio01() {
        // D
        // d = v.t ; t = d/v ; v = d/t
        double distancia, velocidad, tiempo;

        // I
        System.out.println("Ingresa la velocidad");
        velocidad = dato.nextDouble();
        System.out.println("Ingresa el tiempo");
        tiempo = dato.nextDouble();

        // P
        distancia = velocidad * tiempo;

        // S
        System.out.println("La distancia es: " + distancia + " km");

    }
}
