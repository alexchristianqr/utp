/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package utp.edu.pe.poo.Semana07;

/**
 *
 * @author LAB-USR-AQ265-A0302
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

//        Figura circulo = new Circulo(2,4,6);
//        circulo.area();
//        String mensaje = circulo.mostrarInfo();
//        System.out.println(mensaje);

        Figura[] figuras = new Figura[7];
        figuras[0] = new Circulo(1.0, 1.0, 5.4);
        figuras[1] = new Circulo(1.0, 2.0, 5);
        figuras[2] = new Rectangulo(1.5, 3.0, 20, 10);
        figuras[3] = new Rectangulo(1.75, 4.0, 20, 9);
        figuras[4] = new Rectangulo(1.59, 5.0, 20, 8);
        figuras[5] = new Rectangulo(1.96, 6.0, 20, 7);
        figuras[6] = new Circulo(1.76, 7.0, 13);

        for (Figura figura : figuras) {
            System.out.println("La figura [" + figura.getClass().getSimpleName() + "] es: " + figura.mostrarInfo());
        }
    }

}
